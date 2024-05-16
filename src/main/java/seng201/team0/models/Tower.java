package seng201.team0.models;

import java.util.List;

public class Tower implements Purchasable{

    // attributes
    private final int gameBonus;
    private int towerLevel;
    private int consecutiveTowerUses;
    private float breakChance = 0;
    private String towerName;
    private String towerType;
    private float towerSpeed;
    private int towerFillAmount;
    private boolean towerIsBroken = false;

    private boolean towerIsSelected = false;

    private int buyPrice;
    private int sellPrice;

    // constructor
    public Tower(String towerName, String towerType, float towerSpeed, int towerFillAmount, int buyPrice, int gameBonus){
        this.towerName = towerName;
        this.towerType = towerType;
        this.towerSpeed = towerSpeed;
        this.towerFillAmount = towerFillAmount;
        this.buyPrice = buyPrice;
        this.gameBonus = gameBonus;
    }

    // methods
    @Override
    public int getSellPrice(){
        return (int) buyPrice * gameBonus; // This currently increases the sell price as gameBonus < 1
    }

    @Override
    public int setSellPrice() {
        return 0;
    }

    @Override
    public int getBuyPrice() {
        return 0;
    }

    @Override
    public int setBuyPrice(int cost) {
        return 0;
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
        return towerFillAmount;
    }

    public void setTowerAmount(int towerAmount) {
        this.towerFillAmount = towerAmount;
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

    public int getConsecutiveTowerUses() {
        return consecutiveTowerUses;
    }

    public void setConsecutiveTowerUses(int consecutiveTowerUses) {
        this.consecutiveTowerUses = consecutiveTowerUses;
    }

    public int getTowerLevel() {
        return towerLevel;
    }

    public void setTowerLevel(int towerLevel) {
        this.towerLevel = towerLevel;
    }
}
