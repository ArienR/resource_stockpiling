package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * A popup on the upcoming round screen to assist players ot choose the upcoming round.
 */
public class GameRulesPopupController {

    @FXML Label cartStatsLabel;

    /**
     * Constructs a new GameRulesPopupController.
     */
    public GameRulesPopupController() {
        // Default constructor
    }

    /**
     * Closes the popup to show the user the carts' stats.
     */
    @FXML
    private void onCancel() {
        ((Stage) cartStatsLabel.getScene().getWindow()).close();
    }
}
