import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class node {
    
    private ArrayList<Duck> ducks;
    private node parent;
    private int pastCost;

    public ArrayList<Duck> getDucks() {
        return this.ducks;
    }

    public void setDucks(ArrayList<Duck> ducks) {
        this.ducks = ducks;
    }

    public node getParent() {
        return this.parent;
    }

    public void setParent(node parent) {
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




    
}
