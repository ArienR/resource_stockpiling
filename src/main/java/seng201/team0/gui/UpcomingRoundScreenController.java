package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import seng201.team0.GameManager;
import seng201.team0.models.Round;


import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

import java.util.*;

public class UpcomingRoundScreenController {

    private GameManager gameManager;
    private Round option1Round, option2Round, option3Round;
    private Round selectedRound;
    private int selectedRoundIndex = -1;

    private int easyCartSpeedReduction = 25;
    private Random cartSpeedPercentage;
    private List<Label> optionProduceCountLabels;

    private List<Label> optionMeatCountLabels;

    private List<Label> optionDairyCountLabels;
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
    private @FXML Label selectRoundError;

    private @FXML Button cartStatsPopupButton;


    public UpcomingRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
        generateRoundOptions();
    }

    public void initialize() {
        roundSelectTitleLabel.setText(String.format("Select Round %d/%d", gameManager.getCurrentRoundNumber(), gameManager.getNumberOfRounds()));
        List<Button> selectedRoundButtons = List.of(selectOption1Button, selectOption2Button, selectOption3Button);
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        optionProduceCountLabels = List.of(option1ProduceCountLabel, option2ProduceCountLabel, option3ProduceCountLabel);
        updateProduceCountLabels();

        optionMeatCountLabels = List.of(option1MeatCountLabel, option2MeatCountLabel, option3MeatCountLabel);
        updateMeatCountLabels();

        optionDairyCountLabels = List.of(option1DairyCountLabel, option2DairyCountLabel, option3DairyCountLabel);
        updateDairyCountLabels();

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
        Random cartCount = new Random();
        int currentRoundNumber = gameManager.getCurrentRoundNumber();
        if (gameManager.getGameDifficulty().equals("Hard")){
            easyCartSpeedReduction = 0;
        }
        if (currentRoundNumber == 1) {
            option1Round = new Round(1,0,0, cartSpeedPercentage.nextInt(-20, 0)-easyCartSpeedReduction);
            option2Round = new Round(2,0,0, cartSpeedPercentage.nextInt(-40, -25)-easyCartSpeedReduction);
            option3Round = new Round(3,0,0, cartSpeedPercentage.nextInt(-70, -50)-easyCartSpeedReduction);
        } else if (currentRoundNumber == 2){
            option1Round = new Round(cartCount.nextInt(2, 4),0,0, cartSpeedPercentage.nextInt(-25, -0)-easyCartSpeedReduction);
            option2Round = new Round(cartCount.nextInt(3, 5),0,0, cartSpeedPercentage.nextInt(-50, -25)-easyCartSpeedReduction);
            option3Round = new Round(cartCount.nextInt(4, 6),0,0, cartSpeedPercentage.nextInt(-75, -50)-easyCartSpeedReduction);
        } else if (currentRoundNumber >= 3 && currentRoundNumber <= 6){
            option1Round = new Round(cartCount.nextInt(7, 10),0,0, cartSpeedPercentage.nextInt(25, 50)-easyCartSpeedReduction);
            option2Round = new Round(cartCount.nextInt(4, 7),cartCount.nextInt(1,3),0, cartSpeedPercentage.nextInt(-25, 25)-easyCartSpeedReduction);
            option3Round = new Round(cartCount.nextInt(1, 4),cartCount.nextInt(2,4),0, cartSpeedPercentage.nextInt(-25, 25)-easyCartSpeedReduction);
        } else if (currentRoundNumber >= 7 && currentRoundNumber <= 9){
            option1Round = new Round(cartCount.nextInt(7, 10),cartCount.nextInt(0,2),cartCount.nextInt(0,2), cartSpeedPercentage.nextInt(25, 50)-easyCartSpeedReduction);
            option2Round = new Round(cartCount.nextInt(4, 7),cartCount.nextInt(4,6),0, cartSpeedPercentage.nextInt(0, 50)-easyCartSpeedReduction);
            option3Round = new Round(cartCount.nextInt(2, 4),cartCount.nextInt(2,4),cartCount.nextInt(0,2), cartSpeedPercentage.nextInt(0, 50)-easyCartSpeedReduction);
        } else if (currentRoundNumber >= 10 && currentRoundNumber <= 12){
            option1Round = new Round(cartCount.nextInt(7, 10),cartCount.nextInt(2,4),cartCount.nextInt(2,4), cartSpeedPercentage.nextInt(25, 50)-easyCartSpeedReduction);
            option2Round = new Round(cartCount.nextInt(4, 7),cartCount.nextInt(5,7),cartCount.nextInt(2,4), cartSpeedPercentage.nextInt(0, 25)-easyCartSpeedReduction);
            option3Round = new Round(0,cartCount.nextInt(4,6),cartCount.nextInt(4,6), cartSpeedPercentage.nextInt(-25, 25)-easyCartSpeedReduction);
        } else if (currentRoundNumber >= 13 && currentRoundNumber <= 15){
            option1Round = new Round(cartCount.nextInt(1, 4),cartCount.nextInt(1,4),cartCount.nextInt(1,5), cartSpeedPercentage.nextInt(50, 75)-easyCartSpeedReduction);
            option2Round = new Round(cartCount.nextInt(3, 5),cartCount.nextInt(3,5),cartCount.nextInt(3,5), cartSpeedPercentage.nextInt(0, 50)-easyCartSpeedReduction);
            option3Round = new Round(cartCount.nextInt(0, 1),cartCount.nextInt(0,2),cartCount.nextInt(7,10), cartSpeedPercentage.nextInt(0, 50)-easyCartSpeedReduction);
        }
    }

    private void updateProduceCountLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        for (int i = 0; i < roundOptions.size(); i++) {
            optionProduceCountLabels.get(i).setText("Produce Count: " + roundOptions.get(i).getProduceCount() + "x");
        }
    }

    private void updateMeatCountLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        for (int i = 0; i < roundOptions.size(); i++) {
            optionMeatCountLabels.get(i).setText("Meat Count: " + roundOptions.get(i).getMeatCount() + "x");
        }
    }

    private void updateDairyCountLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        for (int i = 0; i < roundOptions.size(); i++) {
            optionDairyCountLabels.get(i).setText("Dairy Count: " + roundOptions.get(i).getDairyCount() + "x");
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
    private void openGameRulesPopup() {
        try {
            // load a new fxml file
            FXMLLoader newStageLoader = new FXMLLoader(getClass().getResource("/fxml/game_rules_popup.fxml"));
            GridPane root = newStageLoader.load();
            Scene modalScene = new Scene(root);
            Stage modalStage = new Stage();
            modalStage.setScene(modalScene);
            modalStage.setWidth(400);
            modalStage.setHeight(600);
            modalStage.setResizable(false);
            modalStage.setTitle("Cart Rules");
            // If we want the modal to not block the other window we can change modality to Modality.NONE
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(cartStatsPopupButton.getScene().getWindow());
            // Show the modal and wait for it to be closed
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeCartStatsPopup(){

    }



    @FXML
    private void continueToInventoryAction() {
        if (selectedRoundIndex != -1) {
            gameManager.launchInventoryScreen();
            gameManager.closeUpcomingRoundScreen();
        } else {
            selectRoundError.setText("Please select a round to continue");
        }
    }

}
