package seng201.team0.models;

public abstract class Tower implements Purchasable {

    private int towerLevel;
    private int consecutiveUses = 0;
    private String towerTableLevel = "1";
    private int consecutiveNonUses = 0;
    private String towerName;
    private String towerType;
    private int towerSpeed;
    private int towerFillAmount;
    private boolean towerBroken = false;
    private boolean towerSelected = false;
    private int buyPrice;
    private int sellPrice; // This logic is done in the shop as we have difficulty bonus, is this redundant?

    // constructor
    public Tower(int towerSpeed, int towerFillAmount, int buyPrice, float difficultyBonus) {
        this.towerSpeed = towerSpeed;
        this.towerFillAmount = towerFillAmount;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.round(this.buyPrice * difficultyBonus);
        this.towerName = generateRandomName();
        this.consecutiveUses = 0;
        this.towerLevel = 1;
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
