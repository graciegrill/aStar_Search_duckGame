import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

public class firstDraft{
    
    public static myNode search(int numDucks, int numPos, int flagDuck, int maxEnergy){
        ArrayList<myNode> reached = new ArrayList<myNode>();
        ArrayList<Duck> allDucks = new ArrayList<Duck>();
        int i = 0;
        Duck newDuck = new Duck(0, maxEnergy, maxEnergy, false);
        Duck goalDuck = new Duck(0, maxEnergy, maxEnergy, true);
        while (i <numDucks){
            if(i!=flagDuck){
                allDucks.add(newDuck);
            }
            else{
                allDucks.add(goalDuck);
            }
            i++;
        }
        int cost = 0;
        myNode initNode = new myNode(allDucks,null, cost); // node <--Node(problem.initial)
        if (initNode.isGoalNode(flagDuck, numPos)){
            System.out.println("Goal found");
            return initNode;
            
        }
        //System.out.println(initNode.toString());
        Queue<myNode> frontier = new LinkedList<myNode>(); //frontier (FIFO queue) with node as element
        myNode clone = initNode.cloneNode(initNode);
        reached.add(clone);
        //reached<-- problem.initial
        frontier.add(clone);
        //System.out.println(frontier.toString());
        //System.out.println(frontier.toString());
        //int j = 0;
        while(!frontier.isEmpty()){
            myNode newNode = frontier.poll();
            myNode newNode1 = newNode.cloneNode(newNode);
            //System.out.println(newNode.toString());
            //j++;

            for (myNode x: newNode.expand(newNode1, newNode1.getDucks(), numPos)){
                if (x.isGoalNode(flagDuck, numPos)){
                    return x;
                }
                if(!reached.contains(x)){
                    reached.add(x);
                    frontier.add(x);
                }
            }

            
        }
        //System.out.println(j);
        return null;

    }
    public static void main(String[]args){
        myNode x = search(4, 5, 1, 4);
        if(x == null){
            System.out.println("No solution found");
        }
        else{
            System.out.println(x.toString());
        }


    }
}
