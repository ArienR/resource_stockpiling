package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
import seng201.team0.services.GameStateService;

public class GameScreenController {

    private GameManager gameManager;
    private GameStateService gameStateService;

    private boolean roundWon = true;

    @FXML
    private Label roundTitleLabel;


    // constructor
    public GameScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    public void initialize() {
        roundTitleLabel.setText(String.format("Round %d/%d", gameManager.getCurrentRoundNumber(), gameManager.getNumberOfRounds()));
        this.gameStateService = new GameStateService(gameManager);

    }

    @FXML
    public void roundLost() {
        gameManager.setRoundWon(false);
        checkGameEnd();
    }

    @FXML
    public void roundWon() {
        gameManager.setRoundWon(true);
        checkGameEnd();
    }

    public void checkGameEnd() {
        gameStateService.isEndOfGame();
    }
}

