package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.Player;
import seng201.team0.models.DairyTower;
import seng201.team0.models.MeatTower;
import seng201.team0.models.ProduceTower;
import seng201.team0.models.Tower;
import seng201.team0.services.CheckValidStartService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CheckValidStartServiceTest {
    private Player testPlayer;

    @BeforeEach
    public void setupTest() {
        testPlayer = new Player("John Doe");
    }

    @Test
    void testStartWithNoTowers() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        assertEquals("Please select at least one tower to begin", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithOneTowers() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        assertEquals(1, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithSixReserves() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        testPlayer.getEquippedTowers().add(new MeatTower());
        testPlayer.getEquippedTowers().add(new DairyTower());
        assertEquals(1, testPlayer.getEquippedTowers().size());
        assertEquals("Cannot start with over five reserve towers", CheckValidStartService.checkValidStart(testPlayer));
    }
}
