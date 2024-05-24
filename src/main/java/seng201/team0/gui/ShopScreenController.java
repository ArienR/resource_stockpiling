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

/**
 * The controller which manages the UI of the shop and handles the buying action
 * for items and towers.
 */
public class ShopScreenController {

    /**
     * The singleton GameManager instance.
     */
    GameManager gameManager;

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

    /**
     * The list of tower buttons.
     */
    private List<Button> towerButtons;

    /**
     * The list of item buttons
     */
    private List<Button> itemButtons;

    /**
     * The tower the player has selected on the screen.
     */
    private Tower selectedTower;

    /**
     * The corresponding index to the tower the player has selected.
     */
    private int selectedTowerIndex = -1;

    /**
     * The item the player has selected on the screen.
     */
    private Item selectedItem;

    /**
     * The corresponding index to the item the player has selected.
     */
    private int selectedItemIndex = -1;

    /**
     * Constructor initializes the controller with a reference to the game manager.
     *
     * @param tempGameManager The GameManager singleton instance.
     */
    ShopScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    /**
     * Initializes the shop screen by loading available towers and items and updating the UI accordingly.
     */
    @FXML
    private void initialize() {
        ShopService shopService = gameManager.getShop();
        shopPlayerMoneyLabel.setText("Money: $" + gameManager.getPlayer().getPlayerMoney());
        insufficientFundsLabel.setText("");
        exceedingLimitLabel.setText("");

        towerButtons = List.of(buyTower1Button, buyTower2Button, buyTower3Button, buyTower4Button);
        itemButtons = List.of(buyItem1Button, buyItem2Button, buyItem3Button);
        List<Tower> towers = shopService.getTowers();
        List<Item> items = shopService.getItems();

        populateTowerButtons(towers);

        // Disable item buttons for the first round
        if (gameManager.getCurrentRoundNumber() >= 2) {
            populateItemButtons(items);
        } else {
            disableItemButtons();
        }
    }

    /**
     * Disables buttons for purchasing items. Typically called when items are not available or permissible to purchase.
     */
    private void disableItemButtons() {
        for (Button button : itemButtons) {
            button.setDisable(true);
            button.setText("Locked");
        }
    }

    /**
     * Populates tower purchase buttons with available towers and sets up their event handlers.
     *
     * @param towers The list of towers available for purchase.
     */
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

    /**
     * Populates item purchase buttons with available items and sets up their event handlers.
     *
     * @param items The list of items available for purchase.
     */
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

    /**
     * Resets the selection of towers after a tower is purchased or deselected.
     */
    private void resetTowerSelection() {
        selectedTower = null;
        selectedTowerIndex = -1;
        updateButtonStyles(towerButtons, null);
    }

    /**
     * Resets the selection of items after an item is purchased or deselected.
     */
    private void resetItemSelection() {
        selectedItem = null;
        selectedItemIndex = -1;
        updateButtonStyles(itemButtons, null);
    }

    /**
     * Updates the style of buttons to reflect selection status.
     *
     * @param buttons The list of buttons to update.
     * @param selectedButton The button that is currently selected.
     */
    private void updateButtonStyles(List<Button> buttons, Button selectedButton) {
        for (Button button : buttons) {
            if (button == selectedButton) {
                button.setStyle("-fx-background-color: #b3b3b3; -fx-background-radius: 30;");
            } else {
                button.setStyle("-fx-background-radius: 10;");
            }
        }
    }

    /**
     * Displays statistics for a selected tower.
     *
     * @param tower The tower whose stats are to be displayed.
     */
    private void displayTowerStats(Tower tower) {
        selectedTowerLevelLabel.setText("Tower Level: " + tower.getTowerLevel());
        selectedTowerFillLabel.setText("Fill Amount: " + tower.getTowerFillAmount() + " Litres");
        selectedTowerSpeedLabel.setText("Fill Speed: " + tower.getTowerSpeed() + " Fill/Second");
        selectedTowerTypeLabel.setText("Type: " + splitCamelCase(tower.getClass().getSimpleName()));

        selectedItemStatLabel.setText("");
        selectedItemTypeLabel.setText("");
    }

    /**
     * Displays statistics for a selected item.
     *
     * @param item The item whose stats are to be displayed.
     */
    private void displayItemStats(Item item) {
        selectedItemStatLabel.setText("Fill Increase: " + item.getFillIncrease() + "%\n" +
                "Speed Increase: " + item.getSpeedIncrease() + "%");
        selectedItemTypeLabel.setText("Affects: " + splitCamelCase(item.getTowerTypeAffected().getClass().getSimpleName()));
        selectedTowerLevelLabel.setText("");
        selectedTowerFillLabel.setText("");
        selectedTowerSpeedLabel.setText("");
        selectedTowerTypeLabel.setText("");
    }

    /**
     * Splits camel case strings into human-readable format.
     *
     * @param s The string to be split.
     * @return The reformatted string.
     */
    public static String splitCamelCase(String s) {
        return s.replaceAll("([a-z])([A-Z])", "$1 $2");
    }

    /**
     * Handles the confirmation of a purchase and updates inventory and player money.
     */
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

    /**
     * Generic method to handle the buying process for towers or items, updating inventory and UI.
     *
     * @param playerInventoryList The player's inventory list.
     * @param purchasedList List of purchased items or towers.
     * @param purchasable The item or tower to purchase.
     * @param button The button associated with the purchase.
     * @param maxCount The maximum allowed count of the items or towers.
     * @param maxMessage The message to display if the max count is exceeded.
     * @param <T> The type of the purchasable object.
     */
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

    /**
     * Disables a button and adjusts its style to reflect its disabled state.
     *
     * @param button The button to disable.
     */
    private void disableButton(Button button) {
        button.setDisable(true);
        button.setStyle("-fx-background-color: #d3d3d3; -fx-text-fill: #a9a9a9; -fx-border-color: #b3b3b3; -fx-background-radius: 30;");
    }

    /**
     * Updates the label showing the player's current money.
     */
    private void updatePlayerMoneyLabel() {
        shopPlayerMoneyLabel.setText("Money: " + gameManager.getPlayer().getPlayerMoney() + "$");
    }

    /**
     * Goes back to the inventory screen when button is pressed.
     */
    @FXML
    public void goToInventory() {
        gameManager.closeBuyShopScreen();
        gameManager.launchInventoryScreen();
    }
}