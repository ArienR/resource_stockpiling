package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;

public class InventoryScreenController {

    //attributes
    @FXML
    private Label inventoryMessageLabel;
    @FXML
    private Button inventoryTowerOneButton;
    @FXML
    private Button inventoryTowerTwoButton;
    @FXML
    private Button inventoryTowerThreeButton;
    @FXML
    private Button inventoryTowerFourButton;
    @FXML
    private Button inventoryTowerFiveButton;
    @FXML
    private Button inventoryTowerSixButton;

    @FXML
    private Button inventoryTowerSevenButton;
    @FXML
    private Button inventoryTowerEightButton;
    @FXML
    private Label inventorySelectedTowerTypeLabel;
    @FXML
    private Label inventorySelectedTowerCollectionAmountLabel;
    @FXML
    private Label inventorySelectedTowerCollectionSpeedLabel;
    @FXML
    private Button inventoryItemOneButton, inventoryItemTwoButton, inventoryItemThreeButton, inventoryItemFourButton;
    @FXML
    private Button inventorySelectedTowerOneButton, inventorySelectedTowerTwoButton, inventorySelectedTowerThreeButton, inventorySelectedTowerFourButton, inventorySelectedTowerFiveButton;
    @FXML
    private Button inventorySelectedItemOneButton, inventorySelectedItemTwoButton;
    @FXML
    private Label inventorySelectedItemNameLabel, inventorySelectedItemTypeLabel, inventorySelectedItemStatLabel;
    @FXML
    private Button inventoryStartButton, inventoryShopButton, inventorySellButton;

    private GameManager gameManager;


    // constructor
    public InventoryScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    // methods
    public void initialize() {

    }
}
