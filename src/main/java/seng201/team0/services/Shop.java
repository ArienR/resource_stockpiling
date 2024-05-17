package seng201.team0.services;


import seng201.team0.models.Item;
import seng201.team0.models.ProduceTower;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop {
    private List<Tower> towers;
    private List<Item> items;

    public Shop() {
        towers = new ArrayList<>();
        items = new ArrayList<>();
        generateRandomTowers();
        generateRandomItems();
    }

    private void generateRandomTowers() {
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            float towerSpeed = rand.nextFloat();
            int towerFillAmount = rand.nextInt(80, 120);
            int buyPrice = rand.nextInt(400, 600);
            int gameBonus = rand.nextInt(50);
            towers.add(new ProduceTower(towerSpeed, towerFillAmount, buyPrice, gameBonus));
        }
    }

    private void generateRandomItems() {
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            String towerTypeAffected = "Produce Tower";
            float collectionIncrease = rand.nextFloat();
            float speedIncrease = rand.nextFloat();
            items.add(new Item(towerTypeAffected, collectionIncrease, speedIncrease));
        }
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<Item> getItems() {
        return items;
    }
}
