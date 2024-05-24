package seng201.team0.unittests.services;

import org.junit.jupiter.api.Test;
import seng201.team0.GameManager;
import seng201.team0.Player;
import seng201.team0.models.Round;
import seng201.team0.services.GenerateCartsService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateCartsServiceTest {

    GameManager gameManager;
    GenerateCartsService generateCartsService;
    Player player = new Player("John Doe");

    @Test
    void testInitialisation() {
        gameManager = new GameManager(player);
        Round upcomingRound = new Round(1, 2, 3, 0);
        gameManager.setUpcomingRound(upcomingRound);
        generateCartsService = new GenerateCartsService(gameManager);

        assertEquals(1, generateCartsService.generateProduceCarts().size());
        assertEquals(2, generateCartsService.generateMeatCarts().size());
        assertEquals(3, generateCartsService.generateDairyCarts().size());

        assertEquals(30, generateCartsService.generateProduceCarts().get(0).getCartSpeed());
        assertEquals(35, generateCartsService.generateMeatCarts().get(0).getCartSpeed());
        assertEquals(40, generateCartsService.generateDairyCarts().get(0).getCartSpeed());
    }

    @Test
    void testInitialisationChangedCartSpeed() {
        gameManager = new GameManager(player);
        Round upcomingRound = new Round(3, 2, 1, -5);
        gameManager.setUpcomingRound(upcomingRound);
        generateCartsService = new GenerateCartsService(gameManager);

        assertEquals(3, generateCartsService.generateProduceCarts().size());
        assertEquals(2, generateCartsService.generateMeatCarts().size());
        assertEquals(1, generateCartsService.generateDairyCarts().size());

        assertEquals(25, generateCartsService.generateProduceCarts().get(0).getCartSpeed());
        assertEquals(30, generateCartsService.generateMeatCarts().get(0).getCartSpeed());
        assertEquals(35, generateCartsService.generateDairyCarts().get(0).getCartSpeed());
    }

    @Test
    void testCartSpeedValue() {
        gameManager = new GameManager(player);
        Round upcomingRound = new Round(3, 2, 1, -5);
        gameManager.setUpcomingRound(upcomingRound);
        generateCartsService = new GenerateCartsService(gameManager);

        assertEquals(-5, generateCartsService.getChangedCartSpeed());
    }
}
