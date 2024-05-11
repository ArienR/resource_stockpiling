package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

import java.util.Arrays;
import java.util.Objects;

public class SetupScreenController {

    private GameManager gameManager;

    @FXML private TextField nameInputTextField;
    @FXML private Slider numberOfRoundsSlider;
    @FXML private Label selectedNumberOfRoundsLabel;
    @FXML private Button easyDifficultyButton;
    @FXML private Button hardDifficultyButton;
    @FXML private Label selectedDiffucltyLabel;
    @FXML private Button startGameButton;

    private String gameDifficulty;

    public SetupScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    public void initialize() {

//        Changes number of rounds based on slider position and updates selected rounds label
        numberOfRoundsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int roundedValue = (int) Math.round(newValue.doubleValue());
            selectedNumberOfRoundsLabel.setText("Selected number of rounds: " + roundedValue);
        });


//        Initializes game difficulty to easy
        gameDifficulty = "Easy";
        updateGameDifficultyLabel("Easy", easyDifficultyButton);

        easyDifficultyButton.setOnAction(event -> {
            gameDifficulty = "Easy";
            updateGameDifficultyLabel("Easy", easyDifficultyButton);
        });

        hardDifficultyButton.setOnAction(event -> {
            gameDifficulty = "Hard";
            updateGameDifficultyLabel("Hard", hardDifficultyButton);
        });

    }

    public void updateGameDifficultyLabel(String gameDifficulty, Button button) {
        selectedDiffucltyLabel.setText("Selected game difficulty: " + gameDifficulty);
        easyDifficultyButton.setStyle("");
        hardDifficultyButton.setStyle("");
        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 5;");
    }

    @FXML
    private void startGameAction() {
        gameManager.setName(nameInputTextField.getText());
        gameManager.setNumberOfRounds((int) numberOfRoundsSlider.getValue());
        gameManager.setGameDifficulty(gameDifficulty);
        gameManager.closeSetupScreen();
    }
}
