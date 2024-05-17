package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class Item implements Purchasable {

    private static final List<String> itemNames = List.of("Speed Booster", "Collection Enhancer", "Range Extender");
    private String itemName;
    private String towerTypeAffected;
    private int fillIncrease;
    private int speedIncrease;

    public Item(String towerTypeAffected, int fillIncrease, int speedIncrease) {
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
        return 0;
    }

    @Override
    public int setBuyPrice(int cost) {
        return 0;
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

    public String getTowerTypeAffected() {
        return towerTypeAffected;
    }

    public void setTowerTypeAffected(String towerTypeAffected) {
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
