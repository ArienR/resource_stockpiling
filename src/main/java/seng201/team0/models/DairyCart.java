package seng201.team0.models;

/**
 * Represents a cart specifically designed for transporting dairy in the game.
 */
public class DairyCart extends Cart{

    /**
     * Constructs a DairyCart with a specified bonus to speed when it is instantiated.
     * Sets the base speed and capacity of the cart, considering the speed adjustment.
     *
     * @param tempBonusSpeed The additional speed bonus applied to the base speed of the cart.
     */
    public DairyCart(int tempBonusSpeed) {
        super(40 + tempBonusSpeed, 300);
    }

    /**
     * Gets the score value of a dairy cart.
     *
     * @return The score value of a dairy cart fixed at 500.
     */
    public int getScoreValue() {
        return 1500;
    }

    /**
     * Gets the monetary value of a dairy cart.
     *
     * @return monetary value of filling a dairy cart.
     */
    public int getMoneyValue() {
        return 300;
    }
}
