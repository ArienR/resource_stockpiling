package seng201.team0;

import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
public class Player {
    private String name;
    private List<Tower> towerList;
    private List<Item> itemList;
    private int playerMoney;

    public Player(String name) {
        this.name = name;
        this.towerList = new ArrayList<>();
        this.itemList = new ArrayList<>();
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
}
