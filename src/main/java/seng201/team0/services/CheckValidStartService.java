package seng201.team0.services;

import seng201.team0.GameManager;
import seng201.team0.Player;

public class CheckValidStartService {

    public static String checkValidStart(Player player) {
        int numberSelectedTowers = player.getEquippedTowers().size();
        int totalNumberTowers = player.getTowerList().size();

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
