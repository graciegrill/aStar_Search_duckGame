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
        Duck newDuck = new Duck(0, 0 , maxEnergy, false);
        Duck goalDuck = new Duck(0, 0, maxEnergy, true);
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
            return initNode;
            
        }
        Queue<myNode> frontier = new PriorityQueue<myNode>(); //frontier (FIFO queue) with node as element
        reached.add(initNode);
        //reached<-- problem.initial
        frontier.add(initNode);
        System.out.println(frontier.toString());
        while(!frontier.isEmpty()){
            myNode newNode = frontier.peek();
            for (myNode x: newNode.expand(newNode.getDucks(), numPos)){
                if (x.isGoalNode(flagDuck, numPos)){
                    return x;
                }
                else{
                    reached.add(x);
                    frontier.add(x);
                }
            }

            
        }
        return initNode;

    }
    public static void main(String[]args){
        System.out.println(search(4,5,1,4));


    }
}
