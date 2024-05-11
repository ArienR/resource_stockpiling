package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import seng201.team0.GameManager;

import java.io.IOException;

public class FXWrapper {
    @FXML
    private Pane pane;

    private Stage stage;


    public void init(Stage stage) {
        this.stage = stage;
        new GameManager(this::launchSetupScreen, /*this::launchRoundIntermediaryScreen ,*/ this::clearPane);
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
            stage.setTitle("Game Manager Setup Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void launchMainScreen(RocketManager rocketManager) {
//        System.out.println("Main screen would launch here.");
//        // For now, do nothing or just print a message.
//    }

    public void launchRoundIntermediaryScreen(GameManager gameManager) {
        try {
            FXMLLoader roundIntermediaryScreenLoader = new FXMLLoader(getClass().getResource("/fxml/inventory_screen.fxml"));
            roundIntermediaryScreenLoader.setControllerFactory(param -> new SetupScreenController(gameManager));
            Parent setupParent = roundIntermediaryScreenLoader.load();
            pane.getChildren().add(setupParent);
            stage.setTitle("Game Manager Round Intermediary Screen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
