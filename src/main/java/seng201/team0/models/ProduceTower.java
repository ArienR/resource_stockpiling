package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class ProduceTower extends Tower {

    private static final List<String> produceNames = List.of(
            "Apple Tower", "Banana Tower", "Orange Tower", "Grape Tower",
            "Cherry Tower", "Peach Tower", "Pineapple Tower", "Watermelon Tower",
            "Mango Tower", "Blueberry Tower", "Strawberry Tower", "Lemon Tower",
            "Kiwi Tower", "Avocado Tower", "Coconut Tower", "Papaya Tower",
            "Plum Tower", "Pear Tower", "Pomegranate Tower", "Fig Tower");

    public ProduceTower(int towerSpeed, int towerFillAmount, int buyPrice, float difficultyBonus, int towerLevel) {
        super(towerSpeed, towerFillAmount, buyPrice, difficultyBonus, towerLevel);
    }

    public ProduceTower() {} // Empty constructor with no attributes to store typeAffected for an item

    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return produceNames.get(rand.nextInt(produceNames.size()));
    }
}