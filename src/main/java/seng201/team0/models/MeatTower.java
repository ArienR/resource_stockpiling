package seng201.team0.models;

import java.util.List;
import java.util.Random;

/**
 * Represents a meat Tower in the game to handle filling produce carts.
 * This tower class includes functionality specific to meat-related operations, distinguishing it
 * from other types of towers.
 */
public class MeatTower extends Tower {

    /**
     * The list of possible names for a Meat Tower.
     */
    private static final List<String> meatNames = List.of(
            "Beef Tower", "Chicken Tower", "Pork Tower", "Lamb Tower",
            "Turkey Tower", "Duck Tower", "Venison Tower", "Bison Tower",
            "Rabbit Tower", "Goat Tower", "Salmon Tower", "Tuna Tower",
            "Shrimp Tower", "Crab Tower", "Lobster Tower", "Clam Tower",
            "Mussel Tower", "Oyster Tower", "Sausage Tower", "Bacon Tower"
    );

    /**
     * Constructs a MeatTower with specified parameters.
     *
     * @param towerSpeed The speed at which the tower operates.
     * @param towerFillAmount The amount of product the tower can fill.
     * @param buyPrice The cost to purchase the tower.
     * @param difficultyBonus The difficulty multiplier affecting sell price.
     * @param towerLevel The initial level of the tower.
     */
    public MeatTower(int towerSpeed, int towerFillAmount, int buyPrice, float difficultyBonus, int towerLevel) {
        super(towerSpeed, towerFillAmount, buyPrice, difficultyBonus, towerLevel);
    }

    /**
     * Empty constructor for MeatTower, used for identifying the type of tower an item will affect.
     */
    public MeatTower() {} // Empty constructor to store typeAffected for an item

    /**
     * Generates a random name for the meatTower from the predefined list of meat tower names.
     *
     * @return A randomly selected name from the list of meat names.
     */
    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return meatNames.get(rand.nextInt(meatNames.size()));
    }
}
