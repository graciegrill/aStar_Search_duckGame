import java.util.Objects;

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

    public void moveDuck(int direction){ //one move at a time
        if(direction == 1 && this.energy>=1){
            this.pos = this.pos + 1;
            this.energy = this.energy - 1;
        }
        else if(direction == -1 && this.energy>=1){
            this.pos = this.pos + 1;
            this.energy = this.energy - 1;

        }
    }

    public void transferEnergy(Duck d0){ // one unit at a time
        if(this.energy>0 && d0.getEnergy() != d0.getMaxEnergy()){
            d0.setEnergy(d0.getEnergy()+1);
            this.energy = this.energy - 1;
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

    @Override
    public boolean equals(Object d1){
        Duck random = (Duck)d1;
        return this.getEnergy() == random.getEnergy() && this.getPos() == random.getPos() && this.getFlagCap() == random.getFlagCap();
    }
    
    
    public Duck cloneDuck(){
        return new Duck(this.pos, this.energy, this.maxEnergy, this.flagCap);
    }

    public void pickUpFlag(int max){
        if(this.pos == max - 1){
            this.setFlagCap(true);
        }
    }
    

    


    
    
}
