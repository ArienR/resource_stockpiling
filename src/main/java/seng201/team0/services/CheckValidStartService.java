package seng201.team0.services;

import seng201.team0.GameManager;

public class CheckValidStartService {

    public static String checkValidStart(GameManager gameManager) {
        int numberSelectedTowers = gameManager.getPlayer().getEquippedTowers().size();
        int totalNumberTowers = gameManager.getPlayer().getTowerList().size();

        if (numberSelectedTowers == 0){
            return "Please select at least one tower to begin";
        }
        else if (totalNumberTowers - numberSelectedTowers > 5){
            return "Cannot start with over five reserve towers";
        } else {
            return "";
        }
    }
}
