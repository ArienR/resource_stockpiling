package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.Player;
import seng201.team0.models.DairyTower;
import seng201.team0.models.MeatTower;
import seng201.team0.models.ProduceTower;
import seng201.team0.models.Tower;
import seng201.team0.services.CheckValidStartService;

import java.util.ArrayList;
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
    void testStartWithOneTower() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        assertEquals(1, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithTwoTowers() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        testPlayer.getEquippedTowers().add(new MeatTower());
        assertEquals(2, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithThreeTowers() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        testPlayer.getEquippedTowers().add(new MeatTower());
        testPlayer.getEquippedTowers().add(new DairyTower());
        assertEquals(3, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }
    @Test
    void testStartWithFourTowers() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        testPlayer.getEquippedTowers().add(new MeatTower());
        testPlayer.getEquippedTowers().add(new DairyTower());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        assertEquals(4, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithFiveTowers() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        testPlayer.getEquippedTowers().add(new MeatTower());
        testPlayer.getEquippedTowers().add(new DairyTower());
        testPlayer.getEquippedTowers().add(new ProduceTower());
        assertEquals(4, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithEightReserves() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        assertEquals(8, testPlayer.getTowerList().size());
        assertEquals(0, testPlayer.getEquippedTowers().size());
        assertEquals("Please select at least one tower to begin", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithSevenReserves() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getEquippedTowers().add(testPlayer.getTowerList().get(0));
        assertEquals(8, testPlayer.getTowerList().size());
        assertEquals(1, testPlayer.getEquippedTowers().size());
        assertEquals("Cannot start with over five reserve towers", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithSixReserves() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getEquippedTowers().add(testPlayer.getTowerList().get(0));
        assertEquals(7, testPlayer.getTowerList().size());
        assertEquals(1, testPlayer.getEquippedTowers().size());
        assertEquals("Cannot start with over five reserve towers", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithFiveReservesOne() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getEquippedTowers().add(testPlayer.getTowerList().get(0));
        assertEquals(6, testPlayer.getTowerList().size());
        assertEquals(1, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithFiveReservesTwo() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getEquippedTowers().add(testPlayer.getTowerList().get(0));
        testPlayer.getEquippedTowers().add(testPlayer.getTowerList().get(1));
        assertEquals(7, testPlayer.getTowerList().size());
        assertEquals(2, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }

    @Test
    void testStartWithSevenReservesThree() {
        assertEquals(0, testPlayer.getEquippedTowers().size());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getTowerList().add(new DairyTower());
        testPlayer.getTowerList().add(new ProduceTower());
        testPlayer.getTowerList().add(new MeatTower());
        testPlayer.getEquippedTowers().add(testPlayer.getTowerList().get(0));
        testPlayer.getEquippedTowers().add(testPlayer.getTowerList().get(1));
        testPlayer.getEquippedTowers().add(testPlayer.getTowerList().get(2));
        assertEquals(8, testPlayer.getTowerList().size());
        assertEquals(3, testPlayer.getEquippedTowers().size());
        assertEquals("", CheckValidStartService.checkValidStart(testPlayer));
    }
}
