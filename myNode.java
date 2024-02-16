import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class myNode {
    
    private ArrayList<Duck> ducks;
    private int parent;
    private int pastCost;


    public myNode(ArrayList<Duck> ducks, int parent, int pastCost) {
        this.ducks = ducks;
        this.parent = parent;
        this.pastCost = pastCost;
    }
    

    public ArrayList<Duck> getDucks() {
        return this.ducks;
    }

    public void setDucks(ArrayList<Duck> ducks) {
        this.ducks = ducks;
    }

    public int getParent() {
        return this.parent;
    }

    public void setParent(int parent) {
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
        n1 = new myNode(ducks, parent, pastCost)
        return 
    }





    
}
