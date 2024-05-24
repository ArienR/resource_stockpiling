package seng201.team0.unittests.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.*;
import seng201.team0.services.GameStateService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class GameStateServiceTest {

    private Player player;
    private GameManager testGameManager;
    private GameStateService testGameStateService;
    private Tower tower;

    @BeforeEach
    public void setupTest() {
        player = new Player("John Doe");
        testGameManager = new GameManager(player);
        testGameStateService = new GameStateService(testGameManager);
        Round upcomingRound = new Round(1, 2, 3, 4); // Arbitrary values for testing
        testGameManager.setUpcomingRound(upcomingRound);
        tower = new ProduceTower(5, 20, 1,1.0f, 1);
        player.getTowerList().add(tower);
        player.setEquippedTowers(Collections.singletonList(tower));
        testGameManager.setUpcomingRound(upcomingRound);
    }

    @Test
    void testRoundOneOfFiveWon() {
        testGameManager.setNumberOfRounds(5);
        testGameManager.incrementCurrentRoundNumber();
        assertEquals(2, testGameManager.getCurrentRoundNumber());
        testGameManager.setRoundWon(true);
        testGameStateService.isEndOfGame();
        assertFalse(testGameManager.isGameWon());
    }

    @Test
    void testRoundFiveOfFiveWon() {
        testGameManager.setNumberOfRounds(5);
        for (int i = 1; i < 5; i++) {
            testGameManager.incrementCurrentRoundNumber(); // Set current round to 5
        }
        testGameManager.setRoundWon(true);
        testGameStateService.isEndOfGame();
        assertTrue(testGameManager.isGameWon());
        assertEquals(6, testGameManager.getCurrentRoundNumber());
    }

    @Test
    void testGameIsLost() {
        testGameManager.setRoundWon(false);
        testGameStateService.isEndOfGame();
        assertFalse(testGameManager.isRoundWon());
        assertFalse(testGameManager.isGameWon());
    }

    @Test
    void testRandomEvents() {
        tower.incrementConsecutiveUses();
        testGameStateService.randomEvents();
        assertTrue(tower.isTowerBroken() || tower.getTowerLevel() > 1);
    }

    @Test
    void testUsedTowerRandomEventWithNoPriorUses() {
        testGameStateService.randomEvents();
        assertEquals(1, tower.getConsecutiveUses());
        assertEquals(2, tower.getTowerLevel());
        assertFalse(tower.isTowerBroken());
    }

    @Test
    void testUsedTowerRandomEventWithHighConsecutiveUses() {
        for (int i = 0; i < 9; i++) {
            tower.incrementConsecutiveUses();
        }
        int initialMoney = player.getPlayerMoney();
        testGameStateService.randomEvents();
        assertTrue(tower.isTowerBroken());
        assertEquals(initialMoney + Math.round((float) (tower.getBuyPrice() / 4)), player.getPlayerMoney());
    }
    @Test
    void testMoneyEarnedCalculation() {
        int initialMoney = 1000;
        player.setPlayerMoney(initialMoney);
        testGameStateService.moneyEarned();
        assertTrue(player.getPlayerMoney() > initialMoney);
    }

    @Test
    void testTowerLevelDecreasesWhenThresholdExceeded() {
        for (int i = 0; i < 4; i++) {
            tower.incrementConsecutiveNonUses();
        }
        tower.incrementTowerLevel();
        int initialLevel = tower.getTowerLevel();
        testGameStateService.unusedTowerRandomEvent(tower);
        assertTrue(tower.getTowerLevel() < initialLevel);
        assertEquals("2  ==>  1", tower.getTowerTableLevel());
    }

    @Test
    void testScoreEarnedCalculation() {
        int initialScore = 1000;
        player.setPlayerMoney(initialScore);
        testGameStateService = new GameStateService(testGameManager);
        testGameStateService.scoreEarned();
        assertTrue(player.getPlayerScore() > initialScore);
    }
}
