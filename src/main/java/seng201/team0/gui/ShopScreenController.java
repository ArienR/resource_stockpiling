package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;

public class ShopScreenController {

    GameManager gameManager;

    @FXML Button buyTower1Button, buyTower2Button, buyTower3Button, buyTower4Button;
    @FXML Button buyItem1Button, buyItem2Button;
    @FXML Button confirmBuyButton;

    ShopScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    @FXML
    public void goToInventory() {
        gameManager.closeBuyShopScreen();
        gameManager.launchInventoryScreen();
    }
}
