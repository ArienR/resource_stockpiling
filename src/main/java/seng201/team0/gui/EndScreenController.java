package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import seng201.team0.Player;

public class EndScreenController {

    GameManager gameManager;

    @FXML
    private Label moneyEarnedLabel;
    @FXML
    private Label roundsCompletedLabel;
    @FXML
    private Label scoreAchievedLabel;

    EndScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        Player player = gameManager.getPlayer();

        moneyEarnedLabel.setText(String.format("You Survived %d/%d", gameManager.getCurrentRoundNumber(), gameManager.getNumberOfRounds()));
        roundsCompletedLabel.setText(String.format("You earned a total of: %d", player.getTotalPlayerMoney()));
        scoreAchievedLabel.setText(String.format("You achieved a score of: %d", player.getPlayerScore()));
    }
}
