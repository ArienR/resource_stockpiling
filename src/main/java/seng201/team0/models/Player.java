package seng201.team0.models;

public class Player {

    private String playerName;
    private int playerMoney;

    // Need a constructor to get the player class to store name.
    public String getName(){
        return playerName;
    }

    public void setName(String tempPlayerName) {
        this.playerName = tempPlayerName;
    }

    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int tempPlayerMoney) {
        this.playerMoney = tempPlayerMoney;
    }

}
