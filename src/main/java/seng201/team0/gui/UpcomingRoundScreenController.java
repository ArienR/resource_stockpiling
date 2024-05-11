package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;
import seng201.team0.GameManager;

public class UpcomingRoundScreenController {

    private Stage stage;
    private GameManager gameManager;

    @FXML Label optionOneCartType;
    @FXML Label optionOneCartSize;
    @FXML Label optionOneNumberOfCarts;
    @FXML Label optionTwoCartType;
    @FXML Label optionTwoCartSize;
    @FXML Label optionTwoNumberOfCarts;
    @FXML Label optionThreeCartType;
    @FXML Label optionThreeCartSize;
    @FXML Label optionThreeNumberOfCarts;
    @FXML Button selectOptionOneButton;
    @FXML Button selectOptionTwoButton;
    @FXML Button selectOptionThreeButton;

    public UpcomingRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    public void initialize() {
        FXWindow.getInstance().setMaximized(true);
    }

    public void handleOptionOneSelected() {
        System.out.println("Option One Selected");
    }

    public void handleOptionTwoSelected() {
        System.out.println("Option Two Selected");
    }

    public void handleOptionThreeSelected() {
        System.out.println("Option Three Selected");
    }

}
