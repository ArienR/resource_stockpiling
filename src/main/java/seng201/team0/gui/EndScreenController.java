package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import seng201.team0.Player;

/**
 * The controller which manages the UI of the end screen, displaying a few of the player stats.
 */
public class EndScreenController {

    /**
     * The singleton GameManager instance.
     */
    GameManager gameManager;

    @FXML
    private Label moneyEarnedLabel;
    @FXML
    private Label roundsCompletedLabel;
    @FXML
    private Label scoreAchievedLabel;
    @FXML
    private Label gameOutcomeLabel;

    /**
     * Constructor initializes the controller with a reference to the game manager.
     *
     * @param gameManager The GameManager singleton instance.
     */
    EndScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Initialises the UI and displays a few of the players stats including their money,
     * total score, and final round achieved.
     */
    public void initialize() {
        Player player = gameManager.getPlayer();
        if (gameManager.isGameWon()){
            gameOutcomeLabel.setText("You Win");
        } else {
            gameOutcomeLabel.setText("You Lose");
        }

        moneyEarnedLabel.setText(String.format("You Survived %d/%d", gameManager.getCurrentRoundNumber()-1, gameManager.getNumberOfRounds()));
        roundsCompletedLabel.setText(String.format("You finished with a total of: $%d", player.getPlayerMoney()));
        scoreAchievedLabel.setText(String.format("You achieved a score of: %d", player.getPlayerScore()));
    }

    /**
     * Closes the application.
     */
    @FXML
    public void closeGameAction() {
        gameManager.closeEndScreen();
    }
}
