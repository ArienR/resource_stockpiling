package seng201.team0.models;

public abstract class Tower implements Purchasable {

    // attributes
    private final int difficultyBonus;
    private int towerLevel;
    private int consecutiveTowerUses;
    private float breakChance = 0;
    private String towerName;
    private String towerType;
    private int towerSpeed;
    private int towerFillAmount;
    private boolean towerBroken = false;

    private boolean towerSelected = false;

    private int buyPrice;
    private int sellPrice;

    // constructor
    public Tower(int towerSpeed, int towerFillAmount, int buyPrice, int difficultyBonus) {
        this.towerSpeed = towerSpeed;
        this.towerFillAmount = towerFillAmount;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.round(difficultyBonus / (float) buyPrice);
        this.difficultyBonus = difficultyBonus;
        this.towerName = generateRandomName();
    }

    // methods
    protected abstract String generateRandomName();
    @Override
    public int getSellPrice(){
        return sellPrice;
    }

    @Override
    public int setSellPrice(int cost) {
        return cost / difficultyBonus;
    }

    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    @Override
    public void setBuyPrice(int cost) {
        this.buyPrice = cost;
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

    public int getTowerSpeed() {
        return towerSpeed;
    }

    public void setTowerSpeed(int towerSpeed) {
        this.towerSpeed = towerSpeed;
    }

    public int getTowerFillAmount() {
        return towerFillAmount;
    }

    public void setTowerFillAmount(int towerAmount) {
        this.towerFillAmount = towerAmount;
    }

    public boolean isTowerBroken() {
        return towerBroken;
    }

    public void setTowerIsBroken(boolean towerIsBroken) {
        this.towerBroken = towerIsBroken;
    }

    public boolean isTowerSelected() {
        return towerSelected;
    }

    public void setTowerIsSelected(boolean towerIsSelected) {
        this.towerSelected = towerIsSelected;
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
