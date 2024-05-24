package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.*;
import seng201.team0.models.Round;
import seng201.team0.models.Tower;
import seng201.team0.services.ShopService;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing the ShopService implementation
 */
public class ShopServiceServiceTest {

    Player testPlayer;
    GameManager testGameManager;
    ShopService testShopService;

    @BeforeEach
    void setupTest(){
        testPlayer = new Player("John Doe");
        testGameManager = new GameManager(testPlayer);
        Round upcomingRound = new Round(5, 5, 5, 5);
        testGameManager.setUpcomingRound(upcomingRound);
        testShopService = new ShopService(testGameManager);
    }

    @Test
    void testItemMade() {
        assertTrue((testShopService.getItems().get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof ProduceTower)
                || (testShopService.getItems().get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof MeatTower)
                || (testShopService.getItems().get(testShopService.getItems().size()-1).getTowerTypeAffected() instanceof DairyTower));
    }
}
