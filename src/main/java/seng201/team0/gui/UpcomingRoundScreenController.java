package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
import seng201.team0.GameManager;

public class UpcomingRoundScreenController {

    private GameManager gameManager;

    @FXML Label optionOneProduceCountLabel;
    @FXML Label optionOneMeatCountLabel;
    @FXML Label optionOneDairyCountLabel;
    @FXML Label optionOneCartSizeLabel;
    @FXML Label optionOneCartSpeedLabel;
    @FXML Label optionTwoProduceCountLabel;
    @FXML Label optionTwoMeatCountLabel;
    @FXML Label optionTwoDairyCountLabel;
    @FXML Label optionTwoCartSizeLabel;
    @FXML Label optionTwoCartSpeedLabel;
    @FXML Label optionThreeProduceCountLabel;
    @FXML Label optionThreeMeatCountLabel;
    @FXML Label optionThreeDairyCountLabel;
    @FXML Label optionThreeCartSizeLabel;
    @FXML Label optionThreeCartSpeedLabel;
    @FXML Button selectOptionOneButton;
    @FXML Button selectOptionTwoButton;
    @FXML Button selectOptionThreeButton;
    @FXML Button upcomingRoundContinueButton;

    public UpcomingRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    public void initialize() {
        FXWindow.getInstance().setMaximized();
    }

    @FXML
    private void continueToInventoryAction() {
        gameManager.launchInventoryScreen();
        gameManager.closeUpcomingRoundScreen();
    }

}
