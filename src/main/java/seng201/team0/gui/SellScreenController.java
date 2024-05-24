package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import javax.sound.midi.SysexMessage;
import java.util.List;

public class SellScreenController {

    private GameManager gameManager;

    private @FXML Label playerMoneyLabel;
    private @FXML Button sellTower1Button, sellTower2Button, sellTower3Button, sellTower4Button, sellTower5Button, sellTower6Button, sellTower7Button, sellTower8Button;
    private @FXML Button sellItem1Button, sellItem2Button, sellItem3Button, sellItem4Button;
    private @FXML Label sellPriceSelectedObjectLabel;
    private @FXML Button sellSelectedObjectButton, goToInventoryButton;
    private @FXML Label selectedTowerLevelLabel, selectedTowerTypeLabel, selectedTowerCollectionAmountLabel, selectedTowerCollectionSpeedLabel;
    private @FXML Label selectedItemTypeLabel, selectedItemStatLabel;

    private List<Button> towerButtons;
    private List<Button> itemButtons;
    private Tower selectedTower;
    private Item selectedItem;


    SellScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    @FXML
    public void initialize() {
        playerMoneyLabel.setText("Money: " + gameManager.getPlayer().getPlayerMoney());
        towerButtons = List.of(sellTower1Button, sellTower2Button, sellTower3Button, sellTower4Button, sellTower5Button, sellTower6Button, sellTower7Button, sellTower8Button);
        itemButtons = List.of(sellItem1Button, sellItem2Button, sellItem3Button, sellItem4Button);

        populateTowerButtons();
        populateItemButtons();

        sellSelectedObjectButton.setOnAction(event -> sellSelectedObject());
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
                    sellPriceSelectedObjectLabel.setText("Sell Price: " + selectedTower.getSellPrice() + "$");
                });
            } else {
                towerButtons.get(i).setText("");
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
                    sellPriceSelectedObjectLabel.setText("Sell Price: " + selectedItem.getSellPrice() + "$");
                });
            } else {
                itemButtons.get(i).setText("");
                itemButtons.get(i).setDisable(true);
                itemButtons.get(i).setStyle("-fx-opacity: 0.5;");
            }
        }
    }

    private void sellSelectedObject() {
        Player player = gameManager.getPlayer();

        if (selectedTower != null) {
            player.getTowerList().remove(selectedTower);
            player.setPlayerMoney(player.getPlayerMoney() + selectedTower.getSellPrice());
            populateTowerButtons();
            clearSelection();
        } else if (selectedItem != null) {
            player.getItemList().remove(selectedItem);
            player.setPlayerMoney(player.getPlayerMoney() + selectedItem.getSellPrice());
            populateItemButtons();
            clearSelection();
        }

        playerMoneyLabel.setText("Money: " + gameManager.getPlayer().getPlayerMoney() + "$");
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
        selectedTowerLevelLabel.setText("Tower Level: " + tower.getTowerLevel());
        selectedTowerCollectionAmountLabel.setText("Fill Amount: " + tower.getTowerFillAmount() + " Litres");
        selectedTowerCollectionSpeedLabel.setText("Fill Speed: " + tower.getTowerSpeed() + " Fill/Second");
        selectedTowerTypeLabel.setText("Type: " + splitCamelCase(tower.getClass().getSimpleName()));
    }

    private void removeTowerStats() {
        selectedTowerCollectionAmountLabel.setText("");
        selectedTowerCollectionSpeedLabel.setText("");
        selectedTowerTypeLabel.setText("");
    }

    private void displayItemStats(Item item) {
        selectedItemStatLabel.setText("Fill Increase: " + item.getCollectionIncrease() + "%\n" +
                "Speed Increase: " + item.getSpeedIncrease() + "%");
        selectedItemTypeLabel.setText("Affects: " + splitCamelCase(item.getTowerTypeAffected().getClass().getSimpleName()));
    }

    private void removeItemStats() {
        selectedItemStatLabel.setText("");
        selectedItemTypeLabel.setText("");
    }

    public static String splitCamelCase(String s) {
        return s.replaceAll("([a-z])([A-Z])", "$1 $2");
    }

    private void clearSelection() {
        selectedTower = null;
        selectedItem = null;
        sellPriceSelectedObjectLabel.setText("Sell Price: ");
        removeTowerStats();
        removeItemStats();
        updateButtonStyles(towerButtons, null);
        updateButtonStyles(itemButtons, null);
    }

    @FXML
    public void goToInventory() {
        gameManager.closeSellShopScreen();
        gameManager.launchInventoryScreen();
    }
}
