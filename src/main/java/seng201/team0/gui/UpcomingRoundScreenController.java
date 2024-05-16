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
    private Random cartSpeedPercentage;
    private List<Label> optionProduceCountLabels;
    private List<Label> optionCartSpeedLabels;

    private @FXML Label roundSelectTitleLabel;
    private @FXML Label option1ProduceCountLabel;
    private @FXML Label option1MeatCountLabel;
    private @FXML Label option1DairyCountLabel;
    private @FXML Label option1CartSpeedLabel;
    private @FXML Label option2ProduceCountLabel;
    private @FXML Label option2MeatCountLabel;
    private @FXML Label option2DairyCountLabel;
    private @FXML Label option2CartSpeedLabel;
    private @FXML Label option3ProduceCountLabel;
    private @FXML Label option3MeatCountLabel;
    private @FXML Label option3DairyCountLabel;
    private @FXML Label option3CartSpeedLabel;
    private @FXML Button selectOption1Button, selectOption2Button, selectOption3Button;
    private @FXML Button upcomingRoundContinueButton;

    public UpcomingRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
        Random cartSpeedPercentage = new Random();
//        this.option1Round = new Round(3, cartSpeedPercentage.nextInt(-15, 15)); // Params are placeholders for now
//        this.option2Round = new Round(3, cartSpeedPercentage.nextInt(-15, 15)); // Params are placeholders for now
//        this.option3Round = new Round(3, cartSpeedPercentage.nextInt(-15, 15)); // Params are placeholders for now
        generateRoundOptions();
    }

    public void initialize() {
        List<Button> selectedRoundButtons = List.of(selectOption1Button, selectOption2Button, selectOption3Button);
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        optionProduceCountLabels = List.of(option1ProduceCountLabel, option2ProduceCountLabel, option3ProduceCountLabel);
        updateProduceCountLabels();

        optionCartSpeedLabels = List.of(option1CartSpeedLabel, option2CartSpeedLabel, option3CartSpeedLabel);
        updateCartSpeedLabels();

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

    public void generateRoundOptions() {
        Random cartSpeedPercentage = new Random();
        int currentRoundNumber = gameManager.getCurrentRoundNumber();

        switch (currentRoundNumber) {
            case 1:
                option1Round = new Round(3, cartSpeedPercentage.nextInt(-15, 15));
                option2Round = new Round(3, cartSpeedPercentage.nextInt(-15, 15));
                option3Round = new Round(3, cartSpeedPercentage.nextInt(-15, 15));
                break;
            case 2:
                option1Round = new Round(5, cartSpeedPercentage.nextInt(-20, 20));
                option2Round = new Round(5, cartSpeedPercentage.nextInt(-15, 15));
                option3Round = new Round(5, cartSpeedPercentage.nextInt(-15, 15));
                break;
            // Add more cases as needed
            default:
                // Handle cases beyond the defined rounds
                break;
        }
    }

    private void updateProduceCountLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        for (int i = 0; i < roundOptions.size(); i++) {
            optionProduceCountLabels.get(i).setText("Produce Count: " + roundOptions.get(i).getProduceCount() + "x");
        }
    }

    private void updateCartSpeedLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        for (int i = 0; i < roundOptions.size(); i++) {
            int cartSpeed = roundOptions.get(i).getCartSpeedPercentage();
            String sign = cartSpeed >= 0 ? "+" : "-";
            optionCartSpeedLabels.get(i).setText("Cart Speed: " + sign + Math.abs(cartSpeed) + "%");
        }
    }

    @FXML
    private void continueToInventoryAction() {
        gameManager.launchInventoryScreen();
        gameManager.closeUpcomingRoundScreen();
    }

}
