package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.*;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;
import seng201.team0.services.ShopService;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the ShopService implementation
 */
public class ShopServiceServiceTest {

    Player testPlayer;
    GameManager testGameManager;
    ShopService testShopService;

    @BeforeEach
    void setupTest() {
        testPlayer = new Player("John Doe");
        testGameManager = new GameManager(testPlayer);
        Round upcomingRound = new Round(5, 5, 5, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
    }

    @Test
    public void testGenerateRandomItemsCount() {
        List<Item> shopItems = testShopService.getItems();

        assertEquals(3, shopItems.size());
    }

    @Test
    void testItemMade() {
        List<Item> shopItems = testShopService.getItems();

        assertTrue((shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof ProduceTower)
                || (shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof MeatTower)
                || (shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof DairyTower));
    }

    @Test
    void testProduceItem() {
        Round upcomingRound = new Round(5, 0, 0, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        assertTrue((shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof ProduceTower));
    }

    @Test
    void testMeatItem() {
        Round upcomingRound = new Round(0, 5, 0, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        assertTrue((shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof MeatTower));
    }

    @Test
    void testDairyItem() {
        Round upcomingRound = new Round(0, 0, 5, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        assertTrue((shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof DairyTower));
    }

    @Test
    void testProduceOrMeatItem() {
        Round upcomingRound = new Round(5, 5, 0, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        assertFalse((shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof DairyTower));
    }

    @Test
    void testProduceOrDairyItem() {
        Round upcomingRound = new Round(5, 0, 5, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        assertFalse((shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof MeatTower));
    }

    @Test
    void testMeatOrDairyItem() {
        Round upcomingRound = new Round(0, 5, 5, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        assertFalse((shopItems.get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof ProduceTower));
    }

    @Test
    public void testItemPropertiesWithinRange() {
        List<Item> shopItems = testShopService.getItems();

        for (Item item : shopItems) {
            int fillIncrease = item.getFillIncrease();
            int speedIncrease = item.getSpeedIncrease();
            assertTrue(fillIncrease >= 0 && fillIncrease <= 25 && fillIncrease % 5 == 0);
            assertTrue(speedIncrease >= 0 && speedIncrease <= 25 && speedIncrease % 5 == 0);
        }
    }

    @Test
    public void testPriceCalculationRoundTwo() {
        testGameManager.incrementCurrentRoundNumber();
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        for (Item item : shopItems) {
            int buyPrice = item.getBuyPrice();
            int fillIncrease = item.getFillIncrease();
            int speedIncrease = item.getSpeedIncrease();
            double priceAdjustmentFactor = 1 + Math.log(1 + testGameManager.getCurrentRoundNumber());
            int expectedMinPrice = (int) ((fillIncrease + speedIncrease) * 8 * priceAdjustmentFactor);
            int expectedMaxPrice = (int) ((fillIncrease + speedIncrease) * 12 * priceAdjustmentFactor);
            assertTrue(buyPrice >= expectedMinPrice && buyPrice <= expectedMaxPrice);
        }
    }

    @Test
    public void testPriceCalculationRoundTen() {
        for (int i = 0; i < 9; i++) {
            testGameManager.incrementCurrentRoundNumber();
        }
        assertEquals(10, testGameManager.getCurrentRoundNumber());
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        for (Item item : shopItems) {
            int buyPrice = item.getBuyPrice();
            int fillIncrease = item.getFillIncrease();
            int speedIncrease = item.getSpeedIncrease();
            double priceAdjustmentFactor = 2 + Math.log(1 + testGameManager.getCurrentRoundNumber());
            int expectedMinPrice = (int) ((fillIncrease + speedIncrease) * 8 * priceAdjustmentFactor);
            int expectedMaxPrice = (int) ((fillIncrease + speedIncrease) * 12 * priceAdjustmentFactor);
            assertTrue(buyPrice >= expectedMinPrice && buyPrice <= expectedMaxPrice);
        }
    }

    @Test
    public void testPriceCalculationRoundFifteen() {
        for (int i = 0; i < 14; i++) {
            testGameManager.incrementCurrentRoundNumber();
        }
        assertEquals(15, testGameManager.getCurrentRoundNumber());
        testShopService = new ShopService(testGameManager);
        List<Item> shopItems = testShopService.getItems();

        for (Item item : shopItems) {
            int buyPrice = item.getBuyPrice();
            int fillIncrease = item.getFillIncrease();
            int speedIncrease = item.getSpeedIncrease();
            double priceAdjustmentFactor = 3 + Math.log(1 + testGameManager.getCurrentRoundNumber());
            int expectedMinPrice = (int) ((fillIncrease + speedIncrease) * 8 * priceAdjustmentFactor);
            int expectedMaxPrice = (int) ((fillIncrease + speedIncrease) * 12 * priceAdjustmentFactor);
            assertTrue(buyPrice >= expectedMinPrice && buyPrice <= expectedMaxPrice);
        }
    }

    @Test
    void testGenerateRandomTowers() {
        List<Tower> shopTowers = testShopService.getTowers();
        assertEquals(4, shopTowers.size());
    }

    @Test
    void testGenerateRandomTowersProduce() {
        Round upcomingRound = new Round(5, 0, 0, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Tower> shopTowers = testShopService.getTowers();
        assertTrue(shopTowers.stream().allMatch(tower -> tower instanceof ProduceTower));
    }

    @Test
    void testGenerateRandomTowersMeat() {
        Round upcomingRound = new Round(0, 5, 0, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Tower> shopTowers = testShopService.getTowers();
        assertTrue(shopTowers.stream().allMatch(tower -> tower instanceof MeatTower));
    }

    @Test
    void testGenerateRandomTowersDairy() {
        Round upcomingRound = new Round(0, 0, 5, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
        List<Tower> shopTowers = testShopService.getTowers();
        assertTrue(shopTowers.stream().allMatch(tower -> tower instanceof DairyTower));
    }

    @Test
    void testGetRequiredTowerLevel() {
        Random rand = new Random();
        int roundNumber = 1;
        int level = testShopService.getRequiredTowerLevel(roundNumber, rand);
        assertEquals(1, level);

        roundNumber = 5;
        level = testShopService.getRequiredTowerLevel(roundNumber, rand);
        assertTrue(level >= 1 && level <= 3);

        roundNumber = 8;
        level = testShopService.getRequiredTowerLevel(roundNumber, rand);
        assertTrue(level >= 2 && level <= 4);

        roundNumber = 11;
        level = testShopService.getRequiredTowerLevel(roundNumber, rand);
        assertTrue(level >= 3 && level <= 5);

        roundNumber = 15;
        level = testShopService.getRequiredTowerLevel(roundNumber, rand);
        assertTrue(level >= 4 && level <= 6);
    }

    @Test
    void testGenerateProduceTowerRoundOne() {
        Random rand = new Random();
        ProduceTower produceTower = testShopService.generateProduceTower(rand, 1);
        assertNotNull(produceTower);
        assertTrue(produceTower.getTowerSpeed() >= 3 && produceTower.getTowerSpeed() <= 5);
        assertTrue(produceTower.getTowerFillAmount() >= 10 && produceTower.getTowerFillAmount() <= 20);
        assertTrue(produceTower.getBuyPrice() >= 480 && produceTower.getBuyPrice() <= 500);
    }

    @Test
    void testGenerateProduceTowerRoundTen() {
        for (int i = 0; i < 9; i++) {
            testGameManager.incrementCurrentRoundNumber();
        }
        assertEquals(10, testGameManager.getCurrentRoundNumber());
        testShopService = new ShopService(testGameManager);
        Random rand = new Random();
        ProduceTower produceTower = testShopService.generateProduceTower(rand, testGameManager.getCurrentRoundNumber());

        assertTrue(produceTower.getTowerSpeed() >= 3 && produceTower.getTowerSpeed() <= 5);
        assertTrue(produceTower.getTowerFillAmount() >= 10 && produceTower.getTowerFillAmount() <= 20);
        assertTrue(produceTower.getBuyPrice() >= 210 && produceTower.getBuyPrice() <= 2000);
    }

    @Test
    void testGenerateMeatTowerRoundTen() {
        for (int i = 0; i < 9; i++) {
            testGameManager.incrementCurrentRoundNumber();
        }
        assertEquals(10, testGameManager.getCurrentRoundNumber());
        testShopService = new ShopService(testGameManager);
        Random rand = new Random();
        MeatTower meatTower = testShopService.generateMeatTower(rand);

        assertTrue(meatTower.getTowerSpeed() >= 3 && meatTower.getTowerSpeed() <= 5);
        assertTrue(meatTower.getTowerFillAmount() >= 30 && meatTower.getTowerFillAmount() <= 40);
        assertTrue(meatTower.getBuyPrice() >= 315 && meatTower.getBuyPrice() <= 2250);
    }

    @Test
    void testGenerateDairyTowerRoundTen() {
        for (int i = 0; i < 9; i++) {
            testGameManager.incrementCurrentRoundNumber();
        }
        assertEquals(10, testGameManager.getCurrentRoundNumber());
        testShopService = new ShopService(testGameManager);
        Random rand = new Random();
        DairyTower dairyTower = testShopService.generateDairyTower(rand);

        assertTrue(dairyTower.getTowerSpeed() >= 3 && dairyTower.getTowerSpeed() <= 5);
        assertTrue(dairyTower.getTowerFillAmount() >= 50 && dairyTower.getTowerFillAmount() <= 60);
        assertTrue(dairyTower.getBuyPrice() >= 525 && dairyTower.getBuyPrice() <= 3000);
    }

    @RepeatedTest(20)
    void testGetRequiredTowerLevelRound1To3() {
        Random rand = new Random();
        int roundNumber = 1;
        int level = testShopService.getRequiredTowerLevel(roundNumber, rand);
        assertEquals(1, level);

        roundNumber = 3;
        level = testShopService.getRequiredTowerLevel(roundNumber, rand);
        assertEquals(1, level);
    }

    @RepeatedTest(20)
    void testGetRequiredTowerLevelRound4To6() {
        Random rand = new Random();
        for (int roundNumber = 4; roundNumber <= 6; roundNumber++) {
            int level = testShopService.getRequiredTowerLevel(roundNumber, rand);
            assertTrue(level >= 1 && level <= 3);
        }
    }

    @RepeatedTest(20)
    void testGetRequiredTowerLevelRound7To9() {
        Random rand = new Random();
        for (int roundNumber = 7; roundNumber <= 9; roundNumber++) {
            int level = testShopService.getRequiredTowerLevel(roundNumber, rand);
            assertTrue(level >= 2 && level <= 4);
        }
    }

    @RepeatedTest(20)
    void testGetRequiredTowerLevelRound10To12() {
        Random rand = new Random();
        for (int roundNumber = 10; roundNumber <= 12; roundNumber++) {
            int level = testShopService.getRequiredTowerLevel(roundNumber, rand);
            assertTrue(level >= 3 && level <= 5);
        }
    }

    @RepeatedTest(20)
    void testGetRequiredTowerLevelRound13AndAbove() {
        Random rand = new Random();
        for (int roundNumber = 13; roundNumber <= 20; roundNumber++) { // Test up to round 20 for thoroughness
            int level = testShopService.getRequiredTowerLevel(roundNumber, rand);
            assertTrue(level >= 4 && level <= 6);
        }
    }
}
