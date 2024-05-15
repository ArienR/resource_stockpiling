package seng201.team0.models;

public class Tower {

    // attributes
    private final int gameBonus;
    private float breakChance = 0;
    private String towerName;
    private String towerType;
    private float towerSpeed;
    private int towerAmount;
    private boolean towerIsBroken = false;

    private boolean towerIsSelected = false;

    private int buyPrice;
    private int sellPrice;

    // constructor
    public Tower(String towerName, String towerType, float towerSpeed, int towerAmount, int buyPrice, int gameBonus){
        this.towerName = towerName;
        this.towerType = towerType;
        this.towerSpeed = towerSpeed;
        this.towerAmount = towerAmount;
        this.buyPrice = buyPrice;
        this.gameBonus = gameBonus;
    }

    // methods
    public int getSellPrice(){
        return (int) buyPrice/gameBonus;
    }

    //getter and setters
    public float getBreakChance() {
        return breakChance;
    }

    public void setBreakChance(float breakChance) {
        this.breakChance = breakChance;
    }

    public String getTowerName() {
        return towerName;
    }

    public void setTowerName(String towerName) {
        this.towerName = towerName;
    }

    public String getTowerType() {
        return towerType;
    }

    public void setTowerType(String towerType) {
        this.towerType = towerType;
    }

    public float getTowerSpeed() {
        return towerSpeed;
    }

    public void setTowerSpeed(float towerSpeed) {
        this.towerSpeed = towerSpeed;
    }

    public int getTowerAmount() {
        return towerAmount;
    }

    public void setTowerAmount(int towerAmount) {
        this.towerAmount = towerAmount;
    }

    public boolean isTowerIsBroken() {
        return towerIsBroken;
    }

    public void setTowerIsBroken(boolean towerIsBroken) {
        this.towerIsBroken = towerIsBroken;
    }

    public boolean isTowerIsSelected() {
        return towerIsSelected;
    }

    public void setTowerIsSelected(boolean towerIsSelected) {
        this.towerIsSelected = towerIsSelected;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }
}
