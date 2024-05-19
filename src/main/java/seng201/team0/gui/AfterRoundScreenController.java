package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import seng201.team0.GameManager;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *Controller class that interacts with the 'after round screen'
 */
public class AfterRoundScreenController implements Initializable {

    /**
     * of gameManager that
     */
    private GameManager gameManager;

    @FXML
    private TableView<?> afterRoundTable;

    @FXML
    private TableColumn<?, ?> tableCompensationColumn;

    @FXML
    private TableColumn<?, ?> tableStatusColumn;

    @FXML
    private TableColumn<?, ?> towerNameColumn;

    @FXML
    private TableColumn<?, ?> tableUsedColumn;

    public AfterRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void afterRoundToSelectRound() {
        gameManager.launchUpcomingRoundScreen();
        gameManager.closeAfterRoundScreen();
    }
}