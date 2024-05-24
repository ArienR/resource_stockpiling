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

/**
 * Controller class for the Upcoming Round selection screen in the game.
 * This class handles the display and selection of upcoming round options,
 */
public class UpcomingRoundScreenController {

    /**
     * The GameManager singleton.
     */
    private final GameManager gameManager;
    private Round option1Round, option2Round, option3Round;
    private Round selectedRound;
    private int selectedRoundIndex = -1;
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

    /**
     * Constructor which initialises the controller with the game manager and generates the round options.
     *
     * @param tempGameManager The GameManager singleton.
     */
    public UpcomingRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
        generateRoundOptions();
    }

    /**
     * Initialises the controller and the general interface of the screen.
     */
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

    /**
     * Generates the round options based on the current round number from which the user will pick from.
     */
    public void generateRoundOptions() {
        Random cartSpeedPercentage = new Random();
        Random cartCount = new Random();
        int currentRoundNumber = gameManager.getCurrentRoundNumber();
        if (currentRoundNumber == 1) {
            option1Round = new Round(1,0,0, cartSpeedPercentage.nextInt(-2, 0));
            option2Round = new Round(2,0,0, cartSpeedPercentage.nextInt(-4, -2));
            option3Round = new Round(3,0,0, cartSpeedPercentage.nextInt(-7, -5));
        } else if (currentRoundNumber == 2){
            option1Round = new Round(cartCount.nextInt(2, 4),0,0, cartSpeedPercentage.nextInt(-2, 0));
            option2Round = new Round(cartCount.nextInt(3, 5),0,0, cartSpeedPercentage.nextInt(-5, -2));
            option3Round = new Round(cartCount.nextInt(4, 6),0,0, cartSpeedPercentage.nextInt(-7, -5));
        } else if (currentRoundNumber >= 3 && currentRoundNumber <= 6){
            option1Round = new Round(cartCount.nextInt(7, 10),0,0, cartSpeedPercentage.nextInt(-2, 0));
            option2Round = new Round(cartCount.nextInt(4, 7),cartCount.nextInt(1,3),0, cartSpeedPercentage.nextInt(-2, 2));
            option3Round = new Round(cartCount.nextInt(1, 4),cartCount.nextInt(2,4),0, cartSpeedPercentage.nextInt(-2, 2));
        } else if (currentRoundNumber >= 7 && currentRoundNumber <= 9){
            option1Round = new Round(cartCount.nextInt(7, 10),cartCount.nextInt(0,2),cartCount.nextInt(0,2), cartSpeedPercentage.nextInt(2, 5));
            option2Round = new Round(cartCount.nextInt(4, 7),cartCount.nextInt(4,6),0, cartSpeedPercentage.nextInt(0, 5));
            option3Round = new Round(cartCount.nextInt(2, 4),cartCount.nextInt(2,4),cartCount.nextInt(0,2), cartSpeedPercentage.nextInt(0, 5));
        } else if (currentRoundNumber >= 10 && currentRoundNumber <= 12){
            option1Round = new Round(cartCount.nextInt(7, 10),cartCount.nextInt(2,4),cartCount.nextInt(2,4), cartSpeedPercentage.nextInt(2, 5));
            option2Round = new Round(cartCount.nextInt(4, 7),cartCount.nextInt(5,7),cartCount.nextInt(2,4), cartSpeedPercentage.nextInt(0, 2));
            option3Round = new Round(0,cartCount.nextInt(4,6),cartCount.nextInt(4,6), cartSpeedPercentage.nextInt(-2, 2));
        } else if (currentRoundNumber >= 13 && currentRoundNumber <= 15){
            option1Round = new Round(cartCount.nextInt(1, 4),cartCount.nextInt(1,4),cartCount.nextInt(1,5), cartSpeedPercentage.nextInt(5, 7));
            option2Round = new Round(cartCount.nextInt(3, 5),cartCount.nextInt(3,5),cartCount.nextInt(3,5), cartSpeedPercentage.nextInt(0, 5));
            option3Round = new Round(cartCount.nextInt(0, 1),cartCount.nextInt(0,2),cartCount.nextInt(7,10), cartSpeedPercentage.nextInt(0, 5));
        }
    }

    /**
     * Updates the labels which display the number of produce carts for each round.
     */
    private void updateProduceCountLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);
        for (int i = 0; i < roundOptions.size(); i++) {
            optionProduceCountLabels.get(i).setText("Produce Count: " + roundOptions.get(i).getProduceCount() + "x");
        }
    }

    /**
     * Updates the labels which display the number of meat carts for each round.
     */
    private void updateMeatCountLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        for (int i = 0; i < roundOptions.size(); i++) {
            optionMeatCountLabels.get(i).setText("Meat Count: " + roundOptions.get(i).getMeatCount() + "x");
        }
    }

    /**
     * Updates the labels which display the number of produce dairy for each round.
     */
    private void updateDairyCountLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        for (int i = 0; i < roundOptions.size(); i++) {
            optionDairyCountLabels.get(i).setText("Dairy Count: " + roundOptions.get(i).getDairyCount() + "x");
        }
    }

    /**
     * Updates the labels which display the speed increase for a particular round.
     */
    private void updateCartSpeedLabels() {
        List<Round> roundOptions = List.of(option1Round, option2Round, option3Round);

        for (int i = 0; i < roundOptions.size(); i++) {
            int cartSpeed = roundOptions.get(i).getChangedCartSpeed();
            String sign = cartSpeed >= 0 ? "+" : "-";
            optionCartSpeedLabels.get(i).setText("Cart Speed: " + sign + Math.abs(cartSpeed) + "kph");
        }
    }

    /**
     * Loads a new fxml file and opens a popup displaying how difficult the game is.
     */
    @FXML
    private void openGameRulesPopup() {
        try {
            FXMLLoader newStageLoader = new FXMLLoader(getClass().getResource("/fxml/game_rules_popup.fxml"));
            GridPane root = newStageLoader.load();
            Scene modalScene = new Scene(root);
            Stage modalStage = new Stage();
            modalStage.setScene(modalScene);
            modalStage.setWidth(400);
            modalStage.setHeight(600);
            modalStage.setResizable(false);
            modalStage.setTitle("Cart Rules");
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(cartStatsPopupButton.getScene().getWindow());
            modalStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Continues to the inventory screen if a round has been chosen by the user.
     */
    @FXML
    private void continueToInventoryAction() {
        if (selectedRoundIndex != -1) {
            gameManager.setUpcomingRound(selectedRound);
            gameManager.launchInventoryScreen();
            gameManager.closeUpcomingRoundScreen();
        } else {
            selectRoundError.setText("Please select a round to continue");
        }
    }
}
