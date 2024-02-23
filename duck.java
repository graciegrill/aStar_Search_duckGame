
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
        if(energy>this.maxEnergy){
            System.out.println("ERROR");
            System.exit(0);
        }
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
    /**
     * Moves a duck left or right.
     * @param direction indicates direction of movement
     */
    public void moveDuck(int direction){ 
        if(direction == 1 && this.energy>=1){
            this.pos = this.pos + 1;
            this.energy = this.energy - 1;
        }
        else if(direction == -1 && this.energy>=1){
            this.pos = this.pos + 1;
            this.energy = this.energy - 1;

        }
    }
    /**
     * Transfers energy between ducks
     * @param d0 - the duck to transfer energy to
     */
    public void transferEnergy(Duck d0){ 
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
    
    /**
     * Clones this duck and returns a new duck.
     * @return a new cloned duck.
     */
    public Duck cloneDuck(){
        return new Duck(this.pos, this.energy, this.maxEnergy, this.flagCap);
    }
    /**
     * Registers the flag pick up
     * @param max: the max position; indicative of picking up the flag
     */
    public void pickUpFlag(int max){
        if(this.pos == max - 1){
            this.setFlagCap(true);
        }
    }
    
    

    


    
    
}
