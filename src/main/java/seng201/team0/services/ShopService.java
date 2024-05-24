package seng201.team0.services;


import seng201.team0.GameManager;
import seng201.team0.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The ShopService class represents the in-game shop from which the player will buy
 * towers and items to prepare for the upcoming round they have selected. The
 * generates towers and items based on the current round number.
 */
public class ShopService {

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
     * Constructs a new ShopService which is associated with the GamaManager
     * depending on the current round number.
     *
     * @param gameManager the GameManager managing the game state
     */
    public ShopService(GameManager gameManager) {
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
        int roundNumber =  gameManager.getCurrentRoundNumber();
        Round upcomingRound = gameManager.getUpcomingRound();

        int numberProduceCarts = upcomingRound.getProduceCount();
        int numberMeatCarts = upcomingRound.getMeatCount();
        int numberDairyCarts = upcomingRound.getDairyCount();
        int totalCarts = numberProduceCarts + numberMeatCarts + numberDairyCarts;


        int towersToGenerate = 4;
        if (numberProduceCarts != 0){
            towersToGenerate -= 1;
            towers.add(generateProduceTower(rand, roundNumber));
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
                towers.add(generateProduceTower(rand, roundNumber));
            } else if (itemTypeLots < numberProduceCarts + numberMeatCarts) {
                towers.add(generateMeatTower(rand));
            } else {
                towers.add(generateDairyTower(rand));
            }
        }
    }

    /**
     * Depending on the current round number it will produce an appropriate level
     * for a tower in the shop.
     *
     * @param currentRoundNumber the current round
     * @param rand a Random object
     * @return an appropriate tower level
     */
    public int getRequiredTowerLevel(int currentRoundNumber, Random rand){
        if (currentRoundNumber <= 3){
            return 1;
        } else if (currentRoundNumber <= 6){
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
     * Generates a new ProduceTower with random attributes. The generation assures that the price
     * scales with the towers' ability.
     *
     * @param rand the Random object for randomisation
     * @param roundNumber the current round
     * @return a new ProduceTower with random attributes
     */
    public ProduceTower generateProduceTower(Random rand, int roundNumber) {
        int towerSpeed = rand.nextInt(3, 6);
        int towerFillAmount = rand.nextInt(10, 21);
        int towerLevel = getRequiredTowerLevel(gameManager.getCurrentRoundNumber(), rand);
        int buyPrice;
        if (roundNumber == 1) {
            buyPrice = rand.nextInt(480, 500);
        } else {
            buyPrice = towerSpeed*towerFillAmount*(3+towerLevel)/4*rand.nextInt(7, 11);
        }
        return new ProduceTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus(), towerLevel);
    }

    /**
     * Generates a new MeatTower with random attributes. The generation assures that the price
     * scales with the towers' ability.
     *
     * @param rand the Random object for randomisation
     * @return a new MeatTower with random attributes
     */
    public MeatTower generateMeatTower(Random rand) {
        int towerSpeed = rand.nextInt(3, 6);
        int towerFillAmount = rand.nextInt(30, 41);
        int towerLevel = getRequiredTowerLevel(gameManager.getCurrentRoundNumber(), rand);
        int buyPrice = towerSpeed*towerFillAmount/2*(3+towerLevel)/4*rand.nextInt(7, 11); // The (4 + towerLevel)/4 is to change the buy price based on how powerful the tower is.
        return new MeatTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus(), towerLevel);
    }

    /**
     * Generates a new DairyTower with random attributes. The generation assures that the price
     * scales with the towers' ability.
     *
     * @param rand the Random object for randomisation
     * @return a new DairyTower with random attributes
     */
    public DairyTower generateDairyTower(Random rand) {
        int towerSpeed = rand.nextInt(3, 6);
        int towerFillAmount = rand.nextInt(50, 61);
        int towerLevel = getRequiredTowerLevel(gameManager.getCurrentRoundNumber(), rand);
        int buyPrice = towerSpeed*towerFillAmount/2*(3+towerLevel)/4*rand.nextInt(7, 11);
        return new DairyTower(towerSpeed, towerFillAmount, buyPrice, gameManager.getDifficultyBonus(), towerLevel);
    }

    /**
     * Generates a list of random items that are purchasable from the shop, and assures
     * there are sufficient items for the upcoming round.
     */
    public void generateRandomItems() {
        Random rand = new Random();
        Round upcomingRound = gameManager.getUpcomingRound();

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
            Item newItem = new Item(towerTypeAffected, fillIncrease, speedIncrease, buyPrice, gameManager.getDifficultyBonus());
            items.add(newItem);
        }
    }

    /**
     * Gets the randomly generated towers.
     *
     * @return randomly generated towers
     */
    public List<Tower> getTowers() {
        return towers;
    }

    /**
     * Gets the randomly generated items.
     *
     * @return randomly generated items
     */
    public List<Item> getItems() {
        return items;
    }
}
