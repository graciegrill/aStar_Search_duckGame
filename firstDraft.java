import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

public class firstDraft{

    public static boolean checkDucks(ArrayList<Duck> d1, ArrayList<Duck> d2){
        for (int i=0; i< d1.size(); i++){
            if(!d1.get(i).equals(d2.get(i))){
                return false;
            }}
            return true;
            
    }

    public static boolean doesContain(ArrayList<myNode> L1, myNode n2){
        for (int i = 0; i<L1.size(); i++){
            if(checkDucks(L1.get(i).getDucks(), n2.getDucks()) && L1.get(i).getParent().equals(n2.getParent()) && L1.get(i).getPastCost() == n2.getPastCost()){
                return false;
            }
        }
        return true;
    }

    public static myNode search(int numDucks, int numPos, int flagDuck, int maxEnergy){
        ArrayList<myNode> reached = new ArrayList<myNode>();
        ArrayList<Duck> allDucks = new ArrayList<Duck>();
        int i = 0;
        Duck newDuck = new Duck(0, maxEnergy, maxEnergy, false);
        while (i <numDucks){
            allDucks.add(newDuck);
            i++;
        }
        int cost = 0;
        myNode initNode = new myNode(allDucks,null, cost); // node <--Node(problem.initial)
        if (initNode.isGoalNode(flagDuck, initNode.getDucks())){
            System.out.println("Goal found");
            return initNode;
            
        }
        //System.out.println(initNode.toString());
        Queue<myNode> frontier = new LinkedList<myNode>(); //frontier (FIFO queue) with node as element
        myNode clone = initNode.cloneNode(initNode);
        reached.add(clone);
        //reached<-- problem.initial
        frontier.add(clone);
        //System.out.println(frontier.toString()+ " is frontier at line 50");
        //int j = 0;
        while(!frontier.isEmpty()){
            myNode newNode = frontier.poll();
            //System.out.println(frontier+" is frontier at line 54");
           // myNode newNode1 = newNode.cloneNode(newNode);
            System.out.println(newNode.toString());
            //j++;

            for (myNode x: newNode.expand(newNode, newNode.getDucks(), numPos, flagDuck)){
                if (x.isGoalNode(flagDuck, x.getDucks())){
                    return x;
                }
                if(doesContain(reached, x)){
                    reached.add(x);
                    frontier.add(x);
                }
            }

            
        }
        //System.out.println(j);
        return null;

    }
    public static void main(String[]args){
        myNode x = search(2, 2, 0, 4);
        if(x == null){
            System.out.println("No solution found");
        }
        else{
            System.out.println("SOLUTION FOUND");
            System.out.println(x.toString());
        }


    }
}
