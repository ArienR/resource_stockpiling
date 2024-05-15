package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import seng201.team0.models.Round;

import java.util.*;

public class UpcomingRoundScreenController {

    private GameManager gameManager;
    private Round option1Round;
    private Round option2Round;
    private Round option3Round;
    private Round selectedRound;
    private int selectedRoundIndex = -1;

    private @FXML Label roundSelectTitleLabel;
    private @FXML Label option1ProduceCountLabel;
    private @FXML Label option1MeatCountLabel;
    private @FXML Label option1DairyCountLabel;
    private @FXML Label option1CartSizeLabel;
    private @FXML Label option1CartSpeedLabel;
    private @FXML Label option2ProduceCountLabel;
    private @FXML Label option2MeatCountLabel;
    private @FXML Label option2DairyCountLabel;
    private @FXML Label option2CartSizeLabel;
    private @FXML Label option2CartSpeedLabel;
    private @FXML Label option3ProduceCountLabel;
    private @FXML Label option3MeatCountLabel;
    private @FXML Label option3DairyCountLabel;
    private @FXML Label option3CartSizeLabel;
    private @FXML Label option3CartSpeedLabel;
    private @FXML Button selectOption1Button, selectOption2Button, selectOption3Button;
    private @FXML Button upcomingRoundContinueButton;

    public UpcomingRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
        this.option1Round = new Round(3, 3, 3, 3); // Params are placeholders for now
        this.option2Round = new Round(3, 3, 3, 3); // Params are placeholders for now
        this.option3Round = new Round(3, 3, 3, 3); // Params are placeholders for now
    }

    public void initialize() {
        List<Button> selectedRoundButtons = List.of(selectOption1Button, selectOption2Button, selectOption3Button);
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

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
