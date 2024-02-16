import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Scanner;

public class firstDraft{


    
    public myNode search(int numDucks, int numPos, int flagDuck, int maxEnergy){
        ArrayList<Duck> allDucks = new ArrayList<Duck>();
        int i = 0;
        Duck newDuck = new Duck(0, maxEnergy, false);
        Duck goalDuck = new Duck(0, maxEnergy, true);
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
        myNode initNode = new myNode(allDucks, 0, cost);
        ArrayList<Duck> goalDucks = new ArrayList<Duck>();
        myNode goalNode = new myNode();
        Queue<myNode> frontier = new PriorityQueue<myNode>();
        frontier.add(initNode);


        return initNode;

    }


    public static void main(String[]args){
        System.out.println("Enter your input: ");
        Scanner in = new Scanner(System.in);
        String[] input = in.nextLine().split(" ");
        in.close();


    }
}
