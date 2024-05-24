package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryScreenController {

    //attributes
    private GameManager gameManager;

    @FXML private Label inventoryMessageLabel;
    @FXML private Label insufficientTowersLabel;
    @FXML private Button inventoryTower1Button;
    @FXML private Button inventoryTower2Button;
    @FXML private Button inventoryTower3Button;
    @FXML private Button inventoryTower4Button;
    @FXML private Button inventoryTower5Button;
    @FXML private Button inventoryTower6Button;
    @FXML private Button inventoryTower7Button;
    @FXML private Button inventoryTower8Button;
    @FXML private Label inventorySelectedTowerLevelLabel;
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
    private List<Button> selectedTowerButtons;
    private List<Button> selectedItemButtons;
    private Tower selectedTower;
    private Item selectedItem;
    private int selectedTowerIndex = -1;
    private int selectedItemIndex = -1;


    // constructor
    public InventoryScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    // methods
    @FXML
    public void initialize() {
        Player player = gameManager.getPlayer();
        player.setEquippedTowers(new ArrayList<>()); // Emptying equipped towers
        player.setEquippedItems(new ArrayList<>()); // Emptying equipped towers

        inventoryMessageLabel.setText(String.format("Select your Towers and Upgrades for the next round, %s.", player.getName()));
        insufficientTowersLabel.setText("");

        towerButtons = List.of(inventoryTower1Button, inventoryTower2Button, inventoryTower3Button, inventoryTower4Button, inventoryTower5Button, inventoryTower6Button, inventoryTower7Button, inventoryTower8Button);
        itemButtons = List.of(inventoryItem1Button, inventoryItem2Button, inventoryItem3Button, inventoryItem4Button);
        selectedTowerButtons = List.of(inventorySelectedTower1Button, inventorySelectedTower2Button, inventorySelectedTower3Button, inventorySelectedTower4Button, inventorySelectedTower5Button);
        selectedItemButtons = List.of(inventorySelectedItem1Button, inventorySelectedItem2Button);

        populateTowerButtons();
        populateItemButtons();
        setupEquipButtons();
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
                    selectedTowerIndex = finalI;
                    selectedItemIndex = -1;
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
                    selectedItemIndex = finalI;
                    selectedTowerIndex = -1;
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

    private void setupEquipButtons() {
        for (Button button : selectedTowerButtons) {
            button.setText("");
            button.setStyle("-fx-opacity: 0.5;");
        }
        for (Button button : selectedItemButtons) {
            button.setText("");
            button.setStyle("-fx-opacity: 0.5;");
        }
        for (int i = 0; i < selectedTowerButtons.size(); i++) {
            int finalI = i;
            selectedTowerButtons.get(i).setOnAction(event -> {
                if (selectedTower != null) {
                    equipTower(finalI);
                }
            });
        }
        for (int i = 0; i < selectedItemButtons.size(); i++) {
            int finalI = i;
            selectedItemButtons.get(i).setOnAction(event -> {
                if (selectedItem != null) {
                    equipItem(finalI);
                }
            });
        }
    }

    private void equipTower(int slot) {
        List<Tower> equippedTowers = gameManager.getPlayer().getEquippedTowers();
        if (!equippedTowers.contains(selectedTower)) {
            if (equippedTowers.size() < 5) {
                if (slot < equippedTowers.size()) {
                    equippedTowers.set(slot, selectedTower);
                } else {
                    equippedTowers.add(selectedTower);
                }
                selectedTowerButtons.get(slot).setText(selectedTower.getTowerName());
                selectedTowerButtons.get(slot).setStyle("-fx-opacity: 1;");
            } else {
                System.out.println("Maximum towers equipped.");
            }
        }
    }

    private void equipItem(int slot) {
        List<Item> equippedItems = gameManager.getPlayer().getEquippedItems();
        if (!equippedItems.contains(selectedItem)) {
            if (equippedItems.size() < 2) {
                if (slot < equippedItems.size()) {
                    equippedItems.set(slot, selectedItem);
                } else {
                    equippedItems.add(selectedItem);
                }
                selectedItemButtons.get(slot).setText(selectedItem.getItemName());
                selectedItemButtons.get(slot).setStyle("-fx-opacity: 1;");
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
        inventorySelectedTowerLevelLabel.setText("Tower Level: " + tower.getTowerLevel());
        inventorySelectedTowerFillAmountLabel.setText("Fill Amount: " + tower.getTowerFillAmount() + " Litres");
        inventorySelectedTowerFillSpeedLabel.setText("Fill Speed: " + tower.getTowerSpeed() + " Fill/Second");
        inventorySelectedTowerTypeLabel.setText("Type: " + splitCamelCase(tower.getClass().getSimpleName()));
    }

    private void removeTowerStats() {
        inventorySelectedTowerLevelLabel.setText("Tower Level: ");
        inventorySelectedTowerFillAmountLabel.setText("Fill Amount: ");
        inventorySelectedTowerFillSpeedLabel.setText("Fill Speed: ");
        inventorySelectedTowerTypeLabel.setText("Type: ");
    }

    private void displayItemStats(Item item) {
        inventorySelectedItemStatLabel.setText("Fill Increase: " + item.getCollectionIncrease() + "%\n" + "Speed Increase: " + item.getSpeedIncrease() + "%");
        inventorySelectedItemTypeLabel.setText("Affects: " + splitCamelCase(item.getTowerTypeAffected().getClass().getSimpleName()));
    }

    private void removeItemStats() {
        inventorySelectedItemStatLabel.setText("Fill Increase: " + "\n" + "Speed Increase: ");
        inventorySelectedItemTypeLabel.setText("Affects: ");
    }

    public static String splitCamelCase(String s) {
        return s.replaceAll("([a-z])([A-Z])", "$1 $2");
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
        if (!gameManager.getPlayer().getEquippedTowers().isEmpty()) {
            gameManager.launchGameScreen();
            gameManager.inventoryScreenToGameScreen();
        } else {
            insufficientTowersLabel.setText("Please select at least one tower to begin");
        }
    }
}
