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
     * The singleton GameManager instance.
     */
    private final GameManager gameManager;

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
    private TableColumn<Tower, String> towerTableLevelColumn;

    /**
     * Constructor initializes the controller with a reference to the game manager.
     *
     * @param tempGameManager The GameManager singleton instance.
     */
    public AfterRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    /**
     * Initializes the controller and sets up the table to display tower data.
     * This sets the table columns to the properties of Tower objects.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player player = gameManager.getPlayer();
        ObservableList<Tower> towerData = FXCollections.observableArrayList(getTowerData());

        currentScoreLabel.setText("Current Score: " + player.getPlayerScore());

        towerNameColumn.setCellValueFactory(new PropertyValueFactory<>("towerName"));
        towerUsesColumn.setCellValueFactory(new PropertyValueFactory<>("consecutiveUses"));
        towerStatusColumn.setCellValueFactory(new PropertyValueFactory<>("towerStatus"));
        towerTableLevelColumn.setCellValueFactory(new PropertyValueFactory<>("towerTableLevel"));

        afterRoundTable.setItems(towerData);

    }

    /**
     * Gets the current list of towers from the players' inventory.
     *
     * @return An ObservableList containing the towers.
     */
    private ObservableList<Tower> getTowerData() {
        Player player = gameManager.getPlayer();
        return FXCollections.observableArrayList(player.getTowerList());
    }

    /**
     * Handles the transition from the after round screen to the select upcoming
     * round screen.
     */
    @FXML
    public void afterRoundToSelectRound() {
        removeBrokenTowers();
        gameManager.launchUpcomingRoundScreen();
        gameManager.closeAfterRoundScreen();
    }

    /**
     * Handles the event that a tower breaks, and it must be removed from the players
     * list of towers.
     */
    public void removeBrokenTowers(){
        for (Tower tower: gameManager.getPlayer().getPurchasedTowers()){
            if (tower.isTowerBroken()){
                gameManager.getPlayer().removeBrokenTower(tower);
            }
        }
    }
}