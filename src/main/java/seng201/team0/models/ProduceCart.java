package seng201.team0.models;

/**
 * Represents a cart specifically designed for transporting produce in the game.
 */
public class ProduceCart extends Cart{

    /**
     * Constructs a ProduceCart with a specified bonus to speed when it is instantiated.
     * Sets the base speed and capacity of the cart, considering the speed adjustment.
     *
     * @param tempBonusSpeed The additional speed bonus applied to the base speed of the cart.
     */
    public ProduceCart(int tempBonusSpeed) {
        super(30 + tempBonusSpeed, 100);
    }

    /**
     * Gets the score value of a produce cart.
     *
     * @return The score value of a produce cart fixed at 500.
     */
    public int getScoreValue() {
        return 500;
    }

    /**
     * Gets the monetary value of a produce cart.
     *
     * @return monetary value of filling a produce cart.
     */
    public int getMoneyValue() {
        return 100;
    }
}
