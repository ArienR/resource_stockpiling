package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import seng201.team0.GameManager;

import java.util.*;

public class EndScreenController {

    GameManager gameManager;

    @FXML private Label endScreenMessageLabel;

    EndScreenController(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
