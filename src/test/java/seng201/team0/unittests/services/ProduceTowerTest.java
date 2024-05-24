package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProduceTowerTest {

    private ProduceTower produceTower;

    private static final List<String> produceNames = List.of(
            "Apple Tower", "Banana Tower", "Orange Tower", "Grape Tower",
            "Cherry Tower", "Peach Tower", "Pineapple Tower", "Watermelon Tower",
            "Mango Tower", "Blueberry Tower", "Strawberry Tower", "Lemon Tower",
            "Kiwi Tower", "Avocado Tower", "Coconut Tower", "Papaya Tower",
            "Plum Tower", "Pear Tower", "Pomegranate Tower", "Fig Tower");

    @BeforeEach
    public void setupTest() {
        produceTower = new ProduceTower(5, 5, 100, 0.75f, 2);
    }

    @Test
    void testInitialisation() {
        ProduceTower produceTower = new ProduceTower(5, 10, 100, 0.75f, 1);
        produceTower.setTowerTableLevel("2");
        assertEquals("2", produceTower.getTowerTableLevel());
        assertEquals(5, produceTower.getTowerSpeed());
        assertEquals(10, produceTower.getTowerFillAmount());
        assertEquals(100, produceTower.getBuyPrice());
        assertEquals(75, produceTower.getSellPrice());
        assertEquals(1, produceTower.getTowerLevel());
        assertTrue(produceNames.contains(produceTower.getTowerName()));
    }

    @Test
    void testEmptyInitialisation() {
        ProduceTower produceTower = new ProduceTower();
        assertEquals("1", produceTower.getTowerTableLevel());
        assertEquals(0, produceTower.getTowerSpeed());
        assertEquals(0, produceTower.getTowerFillAmount());
        assertEquals(0, produceTower.getBuyPrice());
        assertEquals(0, produceTower.getSellPrice());
        assertEquals(0, produceTower.getTowerLevel());
        assertEquals("1", produceTower.getTowerTableLevel());
    }

    @Test
    void testRandomNameGeneration() {
        ProduceTower tower1 = new ProduceTower(5, 5, 100, 0.75f, 2);
        ProduceTower tower2 = new ProduceTower(5, 10, 150, 0.5f, 2);
        assertTrue(produceNames.contains(tower1.getTowerName()));
        assertTrue(produceNames.contains(tower2.getTowerName()));
    }

    @Test
    void testIncrementTowerLevel() {
        produceTower.incrementTowerLevel();
        assertEquals(3, produceTower.getTowerLevel());
    }

    @Test
    void testDecreaseTowerLevel() {
        produceTower.decreaseTowerLevel();
        assertEquals(1, produceTower.getTowerLevel());
    }

    @Test
    void testDecreaseTowerLevelBoundary() {
        produceTower.decreaseTowerLevel();
        produceTower.decreaseTowerLevel();
        assertEquals(1, produceTower.getTowerLevel());
    }

    @Test
    void testTowerStatus() {
        produceTower.setTowerIsBroken(true);
        assertTrue(produceTower.isTowerBroken());
        assertEquals("Broken", produceTower.getTowerStatus());

        produceTower.setTowerIsBroken(false);
        assertFalse(produceTower.isTowerBroken());
        assertEquals("Operational", produceTower.getTowerStatus());
    }

    @Test
    void testConsecutiveUses() {
        produceTower.incrementConsecutiveUses();
        assertEquals(1, produceTower.getConsecutiveUses());
        produceTower.wipeConsecutiveUses();
        assertEquals(0, produceTower.getConsecutiveUses());
    }

    @Test
    void testConsecutiveUsesAtZero() {
        produceTower.wipeConsecutiveUses();
        assertEquals(0, produceTower.getConsecutiveUses());
        produceTower.wipeConsecutiveUses();
        assertEquals(0, produceTower.getConsecutiveUses());
    }

    @Test
    void testConsecutiveNonUses() {
        produceTower.incrementConsecutiveNonUses();
        assertEquals(1, produceTower.getConsecutiveNonUses());
        produceTower.wipeConsecutiveNonUses();
        assertEquals(0, produceTower.getConsecutiveNonUses());
    }

    @Test
    void testConsecutiveNonUsesAtZero() {
        produceTower.wipeConsecutiveNonUses();
        assertEquals(0, produceTower.getConsecutiveNonUses());
        produceTower.wipeConsecutiveNonUses();
        assertEquals(0, produceTower.getConsecutiveNonUses());
    }

    @Test
    void testMonetaryValuesWithDifferentBonuses() {
        ProduceTower towerWithEasyBonus = new ProduceTower(5, 10, 100, 0.75f, 1);
        assertEquals(75, towerWithEasyBonus.getSellPrice());

        ProduceTower towerWithHardBonus = new ProduceTower(5, 10, 100, 0.5f, 1);
        assertEquals(50, towerWithHardBonus.getSellPrice());
    }
}
