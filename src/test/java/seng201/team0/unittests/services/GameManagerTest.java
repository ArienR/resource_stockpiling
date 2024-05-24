package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.Round;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    private GameManager gameManager;
    private Player player;


    @BeforeEach
    void setUp() {
        player = new Player("John Doe");
        gameManager = new GameManager(player);
        gameManager.setUpcomingRound(new Round(5, 5, 5, 5));
    }

    @Test
    void testLaunchInventoryScreenResetsRoundEndedFlag() {
        gameManager.isRoundEnded = true;

        gameManager.launchInventoryScreen();

        assertFalse(gameManager.isRoundEnded);
    }

    @Test
    void testNumberOfRounds() {
        gameManager.setNumberOfRounds(10);
        assertEquals(10, gameManager.getNumberOfRounds());
    }

    @Test
    void testRoundWon() {
        gameManager.setRoundWon(true);
        assertTrue(gameManager.isRoundWon());
        gameManager.setRoundWon(false);
        assertFalse(gameManager.isRoundWon());
    }

    @Test
    void testCurrentRoundNumber() {
        gameManager.setNumberOfRounds(5);
        assertEquals(1, gameManager.getCurrentRoundNumber());
        gameManager.incrementCurrentRoundNumber();
        assertEquals(2, gameManager.getCurrentRoundNumber());
    }

    @Test
    void testGameWon() {
        gameManager.setGameWon(true);
        assertTrue(gameManager.isGameWon());
        gameManager.setGameWon(false);
        assertFalse(gameManager.isGameWon());
    }

    @Test
    void testGameDifficulty() {
        gameManager.setGameDifficulty("Hard");
        assertEquals("Hard", gameManager.getGameDifficulty());
    }

    @Test
    void testDifficultyBonus() {
        gameManager.setDifficultyBonus("Easy");
        assertEquals(0.75f, gameManager.getDifficultyBonus());
        gameManager.setDifficultyBonus("Hard");
        assertEquals(0.5f, gameManager.getDifficultyBonus());
    }

    @Test
    void testPlayer() {
        assertEquals(player, gameManager.getPlayer());
    }

    @Test
    void testUpcomingRound() {
        Round round = new Round(5, 5, 5, 5);
        gameManager.setUpcomingRound(round);
        assertEquals(round, gameManager.getUpcomingRound());
    }

    @Test
    void testTrackDistance() {
        gameManager.setTrackDistance(1000);
        assertEquals(1000, gameManager.getTrackDistance());
    }
}