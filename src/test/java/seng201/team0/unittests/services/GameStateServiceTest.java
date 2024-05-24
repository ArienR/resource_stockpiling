package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.*;
import seng201.team0.services.GameStateService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameStateServiceTest {

    private Player player;
    private GameManager testGameManager;
    private GameStateService testGameStateService;
    private Tower tower;


    @BeforeEach
    public void setupTest() {
        player = new Player("John Doe");
        testGameManager = new GameManager(player);
        testGameStateService = new GameStateService(testGameManager);
        Round upcomingRound = new Round(1, 2, 3, 4); // Arbitrary values for testing
        testGameManager.setUpcomingRound(upcomingRound);
    }

    @Test
    void testRoundOneOfFiveWon() {
        testGameManager.setNumberOfRounds(5);
        testGameManager.incrementCurrentRoundNumber();
        testGameManager.setRoundWon(true);
        testGameStateService.isEndOfGame();
        assertFalse(testGameManager.isGameWon());
    }

    @Test
    void testRoundThreeOfFiveWon() {
        testGameManager.setNumberOfRounds(5);
        testGameManager.incrementCurrentRoundNumber();
        testGameManager.incrementCurrentRoundNumber();
        testGameManager.setRoundWon(true);

        int initialMoney = player.getPlayerMoney();
        int initialScore = player.getPlayerScore();

        testGameStateService.isEndOfGame();
        assertEquals(4, testGameManager.getCurrentRoundNumber());
        assertFalse(testGameManager.isGameWon());

        int expectedMoney = initialMoney + calculateExpectedMoney(testGameManager.getUpcomingRound(), testGameManager.getGameDifficulty(), testGameManager.getCurrentRoundNumber());
        assertEquals(expectedMoney, player.getPlayerMoney());

        int expectedScore = initialScore + calculateExpectedScore(testGameManager.getUpcomingRound(), testGameManager.getDifficultyBonus());
        assertEquals(expectedScore, player.getPlayerScore());
    }

    private int calculateExpectedMoney(Round round, String difficulty, int roundNumber) {
        int numberProduceCarts = round.getProduceCount();
        int moneyValueProduceCart = new ProduceCart(0).getMoneyValue();
        int numberMeatCarts = round.getMeatCount();
        int moneyValueMeatCart = new MeatCart(0).getMoneyValue();
        int numberDairyCarts = round.getDairyCount();
        int moneyValueDairyCart = new DairyCart(0).getMoneyValue();
        float speedValueMultiplier = round.getChangedCartSpeed() / 10.0f;
        int moneyEarnedInRound = (int) ((numberProduceCarts * moneyValueProduceCart + numberMeatCarts * moneyValueMeatCart + numberDairyCarts * moneyValueDairyCart) * (1 + speedValueMultiplier));
        if ("Hard".equals(difficulty)) {
            moneyEarnedInRound += roundNumber * 100;
        }
        return moneyEarnedInRound;
    }

    private int calculateExpectedScore(Round round, float difficultyBonus) {
        int numberProduceCarts = round.getProduceCount();
        int scoreValueProduceCart = new ProduceCart(0).getScoreValue();
        int numberMeatCarts = round.getMeatCount();
        int scoreValueMeatCart = new MeatCart(0).getScoreValue();
        int numberDairyCarts = round.getDairyCount();
        int scoreValueDairyCart = new DairyCart(0).getScoreValue();
        return (int) ((numberProduceCarts * scoreValueProduceCart + numberMeatCarts * scoreValueMeatCart + numberDairyCarts * scoreValueDairyCart) * difficultyBonus);
    }
}
