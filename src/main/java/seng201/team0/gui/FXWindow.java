package seng201.team0.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FXWindow controls the size of the screen.
 */
public class FXWindow extends Application {
    private static FXWindow instance;
    private Stage primaryStage;

    /**
     * Constructs a new FXWindow instance.
     */
    public FXWindow() {
        // Default constructor
    }

    /**
     * Returns itself to become public.
     *
     * @return FXWindow itself.
     */
    public static FXWindow getInstance() {
        return instance;
    }

    /**
     * Opens the gui with the fxml content specified in resources/fxml/main.fxml
     * @param primaryStage The current fxml stage, handled by javaFX Application class
     * @throws IOException if there is an issue loading fxml file
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        instance = this;
        this.primaryStage = primaryStage;
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/fx_wrapper.fxml"));
        Parent root = baseLoader.load();
        seng201.team0.gui.FXWrapper fxWrapper = baseLoader.getController();
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("FX Wrapper");
        primaryStage.setScene(scene);
        primaryStage.show();
        fxWrapper.init(primaryStage);
    }

    /**
     * Launches the FXML application, this must be called from another class (in this cass App.java) otherwise JavaFX
     * errors out and does not run
     * @param args command line arguments
     */
    public static void launchWrapper(String[] args) {
        launch(args);
    }

    /**
     * Adjusts the window size after the player has chosen to start the game.
     *
     * @param width A width in pixels.
     * @param height A height for the screen in pixels.
     */
    public void setScreenSize(double width, double height) {
        if (primaryStage != null) {
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
            primaryStage.centerOnScreen();
        }
    }

}
