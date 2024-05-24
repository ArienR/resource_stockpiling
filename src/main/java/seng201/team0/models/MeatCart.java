package seng201.team0.models;

/**
 * Represents a cart specifically designed for transporting meat in the game.
 */
public class MeatCart extends Cart{

    /**
     * Constructs a MeatCart with a specified bonus to speed when it is instantiated.
     * Sets the base speed and capacity of the cart, considering the speed adjustment.
     *
     * @param tempBonusSpeed The additional speed bonus applied to the base speed of the cart.
     */
    public MeatCart(int tempBonusSpeed) {
        super(35 + tempBonusSpeed, 200);
    }

    /**
     * Gets the score value of a meat cart.
     *
     * @return The score value of a meat cart fixed at 500.
     */
    public int getScoreValue() {
        return 1000;
    }

    /**
     * Gets the monetary value of a meat cart.
     *
     * @return monetary value of filling a meat cart.
     */
    public int getMoneyValue() {
        return 200;
    }
}
