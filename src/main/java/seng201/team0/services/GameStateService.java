package seng201.team0.services;

import seng201.team0.GameManager;
import seng201.team0.models.*;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Manages if the game has been won or lost,
 * application of random events, and calculation of scores and money.
 */
public class GameStateService {

    /**
     * The GameManager singleton.
     */
    private final GameManager gameManager;

    /**
     * Constructs a new GameStateService to manage game state.
     *
     * @param tempGameManager The GameManager that holds and manages the overall game state.
     */
    public GameStateService(GameManager tempGameManager) {
        this.gameManager = tempGameManager;
    }

    /**
     * Evaluates if the game or a round has ended and progresses the game state accordingly.
     * Handles transitions between screens based on the game or round outcomes.
     */
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
            moneyEarned();
            scoreEarned();
            gameManager.setGameWon(true);
            gameManager.launchEndScreen();
            gameManager.gameScreenToEndScreen();
        } else {
            gameManager.launchEndScreen();
            gameManager.gameScreenToEndScreen();
        }
    }

    /**
     * Applies random events to each tower based on whether they were used in the last round.
     */
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

    /**
     * Handles random events for towers that were used in the last round.
     *
     * @param tower The tower that was used.
     */
    public void usedTowerRandomEvent(Tower tower){
        int levelBefore = tower.getTowerLevel();
        int consecutiveUses = tower.getConsecutiveUses();
        if (consecutiveUses == 0){
            tower.incrementTowerLevel();
            tower.incrementConsecutiveUses();
        } else if (consecutiveUses < getRandomEvent()){
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

    /**
     * Handles random events for towers that were not used in the last round.
     *
     * @param tower The tower that was not used.
     */
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

    /**
     * Generates a random number between 1 and 5, used to determine the outcome of random events.
     *
     * @return A random integer between 1 and 5.
     */
    public int getRandomEvent(){
        Random r = new Random();
        return r.nextInt(1, 6);
    }

    /**
     * Calculates and adds the money earned after a round based on cart speeds and counts.
     */
    public void moneyEarned(){
        int playerMoney = gameManager.getPlayer().getPlayerMoney();
        int bonusCartSpeed = gameManager.getUpcomingRound().getChangedCartSpeed();
        int numberProduceCarts = gameManager.getUpcomingRound().getProduceCount();
        int moneyValueProduceCart = new ProduceCart(bonusCartSpeed).getMoneyValue();
        int numberMeatCarts = gameManager.getUpcomingRound().getMeatCount();
        int moneyValueMeatCart = new MeatCart(bonusCartSpeed).getMoneyValue();
        int numberDairyCarts = gameManager.getUpcomingRound().getDairyCount();
        int moneyValueDairyCart = new DairyCart(bonusCartSpeed).getMoneyValue();
        float speedValueMultiplier = gameManager.getUpcomingRound().getChangedCartSpeed()/10.0f;
        int moneyEarnedInRound = (int) ((numberProduceCarts*moneyValueProduceCart+numberMeatCarts*moneyValueMeatCart+numberDairyCarts*moneyValueDairyCart)*(1+speedValueMultiplier));
        if (Objects.equals(gameManager.getGameDifficulty(), "Hard")){
            moneyEarnedInRound += gameManager.getCurrentRoundNumber()*100;
        }
        gameManager.getPlayer().setPlayerMoney(playerMoney+moneyEarnedInRound);


    }

    /**
     * Calculates and adds the score after a round based on cart speeds and counts.
     */
    public void scoreEarned() {
        int numberProduceCarts = gameManager.getUpcomingRound().getProduceCount();
        int bonusCartSpeed = gameManager.getUpcomingRound().getChangedCartSpeed();
        int scoreValueProduceCart = new ProduceCart(bonusCartSpeed).getScoreValue();
        int numberMeatCarts = gameManager.getUpcomingRound().getMeatCount();
        int scoreValueMeatCart = new MeatCart(bonusCartSpeed).getScoreValue();
        int numberDairyCarts = gameManager.getUpcomingRound().getDairyCount();
        int scoreValueDairyCart = new DairyCart(bonusCartSpeed).getScoreValue();
        int scoreEarnedInRound = (int) ((numberProduceCarts*scoreValueProduceCart+numberMeatCarts*scoreValueMeatCart
                +numberDairyCarts*scoreValueDairyCart)*(1/gameManager.getDifficultyBonus()));
        gameManager.getPlayer().addToPlayerScore(scoreEarnedInRound);
    }
}
