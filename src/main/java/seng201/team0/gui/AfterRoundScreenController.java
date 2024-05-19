package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import seng201.team0.GameManager;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterRoundScreenController implements Initializable {

    private GameManager gameManager;

    @FXML
    private TableView<?> afterRoundTable;

    @FXML
    private TableColumn<?, ?> tableCompensationColumn;

    @FXML
    private TableColumn<?, ?> tableStatusColumn;

    @FXML
    private TableColumn<?, ?> tableTowerColumn;

    @FXML
    private TableColumn<?, ?> tableUsedColumn;

    public AfterRoundScreenController(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}