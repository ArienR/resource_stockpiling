package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
import seng201.team0.models.DairyCart;
import seng201.team0.models.MeatCart;
import seng201.team0.models.ProduceCart;
import seng201.team0.models.Tower;
import seng201.team0.services.GameStateService;
import seng201.team0.services.GenerateCartsService;

import java.util.ArrayList;
import java.util.List;

public class GameScreenController {

    private GameManager gameManager;

    private GameStateService gameStateService;

    private GenerateCartsService generateCartsService;

    private boolean roundWon = true;

    //maybe negligible
    private List<Label> gameTowers;

    private List<Object> gameTowerSpecs = new ArrayList<>();


    @FXML
    private Label roundTitleLabel;

    @FXML
    private Label produceCartsRemaining, meatCartsRemaining, dairyCartsRemaining;

    @FXML
    private Label gameTowerLabel0, gameTowerLabel1, gameTowerLabel2, gameTowerLabel3, gameTowerLabel4;

    // constructor
    public GameScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

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
        gameTowers = new ArrayList<>();
        gameTowers.add(gameTowerLabel0);
        gameTowers.add(gameTowerLabel1);
        gameTowers.add(gameTowerLabel2);
        gameTowers.add(gameTowerLabel3);
        gameTowers.add(gameTowerLabel4);
        populateTowers();
    }


    private void populateTowers() {
        List<Tower> towers = gameManager.getPlayer().getEquippedTowers();
        for (int i = 0; i < 5; i ++){
            populateTower(i, towers);
        }
    }

    @FXML
    private void populateTower(int i, List<Tower> towers){
        if (i < towers.size()) {
            gameTowers.get(i).setText(towers.get(i).getTowerName());
            // make gameTower into a List of type lists containing a Lists of type <T> containing all the tower name, size, speed, type etc.
        } else {
            gameTowers.get(i).setText("Empty");
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

