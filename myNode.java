import java.util.ArrayList;

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
        this.parent = null;
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





    
}
