package seng201.team0.services;

import seng201.team0.GameManager;
import seng201.team0.models.Tower;

import java.util.List;
import java.util.Random;

public class GameStateService {

    private GameManager gameManager;
    public GameStateService(GameManager tempGameManager) {this.gameManager = tempGameManager;}


    // will be changed to a non button fxml later
    public void isEndOfGame() {
        if (gameManager.isRoundWon() && gameManager.getCurrentRoundNumber() < gameManager.getNumberOfRounds()) {
            randomEvents();
            moneyEarned();
            gameManager.incrementCurrentRoundNumber();
            gameManager.launchAfterRoundScreen();
            gameManager.gameScreenToAfterRoundScreen();
        } else if (gameManager.isRoundWon() && gameManager.getCurrentRoundNumber() == gameManager.getNumberOfRounds()) {
            gameManager.incrementCurrentRoundNumber();
            gameManager.setGameWon(true);
            gameManager.launchEndScreen();
            gameManager.gameScreenToEndScreen();
        } else {
            gameManager.launchEndScreen();
            gameManager.gameScreenToEndScreen();
        }
    }

    public void randomEvents(){
        List<Tower> allTowers = gameManager.getPlayer().getTowerList();
        List<Tower> usedTowers = gameManager.getPlayer().getEquippedTowers();
        for (Tower tower : allTowers) {
            if (usedTowers.contains(tower)) {
                usedTowerRandomEvent(tower);
            } else {
                unusedTowerRandomEvent(tower);
            }
        }
    }

    public void usedTowerRandomEvent(Tower tower){
        int levelBefore = tower.getTowerLevel();
        int consecutiveUses = tower.getConsecutiveUses();
        if (consecutiveUses == 0){
            tower.incrementTowerLevel();
            tower.incrementConsecutiveUses();
        }
        else if (consecutiveUses < getRandomEvent()){
            tower.incrementTowerLevel();
            if (tower.getConsecutiveUses() < 5){
                tower.incrementConsecutiveUses();

            }
        } else {
            tower.setTowerIsBroken(true);
            int playerMoney = gameManager.getPlayer().getPlayerMoney();
            gameManager.getPlayer().setPlayerMoney(playerMoney + Math.round((float) (tower.getBuyPrice()/4)));
        }
        tower.wipeConsecutiveNonUses();
        int currentLevel = tower.getTowerLevel();
        if (levelBefore != currentLevel){
            tower.setTowerTableLevel(levelBefore + "  ==>  " + currentLevel);
        } else {
            tower.setTowerTableLevel(String.valueOf(currentLevel));
        }
    }

    public void unusedTowerRandomEvent(Tower tower){
        int levelBefore = tower.getTowerLevel();
        int levelDownChance = tower.getConsecutiveNonUses();
        if (levelDownChance >= getRandomEvent()){
            if (tower.getTowerLevel() > 1){
                tower.decreaseTowerLevel();
            }
        }
        tower.wipeConsecutiveUses();
        tower.incrementConsecutiveNonUses();
        int currentLevel = tower.getTowerLevel();
        if (levelBefore != currentLevel){
            tower.setTowerTableLevel(levelBefore + "  ==>  " + currentLevel);
        } else {
            tower.setTowerTableLevel(String.valueOf(currentLevel));
        }
    }

    public int getRandomEvent(){
        Random r = new Random();
        return r.nextInt(1, 11);
    }

    // move to RoundLogicService
    public void moneyEarned(){
        int playerMoney = gameManager.getPlayer().getPlayerMoney();
        // money earned from killing carts
//        int moneyEarnedLastRound = GameStateService.getMone();
//        gameManager.getPlayer().setPlayerMoney(playerMoney+moneyEarnedLastRound);
    }

}
