package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;
import seng201.team0.models.Item;
import seng201.team0.models.Tower;
import seng201.team0.services.Shop;

import java.util.List;

public class ShopScreenController {

    GameManager gameManager;
    private Shop shop;

    @FXML private Button buyTower1Button, buyTower2Button, buyTower3Button, buyTower4Button;
    @FXML private Button buyItem1Button, buyItem2Button;
    @FXML private Label selectedItemStatLabel;
    @FXML private Label selectedItemTypeLabel;
    @FXML private Label selectedTowerFillLabel;
    @FXML private Label selectedTowerSpeedLabel;
    @FXML private Label selectedTowerTypeLabel;
    @FXML private Label shopPlayerMoneyLabel;

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
        this.shop = gameManager.getShop();
        shopPlayerMoneyLabel.setText("Money: " + gameManager.getPlayer().getPlayerMoney() + "$");

        // Initialize lists of buttons
        towerButtons = List.of(buyTower1Button, buyTower2Button, buyTower3Button, buyTower4Button);
        itemButtons = List.of(buyItem1Button, buyItem2Button);
        List<Tower> towers = shop.getTowers();
        List<Item> items = shop.getItems();

        // Set up tower buttons
        for (int i = 0; i < towerButtons.size(); i++) {
            int finalI = i;
            towerButtons.get(i).setText(towers.get(i).getTowerName());
            towerButtons.get(i).setOnAction(event -> {
                selectedTower = towers.get(finalI);
                selectedTowerIndex = finalI;
                updateButtonStyles(towerButtons, towerButtons.get(finalI));
                resetItemSelection();
                displayTowerStats(selectedTower);
            });
        }

        for (int i = 0; i < itemButtons.size(); i++) {
            int finalI = i;
            itemButtons.get(i).setText(items.get(i).getItemName());
            itemButtons.get(i).setOnAction(event -> {
                selectedItem = items.get(finalI);
                selectedItemIndex = finalI;
                updateButtonStyles(itemButtons, itemButtons.get(finalI));
                resetTowerSelection();
                displayItemStats(selectedItem);
            });
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
        selectedTowerFillLabel.setText("Fill Amount: " + tower.getTowerFillAmount());
        selectedTowerSpeedLabel.setText("Fill Speed: " + tower.getTowerSpeed());
        selectedTowerTypeLabel.setText("Type: " + tower.getClass().getSimpleName());

        selectedItemStatLabel.setText("");
        selectedItemTypeLabel.setText("");
    }

    private void displayItemStats(Item item) {
        selectedItemStatLabel.setText("Fill Increase: " + item.getCollectionIncrease() + "%" + ", Speed Increase: " + item.getSpeedIncrease() + "%");
        selectedItemTypeLabel.setText("Affects: " + item.getTowerTypeAffected());

        selectedTowerFillLabel.setText("");
        selectedTowerSpeedLabel.setText("");
        selectedTowerTypeLabel.setText("");
    }

//    @FXML
//    public void onConfirmBuy() {
//        System.out.println("Buy confirmed");
//    }

    @FXML
    public void goToInventory() {
        gameManager.closeBuyShopScreen();
        gameManager.launchInventoryScreen();
    }
}