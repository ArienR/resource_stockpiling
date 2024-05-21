package seng201.team0.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.Tower;

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
    private Label currentScoreLabel;
    @FXML
    private TableView<Tower> afterRoundTable;
    @FXML
    private TableColumn<Tower, String> towerStatusColumn;
    @FXML
    private TableColumn<Tower, String> towerNameColumn;
    @FXML
    private TableColumn<Tower, Integer> towerUsesColumn;
    @FXML
    private TableColumn<Tower, Integer> towerLevelColumn;

    public AfterRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player player = gameManager.getPlayer();
        ObservableList<Tower> towerData = FXCollections.observableArrayList(getTowerData());

        currentScoreLabel.setText("Current Score: " + player.getPlayerScore());

        towerNameColumn.setCellValueFactory(new PropertyValueFactory<>("towerName"));
        towerUsesColumn.setCellValueFactory(new PropertyValueFactory<>("consecutiveUses"));
        towerStatusColumn.setCellValueFactory(new PropertyValueFactory<>("towerStatus"));
        towerLevelColumn.setCellValueFactory(new PropertyValueFactory<>("towerLevel"));

        afterRoundTable.setItems(towerData);
    }

    private ObservableList<Tower> getTowerData() {
        Player player = gameManager.getPlayer();
        return FXCollections.observableArrayList(player.getTowerList());
    }

    @FXML
    public void afterRoundToSelectRound() {
        gameManager.launchUpcomingRoundScreen();
        gameManager.closeAfterRoundScreen();
    }
}