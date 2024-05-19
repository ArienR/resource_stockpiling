package seng201.team0;

import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
public class Player {
    private String name;
    private List<Tower> towerList;
    private List<Item> itemList;
    private List<Tower> purchasedTowers;
    private List<Item> purchasedItems;
    private List<Tower> equippedTowers = new ArrayList<>();
    private List<Item> equippedItems = new ArrayList<>();
    private int playerMoney;
    private int totalPlayerMoney;
    private int playerScore;

    public Player(String name) {
        this.name = name;
        this.playerScore = 0;
        this.totalPlayerMoney = 0;
        this.towerList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.purchasedTowers = new ArrayList<>();
        this.purchasedItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String tempName) {
        this.name = tempName;
    }

    public List<Tower> getTowerList() {
        return towerList;
    }

    public void setTowerList(List<Tower> towerList) {
        this.towerList = towerList;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Tower> getPurchasedTowers() {
        return purchasedTowers;
    }

    public void setPurchasedTowers(List<Tower> purchasedTowers) {
        this.purchasedTowers = purchasedTowers;
    }

    public List<Item> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<Item> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }

    public List<Tower> getEquippedTowers() {
        return equippedTowers;
    }

    public void setEquippedTowers(List<Tower> equippedTowers) {
        this.equippedTowers = equippedTowers;
    }

    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    public void setEquippedItems(List<Item> equippedItems) {
        this.equippedItems = equippedItems;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getTotalPlayerMoney() {
        return totalPlayerMoney;
    }

    public void setTotalPlayerMoney(int totalPlayerMoney) {
        this.totalPlayerMoney = totalPlayerMoney;
    }
}
