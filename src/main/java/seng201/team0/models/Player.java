package seng201.team0.models;

public class Player {

    private String playerName;

    // Need a constructor to get the player class to store name.
    public String getName(){
        return playerName;
    }

    public void setName(String tempPlayerName) {
        this.playerName = tempPlayerName;
    }

}
