public class Duck {

    private int pos;
    private int energy;
    private Boolean flagCap;
    private int maxEnergy;

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }
    
    public Duck(){
        this.pos = 0;
        this.energy = 0;
        this.maxEnergy = 0; 
        this.flagCap = true;
    
    }

    public Duck(int pos, int energy, int maxEnergy, Boolean flagCap){
        this.pos = pos;
        this.energy = energy;
        this.flagCap = flagCap;
        this.maxEnergy = maxEnergy;

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

    public void moveDuck(String direction){
        int numMoves = (int)direction.charAt(2);
        if(direction.startsWith("L")&& this.energy>=numMoves){
            this.pos = this.pos + numMoves;
            this.energy = this.energy - numMoves;
        }
        else if(direction.startsWith("R")&& this.energy>=numMoves){
            this.pos = this.pos + numMoves;
            this.energy = this.energy - numMoves;

        }
    }

    public void transferEnergy(Duck d0){
        if(this.energy>0 && d0.getEnergy() != d0.getMaxEnergy()){
            d0.setEnergy(d0.getEnergy()+this.energy);
            this.energy = 0;
        }
    }

    @Override
    public String toString() {
        return "{" +
            " pos='" + getPos() + "'" +
            ", energy='" + getEnergy() + "'" +
            ", flagCap='" + isFlagCap() + "'" +
            "}";
    }

    public boolean equals(Duck d1){
        d1 = new Duck();
        return getEnergy() == d1.getEnergy() && getPos() == d1.getPos() && getFlagCap() == d1.getFlagCap();
    }
    

    


    
    
}
