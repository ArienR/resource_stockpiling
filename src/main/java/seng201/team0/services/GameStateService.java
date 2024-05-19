package seng201.team0.services;

import seng201.team0.GameManager;

public class GameStateService {

    private GameManager gameManager;
    public GameStateService(GameManager tempGameManager) {this.gameManager = tempGameManager;}


    // will be changed to a non button fxml later
    public void isEndOfGame() {
        if (gameManager.isRoundWon() == true && gameManager.getCurrentRoundNumber() + 1 < gameManager.getNumberOfRounds()) {
            gameManager.launchAfterRoundScreen();
            gameManager.closeGameScreen();
            gameManager.incrementCurrentRoundNumber();
            System.out.println(gameManager.getCurrentRoundNumber());
        } else if (gameManager.isRoundWon() == true && gameManager.getCurrentRoundNumber() + 1 == gameManager.getNumberOfRounds()) {
            System.out.println("Game won");
            //launch game won screen
            gameManager.setEndOfGame(true);
            gameManager.setGameWon(true);
        } else {
            System.out.print("Game lost");
            // launch game lost screen.
            gameManager.setEndOfGame(true);
        }
    }

}
