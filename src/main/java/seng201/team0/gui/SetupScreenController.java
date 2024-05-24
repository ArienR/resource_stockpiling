package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import javafx.scene.control.Slider;

/**
 * Controls the setup screen where the player will pick their name, number of rounds, and difficulty.
 */
public class SetupScreenController {

    private GameManager gameManager;

    @FXML private TextField nameInputTextField;
    @FXML private Slider numberOfRoundsSlider;
    @FXML private Label selectedNumberOfRoundsLabel;
    @FXML private Button easyDifficultyButton;
    @FXML private Button hardDifficultyButton;
    @FXML private Label selectedDiffucltyLabel;
    @FXML private Button startGameButton;
    @FXML private Label startErrorLabel;
    @FXML private Label selectedDistanceLabel1;

    private String gameDifficulty;

    /**
     * Constructor initialises the controller with a reference to the game manager
     *
     * @param tempGameManager The GameManager instance.
     */
    public SetupScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    /**
     * Initializes the setup screen by loading the UI accordingly.
     */
    public void initialize() {

//        Changes number of rounds based on slider position and updates selected rounds label
        numberOfRoundsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int roundedValue = (int) Math.round(newValue.doubleValue());
            selectedNumberOfRoundsLabel.setText("Selected number of rounds: " + roundedValue);
        });


//        Initializes game difficulty to easy
        gameDifficulty = "Easy";
        updateGameDifficultyLabel("Easy", easyDifficultyButton);
        gameManager.setTrackDistance(80);
        selectedDistanceLabel1.setText("Track distance: " + gameManager.getTrackDistance() +"m");

        easyDifficultyButton.setOnAction(event -> {
            gameDifficulty = "Easy";
            updateGameDifficultyLabel("Easy", easyDifficultyButton);
            gameManager.setTrackDistance(80);
            selectedDistanceLabel1.setText("Track distance: " + gameManager.getTrackDistance() +"m");
        });

        hardDifficultyButton.setOnAction(event -> {
            gameDifficulty = "Hard";
            updateGameDifficultyLabel("Hard", hardDifficultyButton);
            gameManager.setTrackDistance(50);
            selectedDistanceLabel1.setText("Track distance: " + gameManager.getTrackDistance() +"m");
        });

    }

    /**
     * Updates the game difficulty when one of game difficulty buttons are pressed.
     *
     * @param gameDifficulty The string indicating game difficulty.
     * @param button The button the user has selected.
     */
    public void updateGameDifficultyLabel(String gameDifficulty, Button button) {
        selectedDiffucltyLabel.setText("Selected game difficulty: " + gameDifficulty);
        easyDifficultyButton.setStyle("");
        hardDifficultyButton.setStyle("");
        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
    }

    /**
     * Continues to start the game where the player will first pick their upcoming round.
     */
    @FXML
    private void startGameAction() {
        String playerName = nameInputTextField.getText();
        if (playerName.length() > 15 || playerName.length() < 3){
            startErrorLabel.setText("Please enter a name between 3 and 15 characters");
        } else {
            gameManager.getPlayer().setName(nameInputTextField.getText());
            gameManager.getPlayer().setPlayerMoney(1500);
            gameManager.setNumberOfRounds((int) numberOfRoundsSlider.getValue());
            gameManager.setGameDifficulty(gameDifficulty);
            gameManager.setDifficultyBonus(gameDifficulty);
            gameManager.launchUpcomingRoundScreen();
            gameManager.closeSetupScreen();
        }
    }
}
