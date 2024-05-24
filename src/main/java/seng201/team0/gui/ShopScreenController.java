package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.Item;
import seng201.team0.models.Purchasable;
import seng201.team0.models.Tower;
import seng201.team0.services.ShopService;

import java.util.List;

public class ShopScreenController {

    GameManager gameManager;
    private ShopService shopService;

    @FXML private Button buyTower1Button, buyTower2Button, buyTower3Button, buyTower4Button;
    @FXML private Button buyItem1Button, buyItem2Button, buyItem3Button;
    @FXML private Label selectedItemStatLabel;
    @FXML private Label selectedItemTypeLabel;
    @FXML private Label selectedTowerLevelLabel;
    @FXML private Label selectedTowerFillLabel;
    @FXML private Label selectedTowerSpeedLabel;
    @FXML private Label selectedTowerTypeLabel;
    @FXML private Label shopPlayerMoneyLabel;
    @FXML private Label selectedCostLabel;
    @FXML private Label insufficientFundsLabel, exceedingLimitLabel;

    private List<Button> towerButtons;
    private List<Button> itemButtons;
    private Tower selectedTower;
    private int selectedTowerIndex = -1;
    private Item selectedItem;
    private int selectedItemIndex = -1;


    ShopScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    @FXML
    private void initialize() {
        this.shopService = gameManager.getShop();
        shopPlayerMoneyLabel.setText("Money: $" + gameManager.getPlayer().getPlayerMoney());
        insufficientFundsLabel.setText("");
        exceedingLimitLabel.setText("");

        towerButtons = List.of(buyTower1Button, buyTower2Button, buyTower3Button, buyTower4Button);
        itemButtons = List.of(buyItem1Button, buyItem2Button, buyItem3Button);
        List<Tower> towers = shopService.getTowers();
        List<Item> items = shopService.getItems();

        populateTowerButtons(towers);
        populateItemButtons(items);
    }

    public void populateTowerButtons(List<Tower> towers) {
        Player player = gameManager.getPlayer();
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i;
            Tower tower = towers.get(i);
            towerButtons.get(i).setText(towers.get(i).getTowerName());
            towerButtons.get(i).setOnAction(event -> {
                selectedTower = towers.get(finalI);
                selectedTowerIndex = finalI;
                updateButtonStyles(towerButtons, towerButtons.get(finalI));
                resetItemSelection();
                displayTowerStats(selectedTower);
                selectedCostLabel.setText("Cost: $" + selectedTower.getBuyPrice());
            });

            if (player.getPurchasedTowers().contains(tower)) {
                disableButton(towerButtons.get(i));
            }
        }
    }

    public void populateItemButtons(List<Item> items) {
        Player player = gameManager.getPlayer();
        for (int i = 0; i < itemButtons.size(); i++) {
            int finalI = i;
            Item item = items.get(i);
            itemButtons.get(i).setText(items.get(i).getItemName());
            itemButtons.get(i).setOnAction(event -> {
                selectedItem = items.get(finalI);
                selectedItemIndex = finalI;
                updateButtonStyles(itemButtons, itemButtons.get(finalI));
                resetTowerSelection();
                displayItemStats(selectedItem);
                selectedCostLabel.setText("Cost: $" + selectedItem.getBuyPrice());
            });

            if (player.getPurchasedItems().contains(item)) {
                disableButton(itemButtons.get(i));
            }
        }
    }

    private void resetTowerSelection() {
        selectedTower = null;
        selectedTowerIndex = -1;
        updateButtonStyles(towerButtons, null);
    }

    private void resetItemSelection() {
        selectedItem = null;
        selectedItemIndex = -1;
        updateButtonStyles(itemButtons, null);
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
        selectedTowerFillLabel.setText("Fill Amount: " + tower.getTowerFillAmount() + " Litres");
        selectedTowerSpeedLabel.setText("Fill Speed: " + tower.getTowerSpeed() + " Fill/Second");
        selectedTowerTypeLabel.setText("Type: " + splitCamelCase(tower.getClass().getSimpleName()));

        selectedItemStatLabel.setText("");
        selectedItemTypeLabel.setText("");
    }

    private void displayItemStats(Item item) {
        selectedItemStatLabel.setText("Fill Increase: " + item.getCollectionIncrease() + "%\n" +
                "Speed Increase: " + item.getSpeedIncrease() + "%");
        selectedItemTypeLabel.setText("Affects: " + splitCamelCase(item.getTowerTypeAffected().getClass().getSimpleName()));
        selectedTowerLevelLabel.setText("");
        selectedTowerFillLabel.setText("");
        selectedTowerSpeedLabel.setText("");
        selectedTowerTypeLabel.setText("");
    }


    public static String splitCamelCase(String s) {
        return s.replaceAll("([a-z])([A-Z])", "$1 $2");
    }


    @FXML
    public void onConfirmBuy() {
        Player player = gameManager.getPlayer();

        if (selectedTower != null) {
            handleBuy(player.getTowerList(), player.getPurchasedTowers(), selectedTower, towerButtons.get(selectedTowerIndex), 8, "Maximum number of towers reached.");
            selectedTower = null;
            selectedTowerIndex = -1;

        } else if (selectedItem != null) {
            handleBuy(player.getItemList(), player.getPurchasedItems(), selectedItem, itemButtons.get(selectedItemIndex), 4, "Maximum number of items reached.");
            selectedItem = null;
            selectedItemIndex = -1;
        }
    }

    private <T> void handleBuy(List<T> playerInventoryList, List<T> purchasedList, Purchasable purchasable, Button button, int maxCount, String maxMessage) {
        if (playerInventoryList.size() >= maxCount) {
            exceedingLimitLabel.setText(maxMessage);
            return;
        }

        int cost = purchasable.getBuyPrice();
        Player player = gameManager.getPlayer();

        if (player.getPlayerMoney() >= cost) {
            playerInventoryList.add((T) purchasable);
            purchasedList.add((T) purchasable);
            player.setPlayerMoney(player.getPlayerMoney() - cost);
            updatePlayerMoneyLabel();
            disableButton(button);
            insufficientFundsLabel.setText("");
        } else {
            insufficientFundsLabel.setText("Insufficient funds");
        }
    }

    private void disableButton(Button button) {
        button.setDisable(true);
        button.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #a9a9a9; -fx-border-color: #b3b3b3; -fx-background-radius: 30;");
    }

    private void updatePlayerMoneyLabel() {
        shopPlayerMoneyLabel.setText("Money: " + gameManager.getPlayer().getPlayerMoney() + "$");
    }

    @FXML
    public void goToInventory() {
        gameManager.closeBuyShopScreen();
        gameManager.launchInventoryScreen();
    }
}