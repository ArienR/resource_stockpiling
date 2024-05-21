package seng201.team0.services;

import seng201.team0.GameManager;

public class GameStateService {

    private GameManager gameManager;
    public GameStateService(GameManager tempGameManager) {this.gameManager = tempGameManager;}


    // will be changed to a non button fxml later
    public void isEndOfGame() {
        if (gameManager.isRoundWon() == true && gameManager.getCurrentRoundNumber() < gameManager.getNumberOfRounds()) {
            gameManager.incrementCurrentRoundNumber();
            gameManager.launchAfterRoundScreen();
            gameManager.gameScreenToAfterRoundScreen();
        } else if (gameManager.isRoundWon() == true && gameManager.getCurrentRoundNumber() == gameManager.getNumberOfRounds()) {
            gameManager.incrementCurrentRoundNumber();
            gameManager.setEndOfGame(true);
            gameManager.setGameWon(true);
            gameManager.launchEndScreen();
            gameManager.gameScreenToEndScreen();
        } else {
            gameManager.setEndOfGame(true);
            gameManager.launchEndScreen();
            gameManager.gameScreenToEndScreen();
        }
    }

}
