package seng201.team0.models;


/**
 * An interface defining the necessary methods for any object that can be purchased or sold in the games inventory shop.
 * Classes implementing this interface must provide methods to retrieve the buy and sell prices.
 */
public interface Purchasable {

    /**
     * Gets the buy price of the item.
     *
     * @return The price at which the item can be purchased.
     */
    int getBuyPrice();


    /**
     * Gets the sell price of the item.
     *
     * @return The price at which the item can be sold back to the game.
     */
    int getSellPrice();
}
