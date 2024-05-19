package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng201.team0.GameManager;
import seng201.team0.models.DairyCart;
import seng201.team0.models.MeatCart;
import seng201.team0.models.ProduceCart;
import seng201.team0.services.GameStateService;
import seng201.team0.services.GenerateCartsService;

import java.util.List;

public class GameScreenController {

    private GameManager gameManager;

    private GameStateService gameStateService;

    private GenerateCartsService generateCartsService;


    private boolean roundWon = true;

    @FXML
    private Label roundTitleLabel;

    @FXML
    private Label produceCartsRemaining, meatCartsRemaining, dairyCartsRemaining;


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

