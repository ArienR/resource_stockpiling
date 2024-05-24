package seng201.team0.models;

/**
 * Represents a single game round, holding the number of carts required for produce, meat, and dairy,
 * as well as any changes to the speed of the carts for the round.
 */
public class Round {

    /**
     * The amount of produce carts in the round.
     */
    private final int produceCount;

    /**
     * The amount of meat carts in the round.
     */
    private final int meatCount;

    /**
     * The amount of dairy carts in the round.
     */
    private final int dairyCount;

    /**
     * The cart speed increase/decrease for that round.
     */
    private final int cartSpeedChange;

    /**
     * Constructs a new round with specific counts for produce, meat, dairy carts and a speed change for the carts.
     *
     * @param tempProduceCount      The number of produce carts required for the round.
     * @param tempMeatCount         The number of meat carts required for the round.
     * @param tempDairyCount        The number of dairy carts required for the round.
     * @param tempCartSpeedChange   The change in speed of the carts for this round.
     */
    public Round(int tempProduceCount, int tempMeatCount, int tempDairyCount, int tempCartSpeedChange) {
        this.produceCount = tempProduceCount;
        this.meatCount = tempMeatCount;
        this.dairyCount = tempDairyCount;
        this.cartSpeedChange = tempCartSpeedChange;
    }

    /**
     * Gets the number of produce carts required for this round.
     *
     * @return The number of produce carts.
     */
    public int getProduceCount() {
        return produceCount;
    }

    /**
     * Gets the number of meat carts required for this round.
     *
     * @return The number of meat carts.
     */
    public int getMeatCount(){
        return meatCount;}

    /**
     * Gets the number of dairy carts required for this round.
     *
     * @return The number of dairy carts.
     */
    public int getDairyCount(){
        return dairyCount;}

    /**
     * Gets the changed speed of carts for this round.
     *
     * @return The speed change applied to all carts in the round.
     */
    public int getChangedCartSpeed() {
        return cartSpeedChange;
    }
}
