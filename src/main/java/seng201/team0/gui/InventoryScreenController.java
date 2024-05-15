package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;

public class InventoryScreenController {

    //attributes
    @FXML
    private Label inventoryMessageLabel;

    @FXML
    private Button inventoryTower1Button;
    @FXML
    private Button inventoryTower2Button;
    @FXML
    private Button inventoryTower3Button;
    @FXML
    private Button inventoryTower4Button;
    @FXML
    private Button inventoryTower5Button;
    @FXML
    private Button inventoryTower6Button;

    @FXML
    private Button inventoryTower7Button;
    @FXML
    private Button inventoryTower8Button;
    @FXML
    private Label inventorySelectedTowerType;
    @FXML
    private Label inventorySelectedTowerCollectionAmount;
    @FXML
    private Label inventorySelectedTowerCollectionSpeed;
    @FXML
    private Button inventoryItem1Button, inventoryItem2Button, inventoryItem3Button, inventoryItem4Button;
    @FXML
    private Button inventorySelectedTower1, inventorySelectedTower2, inventorySelectedTower3, inventorySelectedTower4, inventorySelectedTower5;
    @FXML
    private Button inventorySelectedItem1, inventorySelectedItem2;
    @FXML
    private Label inventorySelectedItemNameLabel, inventorySelectedItemType, inventorySelectedItemStat;
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
