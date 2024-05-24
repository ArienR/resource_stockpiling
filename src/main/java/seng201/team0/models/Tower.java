package seng201.team0.models;

/**
 * Represents a generic Tower in the game, providing a foundation for specific types of towers.
 * This abstract class has attributes that are common to all towers, such as level, speed, and operational status.
 */
public abstract class Tower implements Purchasable {

    /**
     * The current level of the tower.
     */
    private int towerLevel;

    /**
     * How many times the tower has been used consecutively.
     */
    private int consecutiveUses = 0;

    /**
     * The tower level to display on the after round screen.
     */
    private String towerTableLevel = "1";

    /**
     * How many times the tower has not been used consecutively.
     */
    private int consecutiveNonUses = 0;

    /**
     * The name of the tower.
     */
    private String towerName;

    /**
     * The speed at which the tower fills.
     */
    private int towerSpeed;

    /**
     * The fill amount for each fill.
     */
    private int towerFillAmount;

    /**
     * The tower broken status.
     */
    private boolean towerBroken = false;

    /**
     * The cost to buy the tower.
     */
    private int buyPrice;

    /**
     * The price at which the tower sells for.
     */
    private int sellPrice;

    /**
     * Constructs a new Tower with specified parameters.
     *
     * @param towerSpeed      The speed at which the tower operates.
     * @param towerFillAmount The amount of product the tower can fill.
     * @param buyPrice        The cost to purchase the tower.
     * @param difficultyBonus The difficulty multiplier affecting sell price.
     * @param towerLevel      The initial level of the tower.
     */
    public Tower(int towerSpeed, int towerFillAmount, int buyPrice, float difficultyBonus, int towerLevel) {
        this.towerSpeed = towerSpeed;
        this.towerFillAmount = towerFillAmount;
        this.buyPrice = buyPrice;
        this.sellPrice = Math.round(this.buyPrice * difficultyBonus);
        this.towerName = generateRandomName();
        this.consecutiveUses = 0;
        this.towerLevel = towerLevel;
    }

    /**
     * Empty constructor used in certain scenarios where a full tower object is not required.
     */
    public Tower() {} // Empty super constructor to store typeAffected for an item

    /**
     * Generates a random name for the tower. Must be implemented by subclasses.
     *
     * @return A randomly generated name for the tower.
     */
    protected abstract String generateRandomName();

    /**
     * Returns the sell price of the tower, taking into account any difficulty bonuses.
     *
     * @return The sell price of the tower.
     */
    @Override
    public int getSellPrice(){
        return sellPrice;
    }

    /**
     * Returns the purchase price of the tower.
     *
     * @return The buy price of the tower.
     */
    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    /**
     * Returns the name of the tower.
     *
     * @return The name of the tower.
     */
    public String getTowerName() {
        return towerName;
    }

    /**
     * Returns the operational speed of the tower.
     *
     * @return The speed of the tower.
     */
    public int getTowerSpeed() {
        return towerSpeed;
    }

    /**
     * Returns the fill amount of the tower.
     *
     * @return The fill amount the tower can handle.
     */
    public int getTowerFillAmount() {
        return towerFillAmount;
    }

    /**
     * Checks if the tower is currently broken.
     *
     * @return true if the tower is broken, false otherwise.
     */
    public boolean isTowerBroken() {
        return towerBroken;
    }

    /**
     * Sets the operational status of the tower to broken or fixed.
     *
     * @param towerIsBroken true if the tower is to be marked as broken, false otherwise.
     */
    public void setTowerIsBroken(boolean towerIsBroken) {
        this.towerBroken = towerIsBroken;
    }

    /**
     * Returns the number of consecutive uses of the tower.
     *
     * @return The count of consecutive uses.
     */
    public int getConsecutiveUses() {
        return consecutiveUses;
    }

    /**
     * Increments the count of consecutive uses of the tower.
     */
    public void incrementConsecutiveUses() {
        this.consecutiveUses += 1;
    }

    /**
     * Resets the count of consecutive uses of the tower to zero.
     */
    public void wipeConsecutiveUses() {
        this.consecutiveUses = 0;
    }

    /**
     * Returns the number of consecutive rounds in which the tower was not used.
     *
     * @return The count of consecutive non-uses.
     */
    public int getConsecutiveNonUses() {
        return consecutiveNonUses;
    }


    /**
     * Increments the count of consecutive non-uses of the tower.
     */
    public void incrementConsecutiveNonUses() {
        this.consecutiveNonUses += 1;
    }

    /**
     * Resets the count of consecutive non-uses of the tower to zero.
     */
    public void wipeConsecutiveNonUses() {
        this.consecutiveNonUses = 0;
    }

    /**
     * Returns the current level of the tower.
     *
     * @return The level of the tower.
     */
    public int getTowerLevel() {
        return towerLevel;
    }

    /**
     * Increments the level of the tower by one.
     */
    public void incrementTowerLevel(){
        this.towerLevel += 1;
    }

    /**
     * Decreases the level of the tower by one if it is greater than 1.
     */
    public void decreaseTowerLevel(){
        if (towerLevel > 1) {
            this.towerLevel -= 1;
        }
    }

    /**
     * Returns the status of the tower as either "Broken" or "Operational".
     *
     * @return The operational status of the tower.
     */
    public String getTowerStatus() {
        return towerBroken ? "Broken" : "Operational";
    }

    /**
     * Returns the current level status of the tower for display in tables.
     *
     * @return The formatted string showing the tower level change.
     */
    public String getTowerTableLevel() {
        return towerTableLevel;
    }

    /**
     * Sets the level status of the tower for display purposes.
     *
     * @param towerTableLevel The formatted string showing the tower level change.
     */
    public void setTowerTableLevel(String towerTableLevel) {
        this.towerTableLevel = towerTableLevel;
    }
}
