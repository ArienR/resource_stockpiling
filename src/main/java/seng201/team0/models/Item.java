package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class Item implements Purchasable {

    private static final List<String> itemNames = List.of("Grocery Galvanizer", "Market Maestro", "Aisle Accelerator",
            "Checkout Champion", "Cart Catalyst", "Stockroom Speedster", "Supermarket Surge",
            "Retail Rocket", "Bountiful Booster", "Fulfillment Fury", "Supply Streamliner",
            "Stockpile Sprinter", "Distribution Dynamo", "Warehouse Whirlwind", "Market Mover",
            "Expedition Enhancer", "Inventory Igniter", "Cart Crusader", "Harvest Helper",
            "Delivery Dynamo", "Provision Propeller", "Cart Commander", "Stock Swift",
            "Vendor Velocity", "Retail Rush"
    );
    private final String itemName;
    private final Tower towerTypeAffected;
    private final int fillIncrease;
    private final int speedIncrease;
    private final int buyPrice;
    private final int sellPrice;

    public Item(Tower towerTypeAffected, int fillIncrease, int speedIncrease, int buyPrice, float difficultyBonus) {
        this.buyPrice = buyPrice;
        this.sellPrice = Math.round(this.buyPrice * difficultyBonus);
        this.itemName = generateRandomName();
        this.towerTypeAffected = towerTypeAffected;
        this.fillIncrease = fillIncrease;
        this.speedIncrease = speedIncrease;
    }

    private String generateRandomName() {
        Random rand = new Random();
        return itemNames.get(rand.nextInt(itemNames.size()));
    }

    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    @Override
    public int getSellPrice() {
        return sellPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public Tower getTowerTypeAffected() {
        return towerTypeAffected;
    }

    public int getCollectionIncrease() {
        return fillIncrease;
    }

    public int getSpeedIncrease() {
        return speedIncrease;
    }
}
