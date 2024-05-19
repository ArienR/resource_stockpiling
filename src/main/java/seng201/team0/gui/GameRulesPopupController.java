package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class GameRulesPopupController {

    @FXML Label cartStatsLabel;

    @FXML
    private void onCancel() {
        // close modal
        ((Stage) cartStatsLabel.getScene().getWindow()).close();
    }
}
