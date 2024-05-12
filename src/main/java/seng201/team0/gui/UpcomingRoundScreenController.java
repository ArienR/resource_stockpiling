package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
import seng201.team0.GameManager;

import java.util.*;

public class UpcomingRoundScreenController {

    private GameManager gameManager;
    private Round optionOneRound;
    private Round optionTwoRound;
    private Round optionThreeRound;
    private Round selectedRound;
    private int selectedRoundIndex = -1;

    private @FXML Label optionOneProduceCountLabel;
    private @FXML Label optionOneMeatCountLabel;
    private @FXML Label optionOneDairyCountLabel;
    private @FXML Label optionOneCartSizeLabel;
    private @FXML Label optionOneCartSpeedLabel;
    private @FXML Label optionTwoProduceCountLabel;
    private @FXML Label optionTwoMeatCountLabel;
    private @FXML Label optionTwoDairyCountLabel;
    private @FXML Label optionTwoCartSizeLabel;
    private @FXML Label optionTwoCartSpeedLabel;
    private @FXML Label optionThreeProduceCountLabel;
    private @FXML Label optionThreeMeatCountLabel;
    private @FXML Label optionThreeDairyCountLabel;
    private @FXML Label optionThreeCartSizeLabel;
    private @FXML Label optionThreeCartSpeedLabel;
    private @FXML Button selectOptionOneButton;
    private @FXML Button selectOptionTwoButton;
    private @FXML Button selectOptionThreeButton;
    private @FXML Button upcomingRoundContinueButton;

    public UpcomingRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
        this.optionOneRound = new Round(3, 3, 3); // Values are placeholders for now
        this.optionTwoRound = new Round(3, 3, 3); // Values are placeholders for now
        this.optionThreeRound = new Round(3, 3, 3); // Values are placeholders for now
    }

    public void initialize() {
        List<Button> selectedRoundButtons = List.of(selectOptionOneButton, selectOptionTwoButton, selectOptionThreeButton);
        List<Round> roundOptions = List.of(optionOneRound, optionTwoRound, optionThreeRound);

        // Changes colour of selected round and updates selectedRound variable to that of one clicked
        for (int i = 0; i < selectedRoundButtons.size(); i++) {
            int finalI = i;
            selectedRoundButtons.get(i).setOnAction(event -> {
                selectedRound = roundOptions.get(finalI);
                selectedRoundIndex = finalI;
                selectedRoundButtons.forEach(button -> {
                    if (button == selectedRoundButtons.get(finalI)) {
                        button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 30;");
                    } else {
                        button.setStyle("-fx-background-radius: 10;");
                    }
                });
            });
        }
    }

    @FXML
    private void continueToInventoryAction() {
        gameManager.launchInventoryScreen();
        gameManager.closeUpcomingRoundScreen();
    }

}
