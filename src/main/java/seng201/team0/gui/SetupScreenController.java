package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;

public class SetupScreenController {

    private GameManager gameManager;

    @FXML TextField nameInputTextField;
    @FXML Slider numberOfRoundsSlider;
    @FXML Button easyDifficultyButton;
    @FXML Button hardDifficultyButton;

    public SetupScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }
}
