package seng201.team0.models;

public abstract class Tower implements Purchasable {

    private int towerLevel;
    private int consecutiveUses = 0;
    private String towerTableLevel = "1";
    private int consecutiveNonUses = 0;
    private String towerName;
    private int towerSpeed;
    private int towerFillAmount;
    private boolean towerBroken = false;
    private int buyPrice;
    private int sellPrice;

    // constructor
    public Tower(int towerSpeed, int towerFillAmount, int buyPrice, float difficultyBonus, int towerLevel) {
        this.towerSpeed = towerSpeed;
        this.towerFillAmount = towerFillAmount;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.round(this.buyPrice * difficultyBonus);
        this.towerName = generateRandomName();
        this.consecutiveUses = 0;
        this.towerLevel = towerLevel;
    }

    public Tower() {} // Empty super constructor to store typeAffected for an item

    // methods
    protected abstract String generateRandomName();
    @Override
    public int getSellPrice(){
        return sellPrice;
    }

    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    public String getTowerName() {
        return towerName;
    }

    public int getTowerSpeed() {
        return towerSpeed;
    }

    public int getTowerFillAmount() {
        return towerFillAmount;
    }

    public boolean isTowerBroken() {
        return towerBroken;
    }

    public void setTowerIsBroken(boolean towerIsBroken) {
        this.towerBroken = towerIsBroken;
    }

    public int getConsecutiveUses() {
        return consecutiveUses;
    }

    public void incrementConsecutiveUses() {
        this.consecutiveUses += 1;
    }

    public void wipeConsecutiveUses() {
        this.consecutiveUses = 0;
    }

    public int getConsecutiveNonUses() {
        return consecutiveNonUses;
    }

    public void incrementConsecutiveNonUses() {
        this.consecutiveNonUses += 1;
    }

    public void wipeConsecutiveNonUses() {
        this.consecutiveNonUses = 0;
    }

    public int getTowerLevel() {
        return towerLevel;
    }

    public void incrementTowerLevel(){
        this.towerLevel += 1;
    }

    public void decreaseTowerLevel(){
        this.towerLevel -= 1;
    }

    public String getTowerStatus() {
        return towerBroken ? "Broken" : "Operational";
    }

    public String getTowerTableLevel() {
        return towerTableLevel;
    }

    public void setTowerTableLevel(String towerTableLevel) {
        this.towerTableLevel = towerTableLevel;
    }
}
