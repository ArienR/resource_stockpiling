package seng201.team0.services;

import seng201.team0.GameManager;
import seng201.team0.models.*;

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
            scoreEarned();
            gameManager.incrementCurrentRoundNumber();
            gameManager.launchAfterRoundScreen();
            gameManager.gameScreenToAfterRoundScreen();
        } else if (gameManager.isRoundWon() && gameManager.getCurrentRoundNumber() == gameManager.getNumberOfRounds()) {
            gameManager.incrementCurrentRoundNumber();
            scoreEarned();
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
        tower.incrementConsecutiveNonUses();
        int levelDownChance = tower.getConsecutiveNonUses();
        if (levelDownChance >= getRandomEvent()){
            if (tower.getTowerLevel() > 1){
                tower.decreaseTowerLevel();
            }
        }
        tower.wipeConsecutiveUses();
        int currentLevel = tower.getTowerLevel();
        if (levelBefore != currentLevel){
            tower.setTowerTableLevel(levelBefore + "  ==>  " + currentLevel);
        } else {
            tower.setTowerTableLevel(String.valueOf(currentLevel));
        }
    }

    public int getRandomEvent(){
        Random r = new Random();
        return r.nextInt(1, 6);
    }

    public void moneyEarned(){
        int playerMoney = gameManager.getPlayer().getPlayerMoney();
        int numberProduceCarts = gameManager.getUpcomingRound().getProduceCount();
        int moneyValueProduceCart = new ProduceCart().getMoneyValue();
        int numberMeatCarts = gameManager.getUpcomingRound().getMeatCount();
        int moneyValueMeatCart = new MeatCart().getMoneyValue();
        int numberDairyCarts = gameManager.getUpcomingRound().getDairyCount();
        int moneyValueDairyCart = new DairyCart().getMoneyValue();
        float speedValueMultiplier = gameManager.getUpcomingRound().getChangedCartSpeed()/10.0f;
        int moneyEarnedInRound = (int) ((numberProduceCarts*moneyValueProduceCart+numberMeatCarts*moneyValueMeatCart+numberDairyCarts*moneyValueDairyCart)*(1+speedValueMultiplier));
        if (gameManager.getGameDifficulty() == "Hard"){
            moneyEarnedInRound += gameManager.getCurrentRoundNumber()*100;
        }
        gameManager.getPlayer().setPlayerMoney(playerMoney+moneyEarnedInRound);


    }

    public void scoreEarned(){
        int numberProduceCarts = gameManager.getUpcomingRound().getProduceCount();
        int scoreValueProduceCart = new ProduceCart().getScoreValue();
        int numberMeatCarts = gameManager.getUpcomingRound().getMeatCount();
        int scoreValueMeatCart = new MeatCart().getScoreValue();
        int numberDairyCarts = gameManager.getUpcomingRound().getDairyCount();
        int scoreValueDairyCart = new DairyCart().getScoreValue();
        int scoreEarnedInRound = (int) ((numberProduceCarts*scoreValueProduceCart+numberMeatCarts*scoreValueMeatCart
                +numberDairyCarts*scoreValueDairyCart)*gameManager.getDifficultyBonus());
        gameManager.getPlayer().addToPlayerScore(scoreEarnedInRound);
    }

}
