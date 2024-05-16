package seng201.team0.models;

public class Item implements Purchasable {

    private String itemName;
    private String towerTypeAffected;
    private float collectionIncrease;
    private float speedIncrease;

    Item() {
        String[] itemNames = {}; // Need to make list of possible item names
//        this.itemName = tempItemName;
//        this.towerTypeAffected = tempTowerTypeAffected;
//        this.collectionIncrease = tempCollectionIncrease;
//        this.speedIncrease = tempSpeedIncrease;
    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public int setCost(int cost) {
        return 0;
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public int setSellPrice() {
        return 0;
    }
}
