import java.util.ArrayList;
import java.util.PriorityQueue;

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

    public boolean isGoalNode(int flagCap, int totalPositions){
        if(this.ducks.get(flagCap).getPos() == totalPositions){
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

    public Boolean equals(myNode n1){
        n1 = new myNode(ducks, parent, pastCost);
        return getDucks().equals(n1.getDucks()) && getParent() == n1.getParent() && getPastCost() == n1.getPastCost();
    }

    public int totalEnergy(ArrayList<Duck> duckyMcDuckFace){
        int e = 0;
        for (Duck d: duckyMcDuckFace){
            e+=d.getEnergy();
        }
        return e;
    }

     /*
     * Need to fill in 
     * A duck can:
     *  -move left once (+1)
     *  -move right once (-1)
     *  -transfer 1 energy to duckAt(i+1)
     *  -transfer 1 energy to duckAt(i-1)
     */
    public PriorityQueue<myNode> expand(ArrayList<Duck> nodeDucks, int numberPos){
        PriorityQueue<myNode> newNodes = new PriorityQueue<myNode>();
        for(int i = 0; i<nodeDucks.size(); i++){

            ArrayList<Duck> leftNodeDucks = new ArrayList<Duck>(nodeDucks);
            if(leftNodeDucks.get(i).getEnergy()> 0 && leftNodeDucks.get(i).getPos() < numberPos){
                leftNodeDucks.get(i).setPos(leftNodeDucks.get(i).getPos() +1);
                leftNodeDucks.get(i).setEnergy(leftNodeDucks.get(i).getEnergy() -1);
                if(totalEnergy(leftNodeDucks) >= numberPos){
                    newNodes.add(new myNode(leftNodeDucks, this, 0));
                }
            }
            if(leftNodeDucks.get(i).getEnergy()> 0 && leftNodeDucks.get(i).getPos() > 0 ){
                ArrayList<Duck> rightNodeDucks = new ArrayList<Duck>(nodeDucks);
                rightNodeDucks.get(i).setPos(rightNodeDucks.get(i).getPos() -1);
                rightNodeDucks.get(i).setEnergy(rightNodeDucks.get(i).getEnergy() -1);
                if(totalEnergy(rightNodeDucks) >= numberPos){
                    newNodes.add(new myNode(rightNodeDucks, this, 0));
                }
            }

            ArrayList<Duck> transferUp = new ArrayList<Duck>(nodeDucks);
            if(i> 0 && transferUp.get(i).getPos() == transferUp.get(i-1).getPos() && transferUp.get(i).getPos()>0 && transferUp.get(i-1).getEnergy() < transferUp.get(i-1).getMaxEnergy()){
                transferUp.get(i).setEnergy(transferUp.get(i).getEnergy() -1);
                transferUp.get(i-1).setEnergy(transferUp.get(i-1).getEnergy() +1);
                if(totalEnergy(transferUp) >= numberPos){
                    newNodes.add(new myNode(transferUp, this, 0));
                }
            }

            ArrayList<Duck> transferDown = new ArrayList<Duck>(nodeDucks);
            if( i < transferDown.size() -1 && transferDown.get(i).getPos() == transferDown.get(i+1).getPos() && transferDown.get(i).getPos()>0 && transferDown.get(i-1).getEnergy() < transferDown.get(i-1).getMaxEnergy()){
                transferDown.get(i).setEnergy(transferDown.get(i).getEnergy() -1);
                transferDown.get(i+1).setEnergy(transferDown.get(i+1).getEnergy() +1);
                if(totalEnergy(transferDown) >= numberPos){
                    newNodes.add(new myNode(transferDown, this, 0));
                }
            }
        }


        return newNodes;
    }






    
}
