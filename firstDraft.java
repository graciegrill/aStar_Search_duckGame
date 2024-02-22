import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class firstDraft{

    public static boolean checkDucks(ArrayList<Duck> d1, ArrayList<Duck> d2){
        for (int i=0; i< d1.size(); i++){
            if(!d1.get(i).equals(d2.get(i))){
                return false;
            }}
            return true;
            
    }

    public static boolean doesContain(HashMap<ArrayList<Duck>, myNode> k, ArrayList<Duck> n ){
        for (int i = 0; i<k.size(); i++){
            for(ArrayList<Duck> x: k.keySet()){
                if(checkDucks(x, n)){
                    return true;
                }
            }
        }
        return false;
    }

    public static myNode search(int numDucks, int numPos, int flagDuck, int maxEnergy){
        HashMap<ArrayList<Duck>, myNode> reached = new HashMap<ArrayList<Duck>,myNode>();
        ArrayList<Duck> allDucks = new ArrayList<Duck>();
        int i = 0;
        while (i <numDucks){
            allDucks.add(new Duck(0, maxEnergy, maxEnergy, false));
            i++;
        }
        int cost = 0;
        myNode initNode = new myNode(allDucks,null, cost); // node <--Node(problem.initial)
        if (initNode.isGoalNode(flagDuck, initNode.getDucks())){
            System.out.println("Goal found");
            return initNode;
            
        }
        //System.out.println(initNode.toString());
        Queue<myNode> frontier = new PriorityQueue<>(new Comparator<myNode>() {
    @Override
    public int compare(myNode o1, myNode o2) {
        return Integer.compare(o1.getPastCost(), o2.getPastCost());
    }
}); //frontier (FIFO queue) with node as element
        myNode clone = initNode.cloneNode(initNode);
        reached.put(clone.getDucks(), clone);
        //reached<-- problem.initial
        frontier.add(clone);
        //System.out.println(frontier.toString()+ " is frontier at line 50");
        //int j = 0;
        while(!frontier.isEmpty()){
            myNode newNode = frontier.poll();
            //System.out.println(frontier+" is frontier at line 54");
           // myNode newNode1 = newNode.cloneNode(newNode);
            //System.out.println(newNode.toString());
            //j++;

            for (myNode x: newNode.expand(newNode, newNode.getDucks(), numPos, flagDuck)){
                if (x.isGoalNode(flagDuck, x.getDucks())){
                    return x;
                }
                if(doesContain(reached, x.getDucks())  == false){
                    reached.put(x.getDucks(), x);
                    frontier.add(x);
                    System.out.println("true");
                }
            }

            
        }
        //System.out.println(j);
        return null;

    }
    public static void main(String[]args){
        myNode x = search(7, 4, 3, 5);
        if(x == null){
            System.out.println("No solution found");
        }
        else{
            System.out.println("SOLUTION FOUND");
            System.out.println(x.toString());
        }


    }
}