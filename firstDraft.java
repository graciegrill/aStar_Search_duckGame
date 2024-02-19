import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

public class firstDraft{
    ArrayList<myNode> reached = new ArrayList<myNode>();


    /*
     * Need to fill in 
     * A duck can:
     *  -move left once
     *  -move right once
     *  -transfer 1 energy to duckAt(i+1)
     *  -transfer 1 energy to duckAt(i-1)
     */
    public myNode expand(Duck sampleDuck, Duck targetDuck){
        return new myNode();
    }



    
    public myNode search(int numDucks, int numPos, int flagDuck, int maxEnergy){
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
        myNode initNode = new myNode(allDucks, null, cost); // node <--Node(problem.initial)
        if (initNode.isGoalNode(flagDuck, numPos)){
            return initNode;
            
        }
        Queue<myNode> frontier = new PriorityQueue<myNode>(); //frontier (FIFO queue) with node as element
        reached.add(initNode);
        //reached<-- problem.initial
        frontier.add(initNode);
        while(!frontier.isEmpty()){
            myNode newNode = frontier.peek();

            
        }
        return initNode;

    }


    public static void main(String[]args){
        System.out.println("Enter your input: ");
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        in.close();


    }
}
