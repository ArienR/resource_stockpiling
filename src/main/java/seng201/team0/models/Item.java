package seng201.team0.models;

import java.util.List;
import java.util.Random;

/**
 * Represents an Item that can be purchased within the game. It will only affect a particular type
 * of tower, boosting one or both of its attributes.
 */
public class Item implements Purchasable {

    /**
     * The list of possible name for an Item.
     */
    private static final List<String> itemNames = List.of("Grocery Galvanizer", "Market Maestro", "Aisle Accelerator",
            "Checkout Champion", "Cart Catalyst", "Stockroom Speedster", "Supermarket Surge",
            "Retail Rocket", "Bountiful Booster", "Fulfillment Fury", "Supply Streamliner",
            "Stockpile Sprinter", "Distribution Dynamo", "Warehouse Whirlwind", "Market Mover",
            "Expedition Enhancer", "Inventory Igniter", "Cart Crusader", "Harvest Helper",
            "Delivery Dynamo", "Provision Propeller", "Cart Commander", "Stock Swift",
            "Vendor Velocity", "Retail Rush"
    );

    /**
     * The name of the item.
     */
    private final String itemName;

    /**
     * The tower type this item will affect within a round.
     */
    private final Tower towerTypeAffected;

    /**
     * The percentage of fill increase the item provides.
     */
    private final int fillIncrease;

    /**
     * The percentage of speed increase the item provides.
     */
    private final int speedIncrease;

    /**
     * The cost to buy the item.
     */
    private final int buyPrice;

    /**
     * The monetary gain for selling the item.
     */
    private final int sellPrice;

    /**
     * Constructs a new Item with specified characteristics.
     *
     * @param towerTypeAffected The type of tower that the item affects.
     * @param fillIncrease      The increase in fill rate provided by the item.
     * @param speedIncrease     The increase in speed provided by the item.
     * @param buyPrice          The purchase price of the item.
     * @param difficultyBonus   The difficulty multiplier affecting the sell price.
     */
    public Item(Tower towerTypeAffected, int fillIncrease, int speedIncrease, int buyPrice, float difficultyBonus) {
        this.buyPrice = buyPrice;
        this.sellPrice = Math.round(this.buyPrice * difficultyBonus);
        this.itemName = generateRandomName();
        this.towerTypeAffected = towerTypeAffected;
        this.fillIncrease = fillIncrease;
        this.speedIncrease = speedIncrease;
    }

    /**
     * Generates a random name for the item from a predefined list of item names.
     *
     * @return A randomly selected name for the item.
     */
    private String generateRandomName() {
        Random rand = new Random();
        return itemNames.get(rand.nextInt(itemNames.size()));
    }


    /**
     * Gets the buy price of the item.
     *
     * @return The price at which the item can be purchased.
     */
    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    /**
     * Gets the sell price of the item.
     *
     * @return The price at which the item can be sold back to the game.
     */
    @Override
    public int getSellPrice() {
        return sellPrice;
    }


    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets the type of tower that the item affects.
     *
     * @return The tower type affected by the item.
     */
    public Tower getTowerTypeAffected() {
        return towerTypeAffected;
    }

    /**
     * Gets the percentage fill increase for an item.
     *
     * @return The fill increase percentage.
     */
    public int getFillIncrease() {
        return fillIncrease;
    }

    /**
     * Gets the speed increase for an item.
     *
     * @return The speed increase percentage.
     */
    public int getSpeedIncrease() {
        return speedIncrease;
    }
}
