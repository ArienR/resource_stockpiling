package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class GameRulesPopupController {

    @FXML Label cartStatsLabel;

    /**
     * Closes the popup to show the user the carts' stats.
     */
    @FXML
    private void onCancel() {
        ((Stage) cartStatsLabel.getScene().getWindow()).close();
    }
}
