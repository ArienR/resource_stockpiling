package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class Item implements Purchasable {

    private static final List<String> itemNames = List.of("Speed Booster", "Collection Enhancer", "Range Extender");
    private String itemName;
    private String towerTypeAffected;
    private float collectionIncrease;
    private float speedIncrease;

    public Item(String towerTypeAffected, float collectionIncrease, float speedIncrease) {
        this.itemName = generateRandomName();
        this.towerTypeAffected = towerTypeAffected;
        this.collectionIncrease = collectionIncrease;
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

    public float getCollectionIncrease() {
        return collectionIncrease;
    }

    public void setCollectionIncrease(float collectionIncrease) {
        this.collectionIncrease = collectionIncrease;
    }

    public float getSpeedIncrease() {
        return speedIncrease;
    }

    public void setSpeedIncrease(float speedIncrease) {
        this.speedIncrease = speedIncrease;
    }
}
