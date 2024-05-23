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

/**
 * The Shop class represents the in-game shop from which the player will buy
 * towers and items to prepare for the upcoming round they have selected. The
 * generates towers and items based on the current round number.
 */
public class Shop {

    /**
     * Reference to the GameManager singleton managing the game state.
     */
    GameManager gameManager;

    /**
     * List of towers to be displayed in the shop for purchase.
     */
    private final List<Tower> towers;

    /**
     * List of items to be displayed in the shop for purchase.
     */
    private final List<Item> items;

    /**
     * Constructs a new Shop which is associated with the GamaManager
     * depending on the current round number.
     *
     * @param gameManager the GameManager managing the game state
     */
    public Shop(GameManager gameManager) {
        this.gameManager = gameManager;
        towers = new ArrayList<>();
        items = new ArrayList<>();
        generateRandomTowers();
        generateRandomItems();
    }

    /**
     * Generates a list of random towers available for purchase in the shop
     * depending on the current round number, and assures there is sufficient
     * towers for the player to prepare for the upcoming round.
     */
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

    /**
     * Generates a new ProduceTower with random attributes.
     *
     * @param rand the Random object for randomisation
     * @return a new ProduceTower with random attributes
     */
    private ProduceTower generateProduceTower(Random rand) {
        int towerSpeed = rand.nextInt(5, 8);
        int towerFillAmount = rand.nextInt(50, 70);
        int buyPrice = rand.nextInt(400, 500);
        return new ProduceTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus());
    }

    /**
     * Generates a new MeatTower with random attributes.
     *
     * @param rand the Random object for randomisation
     * @return a new MeatTower with random attributes
     */
    private MeatTower generateMeatTower(Random rand) {
        int towerSpeed = rand.nextInt(1, 4);
        int towerFillAmount = rand.nextInt(80, 120);
        int buyPrice = rand.nextInt(400, 500);
        return new MeatTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus());
    }

    /**
     * Generates a new DairyTower with random attributes.
     *
     * @param rand the Random object for randomisation
     * @return a new DairyTower with random attributes
     */
    private DairyTower generateDairyTower(Random rand) {
        int towerSpeed = rand.nextInt(1, 4);
        int towerFillAmount = rand.nextInt(80, 120);
        int buyPrice = rand.nextInt(400, 500);
        return new DairyTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus());
    }

    /**
     * Generates a list of random items that are purchasable from the shop, and assures
     * there are sufficient items for the upcoming round.
     */
    private void generateRandomItems() {
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            List<Tower> towerTypeList = List.of(new ProduceTower(), new MeatTower(), new DairyTower());
            Tower towerTypeAffected = towerTypeList.get(rand.nextInt(3));
            int buyPrice = rand.nextInt(50, 100);
            int fillIncrease = rand.nextInt(0, 3) * 100;
            int speedIncrease = rand.nextInt(0, 3) * 100;
            items.add(new Item(towerTypeAffected, fillIncrease, speedIncrease, buyPrice, gameManager.getDifficultyBonus()));
        }
    }

    /**
     * Gets the randomly generated towers.
     * @return randomly generated towers
     */
    public List<Tower> getTowers() {
        return towers;
    }

    /**
     * Gets the randomly generated items.
     * @return randomly generated items
     */
    public List<Item> getItems() {
        return items;
    }
}
