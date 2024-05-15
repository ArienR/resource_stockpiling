package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;

import javax.sound.midi.SysexMessage;

public class SellScreenController {

    private GameManager gameManager;

    private @FXML Button sellTower1Button, sellTower2Button, sellTower3Button, sellTower4Button, sellTower5Button, sellTower6Button, sellTower7Button, sellTower8Button;
    private @FXML Button sellItem1Button, sellItem2Button, sellItem3Button, sellItem4Button;
    private @FXML Label sellPriceSelectedObjectButton;
    private @FXML Button sellSelectedObjectButton, goToInventoryButton;
    private @FXML Label selectedTowerTypeLabel, selectedTowerCollectionAmountLabel, selectedTowerCollectionSpeedLabel;
    private @FXML Label selectedItemTypeLabel, selectedItemStatLabel;

    SellScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    public void goToInventory() {
        gameManager.closeSellShopScreen();
        gameManager.launchInventoryScreen();
    }
}
