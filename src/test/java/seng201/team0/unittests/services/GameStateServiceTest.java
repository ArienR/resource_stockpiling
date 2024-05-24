package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;

public class GameStateServiceTest {

    private Player player;
    private GameManager gameManager;

    @BeforeEach
    public void setupTest() {
        player = new Player("John Doe");
        gameManager = new GameManager(player);
    }

    @Test
    void checkIsEndOfGame() {

    }
}
