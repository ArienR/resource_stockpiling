package seng201.team0.services;

import seng201.team0.GameManager;

public class GameStateService {

    private GameManager gameManager;
    public GameStateService(GameManager tempGameManager) {this.gameManager = tempGameManager;}


    // will be changed to a non button fxml later
    public void isEndOfGame() {
        if (gameManager.isRoundWon() == true && gameManager.getCurrentRoundNumber() + 1 < gameManager.getNumberOfRounds()) {
            gameManager.launchAfterRoundScreen();
            gameManager.gameScreenToAfterRoundScreen();
            gameManager.incrementCurrentRoundNumber();
        } else if (gameManager.isRoundWon() == true && gameManager.getCurrentRoundNumber() + 1 == gameManager.getNumberOfRounds()) {
            gameManager.launchEndScreen();
            gameManager.gameScreenToEndScreen();
            gameManager.setEndOfGame(true);
            gameManager.setGameWon(true);
        } else {
            gameManager.launchEndScreen();
            gameManager.gameScreenToEndScreen();
            gameManager.setEndOfGame(true);
        }
    }

}
