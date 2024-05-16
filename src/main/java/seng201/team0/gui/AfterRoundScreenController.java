package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterRoundScreenController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}