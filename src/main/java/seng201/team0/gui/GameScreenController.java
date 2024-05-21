package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import seng201.team0.GameManager;
import seng201.team0.models.*;
import seng201.team0.services.GameStateService;
import seng201.team0.services.GenerateCartsService;

import java.util.ArrayList;
import java.util.List;

public class GameScreenController {

    private GameManager gameManager;

    private GameStateService gameStateService;

    private GenerateCartsService generateCartsService;

    private boolean roundWon = true;

    @FXML
    private Label produceCartsRemaining, meatCartsRemaining, dairyCartsRemaining;

    @FXML
    private Circle tower1Circle, tower2Circle, tower3Circle, tower4Circle, tower5Circle;
    @FXML
    private Label tower1AmountLabel, tower1NameLabel, tower1SpeedLabel;
    @FXML
    private Label tower2AmountLabel, tower2NameLabel, tower2SpeedLabel;
    @FXML
    private Label tower3AmountLabel, tower3NameLabel, tower3SpeedLabel;
    @FXML
    private Label tower4AmountLabel, tower4NameLabel, tower4SpeedLabel;
    @FXML
    private Label tower5AmountLabel, tower5NameLabel, tower5SpeedLabel;

    @FXML
    private List<Label> towerAmountLabels;
    @FXML
    private List<Label> towerNameLabels;
    @FXML
    private List<Label> towerSpeedLabels;
    @FXML
    private List<Circle> towerCircles;

    @FXML
    private Label roundTitleLabel;

    // constructor
    public GameScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    @FXML
    public void initialize() {
        generateCartsService = new GenerateCartsService(gameManager);
        roundTitleLabel.setText(String.format("Round %d/%d", gameManager.getCurrentRoundNumber(), gameManager.getNumberOfRounds()));
        this.gameStateService = new GameStateService(gameManager);

        List<ProduceCart> listOfProduceCarts = generateCartsService.generateProduceCarts(gameManager);
        List<MeatCart> listOfMeatCarts = generateCartsService.generateMeatCarts(gameManager);
        List<DairyCart> listOfDairyCarts = generateCartsService.generateDairyCarts(gameManager);

        produceCartsRemaining.setText("Produce " + listOfProduceCarts.size() + "x");
        meatCartsRemaining.setText("Meat " + listOfMeatCarts.size() + "x");
        dairyCartsRemaining.setText("Dairy " + listOfDairyCarts.size() + "x");

        towerAmountLabels = List.of(tower1AmountLabel, tower2AmountLabel, tower3AmountLabel, tower4AmountLabel, tower5AmountLabel);
        towerNameLabels = List.of(tower1NameLabel, tower2NameLabel, tower3NameLabel, tower4NameLabel, tower5NameLabel);
        towerSpeedLabels = List.of(tower1SpeedLabel, tower2SpeedLabel, tower3SpeedLabel, tower4SpeedLabel, tower5SpeedLabel);
        towerCircles = List.of(tower1Circle, tower2Circle, tower3Circle, tower4Circle, tower5Circle);

        populateTowers();


    }

    private void populateTowers() {
        List<Tower> towers = gameManager.getPlayer().getEquippedTowers();
        for (int i = 0; i < towerAmountLabels.size(); i++) {
            if (i < towers.size()) {
                Tower tower = towers.get(i);

                setTowerLabels(i, tower.getTowerFillAmount(), tower.getTowerName(), tower.getTowerSpeed(), tower);
            } else {
                setTowerLabels(i, "Empty", "Empty", "Empty", null);
            }
        }
    }

    private void setTowerLabels(int index, Object fillAmount, Object name, Object speed, Tower tower) {
        towerAmountLabels.get(index).setText("Fill Amount: " + fillAmount + " Litres");
        towerNameLabels.get(index).setText(name.toString());
        towerSpeedLabels.get(index).setText("Speed: " + speed + " Fill/Second");

        // Set the circle color based on the tower type
        String color = getColorByType(tower);
        towerCircles.get(index).setFill(Color.web(color));
    }

    private String getColorByType(Tower tower) {
        if (tower instanceof ProduceTower) {
            return "##00C14A";
        } else if (tower instanceof MeatTower) {
            return "#FF5757";
        } else if (tower instanceof DairyTower) {
            return "#E3CCCC";
        } else {
            return "grey";
        }
    }

    @FXML
    public void roundLost() {
        gameManager.setRoundWon(false);
        gameStateService.isEndOfGame();
    }

    @FXML
    public void roundWon() {
        gameManager.setRoundWon(true);
        gameStateService.isEndOfGame();
    }
}

