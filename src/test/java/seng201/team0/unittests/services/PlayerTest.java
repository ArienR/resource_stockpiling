package seng201.team0.unittests.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.Player;
import seng201.team0.models.*;

import java.util.ArrayList;
import java.util.List;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer");
    }

    @Test
    void testGetName() {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    void testSetName() {
        player.setName("NewName");
        assertEquals("NewName", player.getName());
    }

    @Test
    void testGetTowerList() {
        assertNotNull(player.getTowerList());
        assertTrue(player.getTowerList().isEmpty());
    }

    @Test
    void testGetPlayerMoney() {
        assertEquals(0, player.getPlayerMoney());
    }

    @Test
    void testSetPlayerMoney() {
        player.setPlayerMoney(100);
        assertEquals(100, player.getPlayerMoney());
    }

    @Test
    void testGetItemList() {
        assertNotNull(player.getItemList());
        assertTrue(player.getItemList().isEmpty());
    }

    @Test
    void testGetPurchasedTowers() {
        assertNotNull(player.getPurchasedTowers());
        assertTrue(player.getPurchasedTowers().isEmpty());
    }

    @Test
    void testGetPurchasedItems() {
        assertNotNull(player.getPurchasedItems());
        assertTrue(player.getPurchasedItems().isEmpty());
    }

    @Test
    void testGetEquippedTowers() {
        assertNotNull(player.getEquippedTowers());
        assertTrue(player.getEquippedTowers().isEmpty());
    }

    @Test
    void testSetEquippedTowers() {
        List<Tower> equippedTowers = new ArrayList<>();
        equippedTowers.add(new ProduceTower()); // Assuming Tower has a default constructor
        player.setEquippedTowers(equippedTowers);
        assertEquals(equippedTowers, player.getEquippedTowers());
    }

    @Test
    void testGetEquippedItems() {
        assertNotNull(player.getEquippedItems());
        assertTrue(player.getEquippedItems().isEmpty());
    }

    @Test
    void testSetEquippedItems() {
        List<Item> equippedItems = new ArrayList<>();
        equippedItems.add(new Item(new ProduceTower(), 5, 5, 5, 1.0f)); // Assuming Item has a default constructor
        player.setEquippedItems(equippedItems);
        assertEquals(equippedItems, player.getEquippedItems());
    }

    @Test
    void testGetPlayerScore() {
        assertEquals(0, player.getPlayerScore());
    }

    @Test
    void testAddToPlayerScore() {
        player.addToPlayerScore(50);
        assertEquals(50, player.getPlayerScore());
    }

    @Test
    void testRemoveBrokenTower() {
        Tower tower = new ProduceTower(); // Assuming Tower has a default constructor
        player.getTowerList().add(tower);
        assertEquals(1, player.getTowerList().size());
        player.removeBrokenTower(tower);
        assertTrue(player.getTowerList().isEmpty());
    }
}