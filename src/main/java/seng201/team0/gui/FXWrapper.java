package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.gui.SetupScreenController;

import java.io.IOException;

public class FXWrapper {

    @FXML
    private Pane pane;
    private Stage stage;

    public void init(Stage stage) {
        this.stage = stage;
        Player player = new Player("");
        new GameManager(player, this::launchSetupScreen, this::launchUpcomingRoundScreen, this::launchInventoryScreen, this::clearPane);
    }


    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    public void launchSetupScreen(GameManager gameManager) {
        try {
            FXMLLoader setupScreenLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            setupScreenLoader.setControllerFactory(param -> new SetupScreenController(gameManager));
            Parent setupParent  = setupScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Setup Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchUpcomingRoundScreen(GameManager gameManager) {
        try {
            FXMLLoader upcomingRoundScreenLoader = new FXMLLoader(getClass().getResource("/fxml/upcoming_round_screen.fxml"));
            upcomingRoundScreenLoader.setControllerFactory(param -> new UpcomingRoundScreenController(gameManager));
            Parent setupParent = upcomingRoundScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Upcoming Round Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void launchInventoryScreen(GameManager gameManager) {
        try {
            FXMLLoader inventoryScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory_screen.fxml"));
            inventoryScreenLoader.setControllerFactory(param -> new InventoryScreenController(gameManager));
            Parent setupParent = inventoryScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Inventory Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
