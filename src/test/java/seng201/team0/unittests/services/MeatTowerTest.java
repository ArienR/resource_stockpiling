package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.MeatTower;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MeatTowerTest {

    private MeatTower meatTower;

    private static final List<String> meatNames = List.of(
            "Beef Tower", "Chicken Tower", "Pork Tower", "Lamb Tower",
            "Turkey Tower", "Duck Tower", "Venison Tower", "Bison Tower",
            "Rabbit Tower", "Goat Tower", "Salmon Tower", "Tuna Tower",
            "Shrimp Tower", "Crab Tower", "Lobster Tower", "Clam Tower",
            "Mussel Tower", "Oyster Tower", "Sausage Tower", "Bacon Tower"
    );

    @BeforeEach
    public void setupTest() {
        meatTower = new MeatTower(5, 5, 100, 0.75f, 2);
    }

    @Test
    void testInitialisation() {
        MeatTower meatTower = new MeatTower(5, 10, 100, 0.75f, 1);
        meatTower.setTowerTableLevel("2");
        assertEquals("2", meatTower.getTowerTableLevel());
        assertEquals(5, meatTower.getTowerSpeed());
        assertEquals(10, meatTower.getTowerFillAmount());
        assertEquals(100, meatTower.getBuyPrice());
        assertEquals(75, meatTower.getSellPrice());
        assertEquals(1, meatTower.getTowerLevel());
        assertTrue(meatNames.contains(meatTower.getTowerName()));
    }

    @Test
    void testEmptyInitialisation() {
        MeatTower meatTower = new MeatTower();
        assertEquals("1", meatTower.getTowerTableLevel());
        assertEquals(0, meatTower.getTowerSpeed());
        assertEquals(0, meatTower.getTowerFillAmount());
        assertEquals(0, meatTower.getBuyPrice());
        assertEquals(0, meatTower.getSellPrice());
        assertEquals(0, meatTower.getTowerLevel());
        assertNull(meatTower.getTowerName());
    }

    @Test
    void testRandomNameGeneration() {
        MeatTower tower1 = new MeatTower(5, 5, 100, 0.75f, 2);
        MeatTower tower2 = new MeatTower(5, 10, 150, 0.5f, 2);
        assertTrue(meatNames.contains(tower1.getTowerName()));
        assertTrue(meatNames.contains(tower2.getTowerName()));
    }

    @Test
    void testIncrementTowerLevel() {
        meatTower.incrementTowerLevel();
        assertEquals(3, meatTower.getTowerLevel());
    }

    @Test
    void testDecreaseTowerLevel() {
        meatTower.decreaseTowerLevel();
        assertEquals(1, meatTower.getTowerLevel());
    }

    @Test
    void testDecreaseTowerLevelBoundary() {
        meatTower.decreaseTowerLevel();
        meatTower.decreaseTowerLevel();
        assertEquals(1, meatTower.getTowerLevel());
    }

    @Test
    void testTowerStatus() {
        meatTower.setTowerIsBroken(true);
        assertTrue(meatTower.isTowerBroken());
        assertEquals("Broken", meatTower.getTowerStatus());

        meatTower.setTowerIsBroken(false);
        assertFalse(meatTower.isTowerBroken());
        assertEquals("Operational", meatTower.getTowerStatus());
    }

    @Test
    void testConsecutiveUses() {
        meatTower.incrementConsecutiveUses();
        assertEquals(1, meatTower.getConsecutiveUses());
        meatTower.wipeConsecutiveUses();
        assertEquals(0, meatTower.getConsecutiveUses());
    }

    @Test
    void testConsecutiveUsesAtZero() {
        meatTower.wipeConsecutiveUses();
        assertEquals(0, meatTower.getConsecutiveUses());
        meatTower.wipeConsecutiveUses();
        assertEquals(0, meatTower.getConsecutiveUses());
    }

    @Test
    void testConsecutiveNonUses() {
        meatTower.incrementConsecutiveNonUses();
        assertEquals(1, meatTower.getConsecutiveNonUses());
        meatTower.wipeConsecutiveNonUses();
        assertEquals(0, meatTower.getConsecutiveNonUses());
    }

    @Test
    void testConsecutiveNonUsesAtZero() {
        meatTower.wipeConsecutiveNonUses();
        assertEquals(0, meatTower.getConsecutiveNonUses());
        meatTower.wipeConsecutiveNonUses();
        assertEquals(0, meatTower.getConsecutiveNonUses());
    }

    @Test
    void testMonetaryValuesWithDifferentBonuses() {
        MeatTower towerWithEasyBonus = new MeatTower(5, 10, 100, 0.75f, 1);
        assertEquals(75, towerWithEasyBonus.getSellPrice());

        MeatTower towerWithHardBonus = new MeatTower(5, 10, 100, 0.5f, 1);
        assertEquals(50, towerWithHardBonus.getSellPrice());
    }
}