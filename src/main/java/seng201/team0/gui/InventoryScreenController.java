package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import java.util.List;

public class InventoryScreenController {

    //attributes
    private GameManager gameManager;

    @FXML private Label inventoryMessageLabel;
    @FXML private Button inventoryTower1Button;
    @FXML private Button inventoryTower2Button;
    @FXML private Button inventoryTower3Button;
    @FXML private Button inventoryTower4Button;
    @FXML private Button inventoryTower5Button;
    @FXML private Button inventoryTower6Button;
    @FXML private Button inventoryTower7Button;
    @FXML private Button inventoryTower8Button;
    @FXML private Label inventorySelectedTowerTypeLabel;
    @FXML private Label inventorySelectedTowerFillAmountLabel;
    @FXML private Label inventorySelectedTowerFillSpeedLabel;
    @FXML private Button inventoryItem1Button, inventoryItem2Button, inventoryItem3Button, inventoryItem4Button;
    @FXML private Button inventorySelectedTower1Button, inventorySelectedTower2Button, inventorySelectedTower3Button, inventorySelectedTower4Button, inventorySelectedTower5Button;
    @FXML private Button inventorySelectedItem1Button, inventorySelectedItem2Button;
    @FXML private Label inventorySelectedItemTypeLabel, inventorySelectedItemStatLabel;
    @FXML private Button inventoryStartButton, inventoryShopButton, inventorySellButton;

    private List<Button> towerButtons;
    private List<Button> itemButtons;
    private Tower selectedTower;
    private Item selectedItem;


    // constructor
    public InventoryScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    // methods
    @FXML
    public void initialize() {
        inventoryMessageLabel.setText(String.format("Select your Towers and Upgrades for the next round, %s.", gameManager.getPlayer().getName()));

        towerButtons = List.of(inventoryTower1Button, inventoryTower2Button, inventoryTower3Button, inventoryTower4Button, inventoryTower5Button, inventoryTower6Button, inventoryTower7Button, inventoryTower8Button);
        itemButtons = List.of(inventoryItem1Button, inventoryItem2Button, inventoryItem3Button, inventoryItem4Button);

        populateTowerButtons();
        populateItemButtons();
    }

    private void populateTowerButtons() {
        List<Tower> towers = gameManager.getPlayer().getTowerList();
        for (int i = 0; i < towerButtons.size(); i++) {
            if (i < towers.size()) {
                Tower tower = towers.get(i);
                towerButtons.get(i).setText(tower.getTowerName());
                towerButtons.get(i).setDisable(false);
                int finalI = i;
                towerButtons.get(i).setOnAction(event -> {
                    selectedTower = towers.get(finalI);
                    selectedItem = null;
                    displayTowerStats(selectedTower);
                    removeItemStats();
                    updateButtonStyles(towerButtons, towerButtons.get(finalI));
                    updateButtonStyles(itemButtons, null);
                });
            } else {
                towerButtons.get(i).setText("Empty");
                towerButtons.get(i).setDisable(true);
                towerButtons.get(i).setStyle("-fx-opacity: 0.5;");
            }
        }
    }

    private void populateItemButtons() {
        List<Item> items = gameManager.getPlayer().getItemList();
        for (int i = 0; i < itemButtons.size(); i++) {
            if (i < items.size()) {
                Item item = items.get(i);
                itemButtons.get(i).setText(item.getItemName());
                itemButtons.get(i).setDisable(false);
                int finalI = i;
                itemButtons.get(i).setOnAction(event -> {
                    selectedItem = items.get(finalI);
                    selectedTower = null;
                    displayItemStats(selectedItem);
                    removeTowerStats();
                    updateButtonStyles(itemButtons, itemButtons.get(finalI));
                    updateButtonStyles(towerButtons, null);
                });
            } else {
                itemButtons.get(i).setText("Empty");
                itemButtons.get(i).setDisable(true);
                itemButtons.get(i).setStyle("-fx-opacity: 0.5;");
            }
        }
    }

    private void updateButtonStyles(List<Button> buttons, Button selectedButton) {
        for (Button button : buttons) {
            if (button == selectedButton) {
                button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 30;");
            } else {
                button.setStyle("-fx-background-radius: 10;");
            }
        }
    }

    private void displayTowerStats(Tower tower) {
        inventorySelectedTowerFillAmountLabel.setText("Fill Amount: " + tower.getTowerFillAmount());
        inventorySelectedTowerFillSpeedLabel.setText("Fill Speed: " + tower.getTowerSpeed());
        inventorySelectedTowerTypeLabel.setText("Type: " + tower.getClass().getSimpleName());
    }

    private void removeTowerStats() {
        inventorySelectedTowerFillAmountLabel.setText("Fill Amount: ");
        inventorySelectedTowerFillSpeedLabel.setText("Fill Speed: ");
        inventorySelectedTowerTypeLabel.setText("Type: ");
    }

    private void displayItemStats(Item item) {
        inventorySelectedItemStatLabel.setText("Fill Increase: " + item.getCollectionIncrease() + "%\n" + "Speed Increase: " + item.getSpeedIncrease() + "%");
        inventorySelectedItemTypeLabel.setText("Affects: " + item.getTowerTypeAffected());
    }

    private void removeItemStats() {
        inventorySelectedItemStatLabel.setText("Fill Increase: " + "\n" + "Speed Increase: ");
        inventorySelectedItemTypeLabel.setText("Affects: ");
    }

    @FXML
    public void goToSellShop() {
        gameManager.launchSellShopScreen();
        gameManager.inventoryScreenToSellScreen();
    }

    @FXML
    public void goToBuyShop() {
        gameManager.launchSellShopScreen();
        gameManager.inventoryScreenToBuyScreen();
    }

    @FXML
    public void goToGameScreen() {
        gameManager.launchGameScreen();
        gameManager.inventoryScreenToGameScreen();
    }
}
