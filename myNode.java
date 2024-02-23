import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class myNode {
    
    private ArrayList<Duck> ducks;
    private myNode parent;
    private int pastCost;


    


    public myNode(ArrayList<Duck> ducks, myNode parent, int pastCost) {
        this.ducks = ducks;
        this.parent = parent ;
        this.pastCost = pastCost;
        
    }

    public myNode(){
        this.ducks = new ArrayList<Duck>();
        this.parent = this;
        this.pastCost = 0;
    }
    

    public ArrayList<Duck> getDucks() {
        return this.ducks;
    }

    public void setDucks(ArrayList<Duck> ducks) {
        this.ducks = ducks;
    }

    public myNode getParent() {
        return this.parent;
    }

    public void setParent(myNode parent) {
        this.parent = parent;
    }

    public int getPastCost() {
        return this.pastCost;
    }

    public void setPastCost(int pastCost) {
        this.pastCost = pastCost;
    }

    /**
     * Checks to see if all the ducks are back at the beginning
     * @param d: ArrayList of ducks
     * @return false if they are not all back at the beginning, true if they are
     */
    public boolean checkDuck0Pos(ArrayList<Duck> d){
        for (int i=0; i< d.size(); i++){
            if(d.get(i).getPos() != 0){
                return false;
            }}
            return true;
    }
    /**
     * checks if the node is a goal node
     * @param flag: Checks if the duck that can get the flag has the flag
     * @param d: ArrayList of ducks
     * @return true if it is a goal node, false if not
     */
    public boolean isGoalNode(int flag, ArrayList<Duck> d){
        if(checkDuck0Pos(d) && this.ducks.get(flag).getFlagCap()){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public String toString() {
        return "{" +
            " ducks='" + getDucks() + "'" +
            ", parent='" + getParent() + "'" +
            ", pastCost='" + getPastCost() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object n1){
        myNode random = (myNode)n1;
        return checkDucks(this.getDucks(), random.getDucks()) && this.getParent() == random.getParent() && this.getPastCost() == random.getPastCost();
    }
    public boolean checkDucks(ArrayList<Duck> d1, ArrayList<Duck> d2){
        for (int i=0; i< d1.size(); i++){
            if(!d1.get(i).equals(d2.get(i))){
                return false;
            }}
            return true;
            
    }

    /**
     * Checks the total energy in the state
     * @param duckyMcDuckFace - ArrayList of ducks -> state
     * @return e as the total energy
     */
    public int totalEnergy(ArrayList<Duck> duckyMcDuckFace){
        int e = 0;
        for (Duck d: duckyMcDuckFace){
            e+=d.getEnergy();
        }
        return e;
    }
    /**
     * Clones the duck array
     * @param d3: ArrayList of ducks
     * @return a cloned ArrayList of ducks
     */
    public ArrayList<Duck> cloneDucks(ArrayList<Duck> d3){
        ArrayList<Duck> clonedDucks = new ArrayList<Duck>();
        for (Duck d: d3){
            clonedDucks.add(d.cloneDuck());
        }
        return clonedDucks;
    }

    /**
     * clones a node
     * @param n0 - the node to be cloned
     * @return n1, the cloned node
     */
    public myNode cloneNode(myNode n0){
        myNode n1 = new myNode();
        n1.setDucks(cloneDucks(n0.getDucks()));
        n1.setParent(n0.getParent());
        n1.setPastCost(n0.getPastCost());
        return n1;
    }
    /**
     * Calculates the total energy needed to get to the end from a particular state
     * @param d: ArrayList of ducks
     * @param flagDuckDistance: int indicating which duck can get the flag
     * @param maxDistance: the total number of positions
     * @return: sum as the energy needed.
     */
    public int energyNeeded(ArrayList<Duck> d, int flagDuckDistance, int maxDistance){
        int sum = 0;
        int i = 0;
        for(Duck x: d){
            if(i == flagDuckDistance && x.getFlagCap() == false){
                sum+=(Math.abs((maxDistance - 1) - x.getPos())*2)+x.getPos();
            }
            else{
                sum+=x.getPos();
            }
            i++;
        }
        return sum;
    }

     
    /**
     * Calculates the heuristic as energy needed + the number of past moves
     * @param n0 - myNode
     * @param i - which duck is the flag duck
     * @param j - the number of positions
     * @return h which is the heuristic
     */
     public int heuristic(myNode n0, int i, int j){
        int h = 0;
        h+=energyNeeded(n0.getDucks(),i , j);
        h+=n0.getPastCost();
        return h;
    }
    /**
     * Expands the node
     * @param n0: node to be expanded
     * @param nodeDucks: ArrayList of ducks
     * @param numberPos: total number of positions
     * @param flagNum: the index of the flag duck
     * @return: Queue of new nodes
     */
    public Queue<myNode> expand(myNode n0, ArrayList<Duck> nodeDucks, int numberPos, int flagNum){
        Queue<myNode> newNodes = new PriorityQueue<>(new Comparator<myNode>() {
    @Override
    public int compare(myNode o1, myNode o2) {
        return Integer.compare(o1.heuristic(o1, o1.getDucks().get(flagNum).getPos(), numberPos), o2.heuristic(o2, o2.getDucks().get(flagNum).getPos(), numberPos));
    }
});
        for(int i = 0; i<nodeDucks.size(); i++){

            ArrayList<Duck> leftNodeDucks = cloneDucks(nodeDucks);
            if(leftNodeDucks.get(i).getEnergy()> 0 && leftNodeDucks.get(i).getPos() < numberPos){
                leftNodeDucks.get(i).setPos(leftNodeDucks.get(i).getPos() +1);
                leftNodeDucks.get(i).setEnergy(leftNodeDucks.get(i).getEnergy() -1);
                if(i == flagNum){
                    leftNodeDucks.get(i).pickUpFlag(numberPos);
                }
                if(totalEnergy(leftNodeDucks) >= energyNeeded(leftNodeDucks,flagNum, numberPos-1)){
                    newNodes.add(new myNode(leftNodeDucks, n0, n0.getPastCost()+1));
                }
            }
            ArrayList<Duck> rightNodeDucks = cloneDucks(nodeDucks);
            if(rightNodeDucks.get(i).getEnergy()> 0 && rightNodeDucks.get(i).getPos() > 0 ){
                //ArrayList<Duck> rightNodeDucks = new ArrayList<Duck>(nodeDucks);
                rightNodeDucks.get(i).setPos(rightNodeDucks.get(i).getPos() -1);
                rightNodeDucks.get(i).setEnergy(rightNodeDucks.get(i).getEnergy() -1);
                if(totalEnergy(rightNodeDucks) >= energyNeeded(rightNodeDucks, flagNum, numberPos-1)){
                    newNodes.add(new myNode(rightNodeDucks, n0, n0.getPastCost()+1));
                }
            }

            ArrayList<Duck> transferUp = cloneDucks(nodeDucks);
            if(i> 0 && transferUp.get(i).getPos() == transferUp.get(i-1).getPos() && transferUp.get(i).getEnergy()>0 && transferUp.get(i-1).getEnergy() < transferUp.get(i-1).getMaxEnergy()){
                transferUp.get(i).setEnergy(transferUp.get(i).getEnergy() -1);
                transferUp.get(i-1).setEnergy(transferUp.get(i-1).getEnergy() +1);
                if(totalEnergy(transferUp) >= energyNeeded(transferUp, flagNum, numberPos-1)){
                    newNodes.add(new myNode(transferUp, n0, n0.getPastCost()+1));
                }
            }

            ArrayList<Duck> transferDown = cloneDucks(nodeDucks);
            if( i < transferDown.size() -1 && transferDown.get(i).getPos() == transferDown.get(i+1).getPos() && transferDown.get(i).getEnergy()>0 && transferDown.get(i+1).getEnergy() < transferDown.get(i+1).getMaxEnergy()){
                transferDown.get(i).setEnergy(transferDown.get(i).getEnergy() -1);
                transferDown.get(i+1).setEnergy(transferDown.get(i+1).getEnergy() +1);
                if(totalEnergy(transferDown) >= energyNeeded(transferDown, flagNum, numberPos-1)){
                    newNodes.add(new myNode(transferDown, n0, n0.getPastCost()+1));
                }
            }
        }

        if (newNodes.size() > 0){
            //System.out.println(newNodes.size());
            //System.out.println(newNodes.toString());
        }
        return newNodes;
    }

    






    
}
