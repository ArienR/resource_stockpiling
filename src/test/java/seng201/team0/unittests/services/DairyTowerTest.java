package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.DairyTower;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DairyTowerTest {

    private DairyTower dairyTower;

    private static final List<String> dairyNames = List.of(
            "Milk Tower", "Cheese Tower", "Butter Tower", "Yogurt Tower",
            "Cream Tower", "Ice Cream Tower", "Cottage Cheese Tower", "Sour Cream Tower",
            "Whipped Cream Tower", "Buttermilk Tower", "Ricotta Tower", "Mozzarella Tower",
            "Cheddar Tower", "Parmesan Tower", "Feta Tower", "Gouda Tower",
            "Brie Tower", "Swiss Cheese Tower", "Blue Cheese Tower", "Provolone Tower");

    @BeforeEach
    public void setupTest() {
        dairyTower = new DairyTower(5, 5, 100, 0.75f, 2);
    }

    @Test
    void testInitialisation() {
        DairyTower dairyTower = new DairyTower(5, 10, 100, 0.75f, 1);
        dairyTower.setTowerTableLevel("2");
        assertEquals("2", dairyTower.getTowerTableLevel());
        assertEquals(5, dairyTower.getTowerSpeed());
        assertEquals(10, dairyTower.getTowerFillAmount());
        assertEquals(100, dairyTower.getBuyPrice());
        assertEquals(75, dairyTower.getSellPrice());
        assertEquals(1, dairyTower.getTowerLevel());
        assertTrue(dairyNames.contains(dairyTower.getTowerName()));
    }

    @Test
    void testEmptyInitialisation() {
        DairyTower dairyTower = new DairyTower();
        assertEquals("1", dairyTower.getTowerTableLevel());
        assertEquals(0, dairyTower.getTowerSpeed());
        assertEquals(0, dairyTower.getTowerFillAmount());
        assertEquals(0, dairyTower.getBuyPrice());
        assertEquals(0, dairyTower.getSellPrice());
        assertEquals(0, dairyTower.getTowerLevel());
        assertNull(dairyTower.getTowerName());
    }

    @Test
    void testRandomNameGeneration() {
        DairyTower tower1 = new DairyTower(5, 5, 100, 0.75f, 2);
        DairyTower tower2 = new DairyTower(5, 10, 150, 0.5f, 2);
        assertTrue(dairyNames.contains(tower1.getTowerName()));
        assertTrue(dairyNames.contains(tower2.getTowerName()));
    }

    @Test
    void testIncrementTowerLevel() {
        dairyTower.incrementTowerLevel();
        assertEquals(3, dairyTower.getTowerLevel());
    }

    @Test
    void testDecreaseTowerLevel() {
        dairyTower.decreaseTowerLevel();
        assertEquals(1, dairyTower.getTowerLevel());
    }

    @Test
    void testDecreaseTowerLevelBoundary() {
        dairyTower.decreaseTowerLevel();
        dairyTower.decreaseTowerLevel();
        assertEquals(1, dairyTower.getTowerLevel());
    }

    @Test
    void testTowerStatus() {
        dairyTower.setTowerIsBroken(true);
        assertTrue(dairyTower.isTowerBroken());
        assertEquals("Broken", dairyTower.getTowerStatus());

        dairyTower.setTowerIsBroken(false);
        assertFalse(dairyTower.isTowerBroken());
        assertEquals("Operational", dairyTower.getTowerStatus());
    }

    @Test
    void testConsecutiveUses() {
        dairyTower.incrementConsecutiveUses();
        assertEquals(1, dairyTower.getConsecutiveUses());
        dairyTower.wipeConsecutiveUses();
        assertEquals(0, dairyTower.getConsecutiveUses());
    }

    @Test
    void testConsecutiveUsesAtZero() {
        dairyTower.wipeConsecutiveUses();
        assertEquals(0, dairyTower.getConsecutiveUses());
        dairyTower.wipeConsecutiveUses();
        assertEquals(0, dairyTower.getConsecutiveUses());
    }

    @Test
    void testConsecutiveNonUses() {
        dairyTower.incrementConsecutiveNonUses();
        assertEquals(1, dairyTower.getConsecutiveNonUses());
        dairyTower.wipeConsecutiveNonUses();
        assertEquals(0, dairyTower.getConsecutiveNonUses());
    }

    @Test
    void testConsecutiveNonUsesAtZero() {
        dairyTower.wipeConsecutiveNonUses();
        assertEquals(0, dairyTower.getConsecutiveNonUses());
        dairyTower.wipeConsecutiveNonUses();
        assertEquals(0, dairyTower.getConsecutiveNonUses());
    }

    @Test
    void testMonetaryValuesWithDifferentBonuses() {
        DairyTower towerWithEasyBonus = new DairyTower(5, 10, 100, 0.75f, 1);
        assertEquals(75, towerWithEasyBonus.getSellPrice());

        DairyTower towerWithHardBonus = new DairyTower(5, 10, 100, 0.5f, 1);
        assertEquals(50, towerWithHardBonus.getSellPrice());
    }
}