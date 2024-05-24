package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.ProduceCart;

import static org.junit.jupiter.api.Assertions.*;

public class ProduceCartTest {

    private ProduceCart produceCart;

    @BeforeEach
    public void setupTest() {
        produceCart = new ProduceCart(0);
    }

    @Test
    void testInitialization() {
        assertEquals(30, produceCart.getCartSpeed());
        assertEquals(100, produceCart.getCartCapacity());
        assertEquals(500, produceCart.getScoreValue());
        assertEquals(100, produceCart.getMoneyValue());
    }

    @Test
    void testInitializationChangedSpeed() {
        produceCart = new ProduceCart(-5);
        assertEquals(25, produceCart.getCartSpeed());
        assertEquals(100, produceCart.getCartCapacity());
        assertEquals(500, produceCart.getScoreValue());
        assertEquals(100, produceCart.getMoneyValue());
    }

    @Test
    void testGetScoreValue() {
        assertEquals(500, produceCart.getScoreValue());
    }

    @Test
    void testGetMoneyValue() {
        assertEquals(100, produceCart.getMoneyValue());
    }
}