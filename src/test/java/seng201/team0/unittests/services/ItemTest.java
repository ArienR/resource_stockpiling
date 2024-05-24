package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item item;
    private static final List<String> itemNames = List.of("Grocery Galvanizer", "Market Maestro", "Aisle Accelerator",
            "Checkout Champion", "Cart Catalyst", "Stockroom Speedster", "Supermarket Surge",
            "Retail Rocket", "Bountiful Booster", "Fulfillment Fury", "Supply Streamliner",
            "Stockpile Sprinter", "Distribution Dynamo", "Warehouse Whirlwind", "Market Mover",
            "Expedition Enhancer", "Inventory Igniter", "Cart Crusader", "Harvest Helper",
            "Delivery Dynamo", "Provision Propeller", "Cart Commander", "Stock Swift",
            "Vendor Velocity", "Retail Rush"
    );

    @Test
    void testInitialisation() {
        item = new Item(new ProduceTower(), 25, 25, 100, 0.75f);
        assertEquals(25, item.getCollectionIncrease());
        assertEquals(25, item.getSpeedIncrease());
        assertEquals(100, item.getBuyPrice());
        assertEquals(75, item.getSellPrice());
        assertInstanceOf(ProduceTower.class, item.getTowerTypeAffected());
        assertTrue(itemNames.contains(item.getItemName()));
    }

    @Test
    void testMeatType() {
        item = new Item(new MeatTower(), 25, 25, 100, 0.75f);
        assertInstanceOf(MeatTower.class, item.getTowerTypeAffected());
    }

    @Test
    void testDairyType() {
        item = new Item(new DairyTower(), 25, 25, 100, 0.75f);
        assertInstanceOf(DairyTower.class, item.getTowerTypeAffected());
    }
}
