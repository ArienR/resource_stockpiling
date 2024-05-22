package seng201.team0.services;


import seng201.team0.GameManager;
import seng201.team0.models.Item;
import seng201.team0.models.MeatTower;
import seng201.team0.models.ProduceTower;
import seng201.team0.models.DairyTower;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop {

    GameManager gameManager;
    private final List<Tower> towers;
    private final List<Item> items;

    public Shop(GameManager gameManager) {
        this.gameManager = gameManager;
        towers = new ArrayList<>();
        items = new ArrayList<>();
        generateRandomTowers();
        generateRandomItems();
    }

    // This still needs to be fixed so that we can assure the player gets at least enough of the particular tower to prepare for the round
    private void generateRandomTowers() {
        Random rand = new Random();
        int currentRoundNumber = gameManager.getCurrentRoundNumber();

        if (currentRoundNumber < 3) {
            for (int i = 0; i < 4; i++) {
                towers.add(generateProduceTower(rand));
            }
        } else if (currentRoundNumber <= 6) {
            towers.add(generateProduceTower(rand));
            towers.add(generateMeatTower(rand));

            for (int i = 0; i < 2; i++) {
                if (rand.nextBoolean()) {
                    towers.add(generateProduceTower(rand));
                } else {
                    towers.add(generateMeatTower(rand));
                }
            }
        } else {
            towers.add(generateDairyTower(rand));

            for (int i = 0; i < 3; i++) {
                int towerType = rand.nextInt(3);
                if (towerType == 0) {
                    towers.add(generateProduceTower(rand));
                } else if (towerType == 1) {
                    towers.add(generateMeatTower(rand));
                } else {
                    towers.add(generateDairyTower(rand));
                }
            }
        }
    }

    private ProduceTower generateProduceTower(Random rand) {
        int towerSpeed = rand.nextInt(1, 4);
        int towerFillAmount = rand.nextInt(80, 120);
        int buyPrice = rand.nextInt(400, 500);
        int gameBonus = rand.nextInt(50);
        return new ProduceTower(towerSpeed, towerFillAmount, buyPrice, gameBonus);
    }

    private MeatTower generateMeatTower(Random rand) {
        int towerSpeed = rand.nextInt(1, 4);
        int towerFillAmount = rand.nextInt(80, 120);
        int buyPrice = rand.nextInt(400, 500);
        int gameBonus = rand.nextInt(50);
        return new MeatTower(towerSpeed, towerFillAmount, buyPrice, gameBonus);
    }

    private DairyTower generateDairyTower(Random rand) {
        int towerSpeed = rand.nextInt(1, 4);
        int towerFillAmount = rand.nextInt(80, 120);
        int buyPrice = rand.nextInt(400, 500);
        int gameBonus = rand.nextInt(50);
        return new DairyTower(towerSpeed, towerFillAmount, buyPrice, gameBonus);
    }

    private void generateRandomItems() {
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            List<Tower> towerTypeList = List.of(new ProduceTower(), new MeatTower(), new DairyTower());
            Tower towerTypeAffected = towerTypeList.get(rand.nextInt(3));
            int buyPrice = rand.nextInt(50, 100);
            int fillIncrease = rand.nextInt(0, 3) * 100;
            int speedIncrease = rand.nextInt(0, 3) * 100;
            items.add(new Item(towerTypeAffected, fillIncrease, speedIncrease, buyPrice));
        }
    }

    public List<Tower> getTowers() {
        return towers;
    }

    public List<Item> getItems() {
        return items;
    }
}
