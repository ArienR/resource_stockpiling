package seng201.team0.models;

import java.util.List;
import java.util.Random;

/**
 * Represents a Dairy Tower in the game to handle filling dairy carts.
 * This tower class includes functionality specific to dairy-related operations, distinguishing it
 * from other types of towers.
 */
public class DairyTower extends Tower {

    /**
     * The list of possible names for a Dairy Tower.
     */
    private static final List<String> dairyNames = List.of(
            "Milk Tower", "Cheese Tower", "Butter Tower", "Yogurt Tower",
            "Cream Tower", "Ice Cream Tower", "Cottage Cheese Tower", "Sour Cream Tower",
            "Whipped Cream Tower", "Buttermilk Tower", "Ricotta Tower", "Mozzarella Tower",
            "Cheddar Tower", "Parmesan Tower", "Feta Tower", "Gouda Tower",
            "Brie Tower", "Swiss Cheese Tower", "Blue Cheese Tower", "Provolone Tower");

    /**
     * Constructs a DairyTower with specified parameters.
     *
     * @param towerSpeed The speed at which the tower operates.
     * @param towerFillAmount The amount of product the tower can fill.
     * @param buyPrice The cost to purchase the tower.
     * @param difficultyBonus The difficulty multiplier affecting sell price.
     * @param towerLevel The initial level of the tower.
     */
    public DairyTower(int towerSpeed, int towerFillAmount, int buyPrice, float difficultyBonus, int towerLevel) {
        super(towerSpeed, towerFillAmount, buyPrice, difficultyBonus, towerLevel);
    }

    /**
     * Empty constructor for DairyTower, used for identifying the type of tower an item will affect.
     */
    public DairyTower() {} // Empty constructor to store typeAffected for an item

    /**
     * Generates a random name for the DairyTower from the predefined list of dairy tower names.
     *
     * @return A randomly selected name from the list of diary names.
     */
    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return dairyNames.get(rand.nextInt(dairyNames.size()));
    }
}
