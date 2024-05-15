package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;

public class InventoryScreenController {

    //attributes
    private GameManager gameManager;

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
    private Label inventorySelectedTowerTypeLabel;
    @FXML
    private Label inventorySelectedTowerCollectionAmountLabel;
    @FXML
    private Label inventorySelectedTowerCollectionSpeedLabel;
    @FXML
    private Button inventoryItem1Button, inventoryItem2Button, inventoryItem3Button, inventoryItem4Button;
    @FXML
    private Button inventorySelectedTower1Button, inventorySelectedTower2Button, inventorySelectedTower3Button, inventorySelectedTower4Button, inventorySelectedTower5Button;
    @FXML
    private Button inventorySelectedItem1Button, inventorySelectedItem2Button;
    @FXML
    private Label inventorySelectedItemNameLabel, inventorySelectedItemTypeLabel, inventorySelectedItemStatLabel;
    @FXML
    private Button inventoryStartButton, inventoryShopButton, inventorySellButton;


    // constructor
    public InventoryScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    // methods
    public void initialize() {

    }
}
