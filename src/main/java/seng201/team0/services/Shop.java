package seng201.team0.services;


import seng201.team0.GameManager;
import seng201.team0.models.*;

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
        this.towers = new ArrayList<>();
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
        Round upcomingRound = gameManager.getUpcomingRound();
        int numberProduceCarts = upcomingRound.getProduceCount();
        int numberMeatCarts = upcomingRound.getMeatCount();
        int numberDairyCarts = upcomingRound.getDairyCount();
        int totalCarts = numberProduceCarts + numberMeatCarts + numberDairyCarts;


        int towersToGenerate = 4;
        if (numberProduceCarts != 0){
            towersToGenerate -= 1;
            towers.add(generateProduceTower(rand));
        }
        if (numberMeatCarts != 0){
            towersToGenerate -= 1;
            towers.add(generateMeatTower(rand));
        }
        if (numberDairyCarts != 0){
            towersToGenerate -= 1;
            towers.add(generateDairyTower(rand));
        }
        for (int i = 0; i < towersToGenerate; i++){
            int itemTypeLots = rand.nextInt(totalCarts);
            if (itemTypeLots < numberProduceCarts) {
                towers.add(generateProduceTower(rand));
            } else if (itemTypeLots < numberProduceCarts + numberMeatCarts) {
                towers.add(generateMeatTower(rand));
            } else {
                towers.add(generateDairyTower(rand));
            }
        }
    }

    private int getRequiredTowerLevel(int currentRoundNumber, Random rand){
        if (currentRoundNumber <= 3){
            return 1;
        } else if (currentRoundNumber <= 6){
            System.out.println(currentRoundNumber);
            return 1 + rand.nextInt(0,3);
        } else if (currentRoundNumber <= 9){
            return 2 + rand.nextInt(0, 3);
        } else if (currentRoundNumber <= 12){
            return 3 + rand.nextInt(0, 3);
        } else {
            return 4 + rand.nextInt(0, 3);
        }
    }

    /**
     * Generates a new ProduceTower with random attributes.
     *
     * @param rand the Random object for randomisation
     * @return a new ProduceTower with random attributes
     */
    private ProduceTower generateProduceTower(Random rand) {
        int towerSpeed = rand.nextInt(3, 6);
        int towerFillAmount = rand.nextInt(10, 21);
        int towerLevel = getRequiredTowerLevel(gameManager.getCurrentRoundNumber(), rand);
        int buyPrice = towerSpeed*towerFillAmount*(3+towerLevel)/4*rand.nextInt(6, 10);
        return new ProduceTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus(), towerLevel);
    }

    /**
     * Generates a new MeatTower with random attributes.
     * The (4 + towerLevel)/4 is to change the buy price based on how powerful the tower is.
     *
     * @param rand the Random object for randomisation
     * @return a new MeatTower with random attributes
     */
    private MeatTower generateMeatTower(Random rand) {
        int towerSpeed = rand.nextInt(3, 6);
        int towerFillAmount = rand.nextInt(30, 41);
        int towerLevel = getRequiredTowerLevel(gameManager.getCurrentRoundNumber(), rand);
        int buyPrice = towerSpeed*towerFillAmount/2*(3+towerLevel)/4*rand.nextInt(6, 10);
        return new MeatTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus(), towerLevel);
    }

    /**
     * Generates a new DairyTower with random attributes.
     *
     * @param rand the Random object for randomisation
     * @return a new DairyTower with random attributes
     */
    private DairyTower generateDairyTower(Random rand) {
        int towerSpeed = rand.nextInt(3, 6);
        int towerFillAmount = rand.nextInt(50, 61);
        int towerLevel = getRequiredTowerLevel(gameManager.getCurrentRoundNumber(), rand);
        int buyPrice = towerSpeed*towerFillAmount*(3+towerLevel)/4*rand.nextInt(6, 10);
        return new DairyTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus(), towerLevel);
    }

    /**
     * Generates a list of random items that are purchasable from the shop, and assures
     * there are sufficient items for the upcoming round.
     */
    private void generateRandomItems() {
        Random rand = new Random();
        // Need to get rid of these
        try {
            Round upcomingRound = gameManager.getUpcomingRound();
            if (upcomingRound == null) {
                System.err.println("Upcoming round is not set. Cannot generate random items.");
                return;
            }

            int numberProduceCarts = upcomingRound.getProduceCount();
            int numberMeatCarts = upcomingRound.getMeatCount();
            int numberDairyCarts = upcomingRound.getDairyCount();
            int totalCarts = numberProduceCarts + numberMeatCarts + numberDairyCarts;
            int currentRoundNumber = gameManager.getCurrentRoundNumber();

            for (int i = 0; i < 3; i++) {
                Tower towerTypeAffected;
                int itemTypeLots = rand.nextInt(totalCarts);
                if (itemTypeLots < numberProduceCarts) {
                    towerTypeAffected = new ProduceTower();
                } else if (itemTypeLots < numberProduceCarts + numberMeatCarts) {
                    towerTypeAffected = new MeatTower();
                } else {
                    towerTypeAffected = new DairyTower();
                }

                int fillIncrease = 0;
                int speedIncrease = 0;
                while (fillIncrease == 0 && speedIncrease == 0) {
                    fillIncrease = rand.nextInt(6) * 5;
                    speedIncrease = rand.nextInt(6) * 5;
                }

                double priceAdjustmentFactor;
                if (currentRoundNumber <= 5) {
                    priceAdjustmentFactor = 1 + Math.log(1 + currentRoundNumber);
                } else if (currentRoundNumber <= 10) {
                    priceAdjustmentFactor = 2 + Math.log(1 + currentRoundNumber);
                } else {
                    priceAdjustmentFactor = 3 + Math.log(1 + currentRoundNumber);
                }

                int buyPrice = (int) ((fillIncrease + speedIncrease) * rand.nextInt(80, 120)/10*priceAdjustmentFactor);
                items.add(new Item(towerTypeAffected, fillIncrease, speedIncrease, buyPrice, gameManager.getDifficultyBonus()));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
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
