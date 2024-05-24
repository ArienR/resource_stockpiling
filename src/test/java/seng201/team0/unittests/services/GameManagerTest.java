package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.Round;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    private GameManager gameManager;
    private Round round;


    @BeforeEach
    void setUp() {
        Player player = new Player("John Doe");
        gameManager = new GameManager(player);
        gameManager.setUpcomingRound(new Round(5, 5, 5, 5));
    }

    @Test
    void testLaunchInventoryScreenResetsRoundEndedFlag() {
        gameManager.isRoundEnded = true;

        gameManager.launchInventoryScreen();

        assertFalse(gameManager.isRoundEnded);
    }
}