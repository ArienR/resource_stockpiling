package seng201.team0.models;

/**
 * Represents a generic cart used in the game. This class serves as a base for specific types of carts.
 * It defines common properties such as cart speed and capacity.
 */
public class Cart {

    /**
     * The speed at which the cart moves in kp/h.
     */
    public int cartSpeed;

    /**
     * The capacity of each cart.
     */
    public int cartCapacity;

    /**
     * Constructs a new Cart with specified speed and capacity.
     *
     * @param tempCartSpeed    The initial speed of the cart.
     * @param tempCartCapacity The carrying capacity of the cart.
     */
    Cart(int tempCartSpeed, int tempCartCapacity) {
        this.cartSpeed = tempCartSpeed;
        this.cartCapacity = tempCartCapacity;
    }

    /**
     * Retrieves the speed of the cart.
     * This speed influences how quickly the cart completes its route in the game.
     *
     * @return The current speed of the cart.
     */
    public int getCartSpeed() {
        return cartSpeed;
    }

    /**
     * Retrieves the capacity of the cart.
     * This capacity determines how much of a specific resource (e.g., produce, meat, dairy) the cart can carry per trip.
     *
     * @return The capacity of the cart.
     */
    public int getCartCapacity() {
        return cartCapacity;
    }
}
