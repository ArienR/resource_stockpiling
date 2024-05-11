package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
import seng201.team0.GameManager;

public class UpcomingRoundScreenController {

    private GameManager gameManager;

    @FXML Label optionOneCartTypeLabel;
    @FXML Label optionOneCartSizeLabel;
    @FXML Label optionOneNumberOfCartsLabel;
    @FXML Label optionTwoCartTypeLabel;
    @FXML Label optionTwoCartSizeLabel;
    @FXML Label optionTwoNumberOfCartsLabel;
    @FXML Label optionThreeCartTypeLabel;
    @FXML Label optionThreeCartSizeLabel;
    @FXML Label optionThreeNumberOfCartsLabel;
    @FXML Button selectOptionOneButton;
    @FXML Button selectOptionTwoButton;
    @FXML Button selectOptionThreeButton;
    @FXML Button upcomingRoundContinueButton;

    public UpcomingRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    public void initialize() {
        FXWindow.getInstance().setMaximized(true);
    }

    @FXML
    private void continueToInventoryAction() {
        gameManager.launchInventoryScreen();
        gameManager.closeUpcomingRoundScreen();
    }

}
