package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.models.Round;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing for the Round Class
 */
public class RoundTest {
    /**
     * Testing the getters from round class
     */
    private Round round;

    @BeforeEach
    public void setupTest() {
        round = new Round(5, 3, 2, 10);
    }

    @Test
    void testInitialization() {
        Round round = new Round(5, 3, 2, 10);
        assertEquals(5, round.getProduceCount());
        assertEquals(3, round.getMeatCount());
        assertEquals(2, round.getDairyCount());
        assertEquals(10, round.getChangedCartSpeed());
    }

    @Test
    void testGetProduceCount() {
        assertEquals(5, round.getProduceCount());
    }

    @Test
    void testGetMeatCount() {
        assertEquals(3, round.getMeatCount());
    }

    @Test
    void testGetDairyCount() {
        assertEquals(2, round.getDairyCount());
    }

    @Test
    void testGetChangedCartSpeed() {
        assertEquals(10, round.getChangedCartSpeed());
    }
}
