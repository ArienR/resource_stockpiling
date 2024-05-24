//package seng201.team0.unittests.services;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import seng201.team0.GameManager;
//import seng201.team0.Player;
//import seng201.team0.services.GameStateService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//public class GameStateServiceTest {
//
//    private Player player;
//    private GameManager testGameManager;
//    private GameStateService testGameStateService;
//
//
//    @BeforeEach
//    public void setupTest() {
//        player = new Player("John Doe");
//        testGameManager = new GameManager(player);
//        testGameStateService = new GameStateService(testGameManager);
//    }
//
//    @Test
//    void testRoundWonGameContinues() {
//        testGameManager.setNumberOfRounds(5);
//        testGameManager.incrementCurrentRoundNumber();
//        testGameManager.incrementCurrentRoundNumber();
//        testGameManager.incrementCurrentRoundNumber();
//        testGameManager.setRoundWon(true);
//        System.out.println(testGameManager.getCurrentRoundNumber());
//        testGameStateService.isEndOfGame();
//        testGameStateService.randomEvents();
//        testGameStateService.usedTowerRandomEvent(tower);
//        assertEquals(false, );
//    }
//}
