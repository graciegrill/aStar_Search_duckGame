import java.util.Queue;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class firstDraft{

    /**
     * Checks if ducks are the
     * @param d1: An array list of ducks
     * @param d2: Another array list of ducks
     * @return true if the ducks are the same, returns false if they are not
     */
    public static boolean checkDucks(ArrayList<Duck> d1, ArrayList<Duck> d2){
        for (int i=0; i< d1.size(); i++){
            if(!d1.get(i).equals(d2.get(i))){
                return false;
            }}
            return true;
            
    }
    /**
     * Checks if a state is in.
     * @param k: Hashmap of arrayList ducks and myNodes
     * @param n: ArrayList of ducks 
     * @return true if the state is in the hashmap and false it's not
     */
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

    /**
     * This is the A* search.
     * @param numDucks: Number of ducks
     * @param numPos: Number of positions
     * @param flagDuck: Which duck is the flag duck
     * @param maxEnergy: The maximum energy
     * @return myNode for the solution or null for failure
     */
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
        Queue<myNode> frontier = new PriorityQueue<myNode>(new Comparator<myNode>() {
    @Override
    public int compare(myNode o1, myNode o2) {
        return Integer.compare(o1.heuristic(o1, o1.getDucks().get(flagDuck).getPos(), numPos), o2.heuristic(o2, o2.getDucks().get(flagDuck).getPos(), numPos));
    }
}); 
        myNode clone = initNode.cloneNode(initNode);
        reached.put(clone.getDucks(), clone);
        frontier.add(clone);
        while(!frontier.isEmpty()){
            myNode newNode = frontier.poll();
            for (myNode x: newNode.expand(newNode, newNode.getDucks(), numPos, flagDuck)){
                if (x.isGoalNode(flagDuck, x.getDucks())){
                    return x;
                }
                if(doesContain(reached, x.getDucks())  == false){
                    reached.put(x.getDucks(), x);
                    frontier.add(x);
                }
            }

            
        }
        return null;

    }

    public static String generateMovementSequence(myNode x) {
        StringBuilder sequence = new StringBuilder();
        myNode currentNode = x;
        while (currentNode != null && currentNode.getParent() != null) {
            myNode parentNode = currentNode.getParent();
            for (int i = 0; i < currentNode.getDucks().size(); i++) {
                Duck currentDuck = currentNode.getDucks().get(i);
                Duck parentDuck = parentNode.getDucks().get(i);
                if (currentDuck.getPos()!=(parentDuck.getPos())) {
                    if (currentDuck.getPos() < parentDuck.getPos()) {
                        sequence.insert(0, "R" + i + " ");
                    } else {
                        sequence.insert(0, "L" + i + " ");
                    }
                } else if (currentDuck.getEnergy() > parentDuck.getEnergy() && currentDuck.getPos() == (parentDuck.getPos())) {
                    // Transfer energy
                    currentDuck.setEnergy(currentDuck.getEnergy() + parentDuck.getEnergy());
                    parentDuck.setEnergy(0);
                    for (int j = 0; j < currentNode.getDucks().size(); j++) {
                        Duck currentDuck1 = currentNode.getDucks().get(j);
                        Duck parentDuck1 = parentNode.getDucks().get(j);
                        if(currentDuck1.getEnergy() < parentDuck1.getEnergy()){
                            sequence.insert(0, "T" + j + "->" + i + " ");

                        }
                    }
                }
            }
            currentNode = parentNode;
        }
        return sequence.toString().trim();
    }
    
    public static void main(String[]args){
        Scanner scanner =new Scanner (System.in);
        System.out.print("Enter the number of ducks: ");
        int numDucks = scanner.nextInt();
        System.out.print("Enter the number of positions: ");
        int numPos = scanner.nextInt();
        System.out.print("Enter the flag duck: ");
        int flagDuck = scanner.nextInt();
        System.out.print("Enter the maximum energy: ");
        int maxEnergy = scanner.nextInt();
        myNode x = search(numDucks, numPos, flagDuck, maxEnergy);
        if(x == null){
            System.out.println("No solution found");
        }
        else{
            System.out.println("SOLUTION FOUND");
            System.out.println(generateMovementSequence(x));
        }
        scanner.close();


    }
}