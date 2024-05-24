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

    Round round = new Round(3, 4, 5, -5);

    /**
     * Testing the getters from round class
     */
    @Test
    void testGetters() {
        assertEquals(3, round.getProduceCount());
        assertEquals(4, round.getMeatCount());
        assertEquals(5, round.getDairyCount());
        assertEquals(-5, round.getChangedCartSpeed());
    }
}
