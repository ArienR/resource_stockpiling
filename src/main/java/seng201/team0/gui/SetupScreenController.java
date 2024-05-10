package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.*;

public class SetupScreenController {

    private GameManager gameManager;

    @FXML TextField nameInputTextField;
    @FXML Slider numberRoundsSlider;
    @FXML Button easyDifficultyButton;
    @FXML Button hardDifficultyButton;

    SetupScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
