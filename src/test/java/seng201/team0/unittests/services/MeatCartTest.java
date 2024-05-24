package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.MeatCart;
import seng201.team0.models.ProduceCart;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeatCartTest {

    private MeatCart meatCart;

    @BeforeEach
    public void setupTest() {
        meatCart = new MeatCart();
    }

    @Test
    void testInitialization() {
        assertEquals(35, meatCart.getCartSpeed());
        assertEquals(200, meatCart.getCartCapacity());
        assertEquals(1000, meatCart.getScoreValue());
        assertEquals(200, meatCart.getMoneyValue());
    }

    @Test
    void testGetScoreValue() {
        assertEquals(1000, meatCart.getScoreValue());
    }

    @Test
    void testGetMoneyValue() {
        assertEquals(200, meatCart.getMoneyValue());
    }
}
