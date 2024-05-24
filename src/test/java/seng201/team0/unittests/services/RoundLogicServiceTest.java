package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.*;
import seng201.team0.services.GenerateCartsService;
import seng201.team0.services.RoundLogicService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoundLogicServiceTest {

    private GameManager testGameManager;
    private RoundLogicService roundLogicService;
    private Player testPlayer;

    @BeforeEach
    void setupTest() {
        testPlayer = new Player("John Doe");
        testGameManager = new GameManager(testPlayer);
        roundLogicService = new RoundLogicService(testGameManager);
    }

    @Test
    void testCanWinRoundProduceBoundary() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(5, 0, 0, -3);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new ProduceTower(3, 20, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        items.add(new Item(new ProduceTower(), 10, 10, 50, 1.0f));
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertTrue(roundLogicService.canWinRound());
    }

    @Test
    void testCanWinRoundMeatBoundary() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(0, 5, 0, 2);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new MeatTower(4, 32, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        items.add(new Item(new MeatTower(), 20, 20, 50, 1.0f));
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertTrue(roundLogicService.canWinRound());
    }

    @Test
    void testCanWinRoundDairyBoundary() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(0, 0, 5, 2);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new DairyTower(4, 50, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        items.add(new Item(new DairyTower(), 25, 25, 50, 1.0f));
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertTrue(roundLogicService.canWinRound());
    }

    @Test
    void testCanWinWithAllTypes() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(1, 1, 1, 0);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new ProduceTower(3, 10, 100, 1.0f, 1));
        towers.add(new MeatTower(3, 30, 100, 1.0f, 1));
        towers.add(new DairyTower(3, 50, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertTrue(roundLogicService.canWinRound());
    }

    @Test
    void testCalculateTotalCartCapacity() {
        List<Cart> carts = List.of(
                new ProduceCart(0),
                new MeatCart(0),
                new DairyCart(0)
        );
        int totalCapacity = roundLogicService.calculateTotalCartCapacity(carts);
        assertEquals(600, totalCapacity);
    }

    @Test
    void testCalculateTotalTowerFillRateIncreasedLevel() {
        List<Tower> towers = List.of(
                new ProduceTower(5, 10, 100, 1.0f, 2),
                new ProduceTower(3, 15, 200, 1.0f, 2)
        );
        float totalItemIncrease = 1f;
        float levelTwoIncrease = 1.25f;
        float fillRate = roundLogicService.calculateTotalTowerFillRate(towers, ProduceTower.class, totalItemIncrease);
        assertEquals(95 * levelTwoIncrease, fillRate);
    }

    @Test
    void testCalculateTotalItemIncrease() {
        List<Item> items = List.of(
                new Item(new ProduceTower(), 10, 10, 50, 1.0f),
                new Item(new ProduceTower(), 5, 5, 50, 1.0f)
        );
        float totalIncrease = roundLogicService.calculateTotalItemIncrease(ProduceTower.class, items);
        assertEquals(1.3f, Math.round(totalIncrease * 10) / 10.0f); // Requires rounding due to floating point inaccuracy
    }

    @Test
    void testCalculateTowerFillRateWithItem() {
        List<Tower> towers = List.of(
                new ProduceTower(5, 10, 100, 1.0f, 1),
                new ProduceTower(3, 15, 200, 1.0f, 1)
        );
        List<Item> items = List.of(
                new Item(new ProduceTower(), 10, 10, 50, 1.0f)
        );
        float fillRate = roundLogicService.calculateTowerFillRate(towers, ProduceTower.class, items);
        assertEquals(95 * 1.2f, fillRate);
    }

    @Test
    void testCanLoseProduce() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(5, 0, 0, 0);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new ProduceTower(3, 10, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertFalse(roundLogicService.canWinRound());
    }

    @Test
    void testCanLoseMeat() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(0, 5, 0, 0);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new MeatTower(3, 30, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertFalse(roundLogicService.canWinRound());
    }

    @Test
    void testCanLoseDairy() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(0, 0, 5, 0);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new DairyTower(3, 50, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertFalse(roundLogicService.canWinRound());
    }

    @Test
    void testCanLoseProduceWithOtherTypes() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(5, 0, 0, 0);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new MeatTower(5, 70, 100, 1.0f, 1));
        towers.add(new DairyTower(5, 70, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertFalse(roundLogicService.canWinRound());
    }

    @Test
    void testCanLoseMeatWithOtherTypes() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(0, 5, 0, 0);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new ProduceTower(5, 70, 100, 1.0f, 1));
        towers.add(new DairyTower(5, 70, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertFalse(roundLogicService.canWinRound());
    }

    @Test
    void testCanLoseDairyWithOtherTypes() {
        List<Tower> towers = new ArrayList<>();
        Round upcomingRound = new Round(0, 0, 5, 0);
        testGameManager.setUpcomingRound(upcomingRound);
        towers.add(new ProduceTower(5, 70, 100, 1.0f, 1));
        towers.add(new MeatTower(5, 70, 100, 1.0f, 1));
        testPlayer.setEquippedTowers(towers);

        testGameManager.setTrackDistance(80);
        List<Item> items = new ArrayList<>();
        testPlayer.setEquippedItems(items);

        roundLogicService = new RoundLogicService(testGameManager);
        assertFalse(roundLogicService.canWinRound());
    }
}
