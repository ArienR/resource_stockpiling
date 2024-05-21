package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class Item implements Purchasable {

    private static final List<String> itemNames = List.of("Speed Booster", "Collection Enhancer", "Range Extender");
    private String itemName;
    private Tower towerTypeAffected;
    private int fillIncrease;
    private int speedIncrease;
    private int buyPrice;
    private int sellPrice;

    public Item(Tower towerTypeAffected, int fillIncrease, int speedIncrease, int buyPrice) {
        this.buyPrice = buyPrice;
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
    public int setSellPrice(int cost) {
        return 0;
    }

    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    @Override
    public void setBuyPrice(int cost) {
        this.buyPrice = cost;
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Tower getTowerTypeAffected() {
        return towerTypeAffected;
    }

    public void setTowerTypeAffected(Tower towerTypeAffected) {
        this.towerTypeAffected = towerTypeAffected;
    }

    public int getCollectionIncrease() {
        return fillIncrease;
    }

    public void setCollectionIncrease(int collectionIncrease) {
        this.fillIncrease = collectionIncrease;
    }

    public int getSpeedIncrease() {
        return speedIncrease;
    }

    public void setSpeedIncrease(int speedIncrease) {
        this.speedIncrease = speedIncrease;
    }
}
