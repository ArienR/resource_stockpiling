package seng201.team0.gui;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import seng201.team0.GameManager;
import seng201.team0.models.*;
import seng201.team0.services.GameStateService;
import seng201.team0.services.GenerateCartsService;
import javafx.scene.shape.Rectangle;
import seng201.team0.services.RoundLogicService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameScreenController {

    private GameManager gameManager;

    private GameStateService gameStateService;
    private RoundLogicService roundLogicService;
    private GenerateCartsService generateCartsService;

    private final int MILLISECONDS_CONVERSION_FACTOR = 1000;
    private final float KPH_TO_MPS = 3.6f;

    private int activeCarts = 0;

    private List<ProduceCart> listOfProduceCarts;
    private List<MeatCart> listOfMeatCarts;
    private List<DairyCart> listOfDairyCarts;

    private int changedCartSpeed;

    @FXML
    private AnchorPane cartTrackAnchorPane;
    @FXML
    private Label produceCartsRemainingLabel, meatCartsRemainingLabel, dairyCartsRemainingLabel;
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

    @FXML
    private Button startRoundButton;

    // constructor
    public GameScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    @FXML
    public void initialize() {
        generateCartsService = new GenerateCartsService(gameManager);
        roundTitleLabel.setText(String.format("Round %d/%d", gameManager.getCurrentRoundNumber(), gameManager.getNumberOfRounds()));
        this.gameStateService = new GameStateService(gameManager);
        this.roundLogicService = new RoundLogicService((gameManager));

        this.listOfProduceCarts = generateCartsService.generateProduceCarts(gameManager);
        this.listOfMeatCarts = generateCartsService.generateMeatCarts(gameManager);
        this.listOfDairyCarts = generateCartsService.generateDairyCarts(gameManager);
        this.changedCartSpeed = generateCartsService.getChangedCartSpeed();

        produceCartsRemainingLabel.setText("Produce " + listOfProduceCarts.size() + "x");
        meatCartsRemainingLabel.setText("Meat " + listOfMeatCarts.size() + "x");
        dairyCartsRemainingLabel.setText("Dairy " + listOfDairyCarts.size() + "x");

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
                setTowerLabels(i, "", "", "", null);
            }
        }
    }

    private void setTowerLabels(int index, Object fillAmount, Object name, Object speed, Tower tower) {
        if (fillAmount != "") {
            towerAmountLabels.get(index).setText("Fill Amount: " + fillAmount + " Litres");
            towerNameLabels.get(index).setText(name.toString());
            towerSpeedLabels.get(index).setText("Speed: " + speed + " Fill/Second");

            // Set the circle color based on the tower type
            String color = getColorByType(tower);
            towerCircles.get(index).setFill(Color.web(color));
        } else {
            towerAmountLabels.get(index).setText("");
            towerNameLabels.get(index).setText(name.toString());
            towerSpeedLabels.get(index).setText("");

            // Set the circle color based on the tower type
            String color = getColorByType(tower);
            towerCircles.get(index).setFill(Color.web(color));
        }
    }

    private String getColorByType(Tower tower) {
        if (tower instanceof ProduceTower) {
            return "#00C14A";
        } else if (tower instanceof MeatTower) {
            return "#FF5757";
        } else if (tower instanceof DairyTower) {
            return "#E3CCCC";
        } else {
            return "#737171";
        }
    }

    // fillCarts
    @FXML
    public void startRound() {
        startRoundButton.setDisable(true);
        startRoundButton.setVisible(false);
        cartTrackAnchorPane.setVisible(true);
        startCartAnimations();
    }


    public void startCartAnimations() {
        ScheduledExecutorService animateCartsExecutor = Executors.newSingleThreadScheduledExecutor();

        // Schedule the task 5 times with a delay of 2 seconds between each execution
        for (int i = 0; i < listOfProduceCarts.size(); i++) {
            int index = i;
            animateCartsExecutor.schedule(() -> spawnCart(listOfProduceCarts.get(index)), i * 1000, TimeUnit.MILLISECONDS);
        }
        for (int i = 0; i < listOfMeatCarts.size(); i++) {
            int index = i;
            animateCartsExecutor.schedule(() -> spawnCart(listOfMeatCarts.get(index)), i * 1000, TimeUnit.MILLISECONDS);
        }
        for (int i = 0; i < listOfDairyCarts.size(); i++) {
            int index = i;
            animateCartsExecutor.schedule(() -> spawnCart(listOfDairyCarts.get(index)), i * 1000, TimeUnit.MILLISECONDS);
        }

        // Shutdown the executor after all tasks are completed
        animateCartsExecutor.shutdown();
    }


    public void spawnCart(Cart cart) {
        Platform.runLater(() -> {
            Rectangle cartGui;
            if (cart instanceof ProduceCart) {
                cartGui = new Rectangle(30, 30, 30, 30);
                cartGui.setLayoutY(5);
                cartGui.setLayoutX(10);
                cartGui.setFill(Color.GREEN);
            } else if (cart instanceof MeatCart) {
                cartGui = new Rectangle(40, 40, 40, 40);
                cartGui.setLayoutY(-10);
                cartGui.setLayoutX(0);
                cartGui.setFill(Color.RED);
            } else {
                cartGui = new Rectangle(50, 50, 50, 50);
                cartGui.setLayoutY(-25);
                cartGui.setLayoutX(-10);
                cartGui.setFill(Color.WHITESMOKE);
            }

            try {
                cartTrackAnchorPane.getChildren().add(cartGui);
            } catch (Exception e) {
                e.printStackTrace();
            }
            float totalSpeed = cart.cartSpeed + changedCartSpeed;
            float totalDistance = gameManager.getTrackDistance();
            float totalTime = totalDistance/(totalSpeed/KPH_TO_MPS);
            int timeToReachEnd = (int) (totalTime * MILLISECONDS_CONVERSION_FACTOR);
            //animate cart
            activeCarts += 1;
            animateCart(cartGui, timeToReachEnd);
        });
    }

    public void animateCart(Rectangle cartGui, int timeToReachEnd) {
        TranslateTransition translateCart = new TranslateTransition(Duration.millis(timeToReachEnd), cartGui);
        translateCart.setByX(1050);

        translateCart.setOnFinished(event -> {
            activeCarts -= 1;
            if (activeCarts == 0 && roundLogicService.canWinRound()){
                roundWon();
            } else if (activeCarts == 0 && !roundLogicService.canWinRound()){
                roundLost();
            }
        });

        translateCart.play();
    }

    @FXML
    public void roundLost() {
        gameManager.setRoundWon(false);
        gameStateService.isEndOfGame();
    }


    public void roundWon() {
        gameManager.setRoundWon(true);
        gameStateService.isEndOfGame();
    }
}
