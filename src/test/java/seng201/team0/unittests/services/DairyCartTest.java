package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.DairyCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DairyCartTest {

    private DairyCart dairyCart;

    @BeforeEach
    public void setupTest() {
        dairyCart = new DairyCart(0);
    }

    @Test
    void testInitialization() {
        assertEquals(40, dairyCart.getCartSpeed());
        assertEquals(300, dairyCart.getCartCapacity());
        assertEquals(1500, dairyCart.getScoreValue());
        assertEquals(300, dairyCart.getMoneyValue());
    }

    @Test
    void testInitializationChangedSpeed() {
        dairyCart = new DairyCart(-5);
        assertEquals(35, dairyCart.getCartSpeed());
        assertEquals(300, dairyCart.getCartCapacity());
        assertEquals(1500, dairyCart.getScoreValue());
        assertEquals(300, dairyCart.getMoneyValue());
    }

    @Test
    void testGetScoreValue() {
        assertEquals(1500, dairyCart.getScoreValue());
    }

    @Test
    void testGetMoneyValue() {
        assertEquals(300, dairyCart.getMoneyValue());
    }
}

