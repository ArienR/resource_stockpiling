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
    @FXML
    private Label gameOutcomeLabel;

    EndScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        Player player = gameManager.getPlayer();
        if (gameManager.isGameWon()){
            gameOutcomeLabel.setText("You Win");
        } else {
            gameOutcomeLabel.setText("You Lose");
        }

        moneyEarnedLabel.setText(String.format("You Survived %d/%d", gameManager.getCurrentRoundNumber()-1, gameManager.getNumberOfRounds()));
        roundsCompletedLabel.setText(String.format("You earned a total of: %d", player.getTotalPlayerMoney()));
        scoreAchievedLabel.setText(String.format("You achieved a score of: %d", player.getPlayerScore()));
    }
    @FXML
    public void closeGameAction(){
    }
}
