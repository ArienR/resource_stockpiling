package seng201.team0.models;

import java.util.List;
import java.util.Random;

/**
 * Represents a Produce Tower in the game to handle filling produce carts.
 * This tower class includes functionality specific to produce-related operations, distinguishing it
 * from other types of towers.
 */
public class ProduceTower extends Tower {

    /**
     * The list of possible names for a Produce Tower.
     */
    private static final List<String> produceNames = List.of(
            "Apple Tower", "Banana Tower", "Orange Tower", "Grape Tower",
            "Cherry Tower", "Peach Tower", "Pineapple Tower", "Watermelon Tower",
            "Mango Tower", "Blueberry Tower", "Strawberry Tower", "Lemon Tower",
            "Kiwi Tower", "Avocado Tower", "Coconut Tower", "Papaya Tower",
            "Plum Tower", "Pear Tower", "Pomegranate Tower", "Fig Tower");

    /**
     * Constructs a ProduceTower with specified parameters.
     *
     * @param towerSpeed The speed at which the tower operates.
     * @param towerFillAmount The amount of product the tower can fill.
     * @param buyPrice The cost to purchase the tower.
     * @param difficultyBonus The difficulty multiplier affecting sell price.
     * @param towerLevel The initial level of the tower.
     */
    public ProduceTower(int towerSpeed, int towerFillAmount, int buyPrice, float difficultyBonus, int towerLevel) {
        super(towerSpeed, towerFillAmount, buyPrice, difficultyBonus, towerLevel);
    }

    /**
     * Empty constructor for ProduceTower, used for identifying the type of tower an item will affect.
     */
    public ProduceTower() {}

    /**
     * Generates a random name for the ProduceTower from the predefined list of produce tower names.
     *
     * @return A randomly selected name from the list of produce names.
     */
    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return produceNames.get(rand.nextInt(produceNames.size()));
    }
}