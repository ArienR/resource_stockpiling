package seng201.team0.services;

import seng201.team0.Player;

/**
 * Executes before the start of each round to check the player is able to play the round.
 */
public class CheckValidStartService {

    /**
     * Constructs a new CheckValidStartService instance.
     */
    public CheckValidStartService() {
        // Default constructor
    }

    /**
     * Static method to check if the player can start a round.
     *
     * @param player the player singleton.
     * @return a string which will display an error message if they cannot start the round.
     */
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
