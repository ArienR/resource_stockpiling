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

/**
 * Holds all the launchers for the screen to assure they launch with their corresponding fxml files.
 */
public class FXWrapper {

    /**
     * Constructs a new FXWrapper instance.
     */
    public FXWrapper() {
        // Default constructor
    }

    @FXML
    private Pane pane;
    private Stage stage;

    /**
     * Initialises the stage on which the application will run.
     * @param stage The application stage.
     */
    public void init(Stage stage) {
        this.stage = stage;
        Player player = new Player("");
        new GameManager(player, this::launchSetupScreen, this::launchUpcomingRoundScreen, this::launchInventoryScreen,
                this::launchSellShopScreen, this::launchBuyShopScreen, this::launchGameScreen, this::launchAfterRoundScreen,
                this::launchEndScreen, this::clearPane);
    }

    /**
     * Clears the pane the screens appear on.
     */
    public void clearPane() {
        pane.getChildren().removeAll(pane.getChildren());
    }

    /**
     * Launches the setup screen and its corresponding fxml file.
     *
     * @param gameManager The GameManager singleton instance.
     */
    public void launchSetupScreen(GameManager gameManager) {
        try {
            FXMLLoader setupScreenLoader = new FXMLLoader(getClass().getResource("/fxml/setup_screen.fxml"));
            setupScreenLoader.setControllerFactory(param -> new SetupScreenController(gameManager));
            Parent setupParent = setupScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Setup Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the upcoming round screen and its corresponding fxml file.
     *
     * @param gameManager The GameManager singleton instance.
     */
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

    /**
     * Launch the inventory screen and its corresponding fxml file.
     *
     * @param gameManager The GameManager singleton instance.
     */
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

    /**
     * Launches the sell screen and its corresponding fxml file.
     *
     * @param gameManager The GameManager singleton instance.
     */
    public void launchSellShopScreen(GameManager gameManager) {
        try {
            FXMLLoader sellShopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/sell_screen.fxml"));
            sellShopScreenLoader.setControllerFactory(param -> new SellScreenController(gameManager));
            Parent setupParent = sellShopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Sell ShopService Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the inventory buy screen and its corresponding fxml file.
     *
     * @param gameManager The GameManager singleton instance.
     */
    public void launchBuyShopScreen(GameManager gameManager) {
        try {
            FXMLLoader buyShopScreenLoader = new FXMLLoader(getClass().getResource("/fxml/shop_screen.fxml"));
            buyShopScreenLoader.setControllerFactory(param -> new ShopScreenController(gameManager));
            Parent setupParent = buyShopScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Buy ShopService Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the game screen and its corresponding fxml file.
     *
     * @param gameManager The GameManager singleton instance.
     */
    public void launchGameScreen(GameManager gameManager){
        try {
            FXMLLoader gameScreenLoader = new FXMLLoader(getClass().getResource("/fxml/game_screen.fxml"));
            gameScreenLoader.setControllerFactory(param -> new GameScreenController(gameManager));
            Parent setupParent = gameScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launches the inventory screen and its corresponding fxml file.
     *
     * @param gameManager The GameManager singleton instance.
     */
    public void launchAfterRoundScreen(GameManager gameManager) {
        try {
            FXMLLoader afterRoundScreenLoader = new FXMLLoader(getClass().getResource("/fxml/after_round_screen.fxml"));
            afterRoundScreenLoader.setControllerFactory(param -> new AfterRoundScreenController(gameManager));
            Parent setupParent = afterRoundScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("After Round Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the end screen and its corresponding fxml file.
     *
     * @param gameManager The GameManager singleton instance.
     */
    public void launchEndScreen(GameManager gameManager) {
        try {
            FXMLLoader endScreenLoader = new FXMLLoader(getClass().getResource("/fxml/end_screen.fxml"));
            endScreenLoader.setControllerFactory(param -> new EndScreenController(gameManager));
            Parent setupParent = endScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("End Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
