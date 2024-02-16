public class Duck {

    private int pos;
    private int energy;
    private Boolean flagCap;
    
    public Duck(){
        this.pos = 0;
        this.energy = 0; 
        this.flagCap = true;
    
    }

    public Duck(int pos, int energy, Boolean flagCap){
        this.pos = pos;
        this.energy = energy;
        this.flagCap = flagCap;
    }

    public int getPos() {
        return this.pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Boolean isFlagCap() {
        return this.flagCap;
    }

    public Boolean getFlagCap() {
        return this.flagCap;
    }

    public void setFlagCap(Boolean flagCap) {
        this.flagCap = flagCap;
    }

    @Override
    public String toString() {
        return "{" +
            " pos='" + getPos() + "'" +
            ", energy='" + getEnergy() + "'" +
            ", flagCap='" + isFlagCap() + "'" +
            "}";
    }
    

    


    
    
}
