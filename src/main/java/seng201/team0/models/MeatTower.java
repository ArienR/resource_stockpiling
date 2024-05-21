package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class MeatTower extends Tower {
    private static final List<String> meatNames = List.of(
            "Beef Tower", "Chicken Tower", "Pork Tower", "Lamb Tower",
            "Turkey Tower", "Duck Tower", "Venison Tower", "Bison Tower",
            "Rabbit Tower", "Goat Tower", "Salmon Tower", "Tuna Tower",
            "Shrimp Tower", "Crab Tower", "Lobster Tower", "Clam Tower",
            "Mussel Tower", "Oyster Tower", "Sausage Tower", "Bacon Tower"
    );

    public MeatTower(int towerSpeed, int towerFillAmount, int buyPrice, int gameBonus) {
        super(towerSpeed, towerFillAmount, buyPrice, gameBonus);
    }

    public MeatTower() {} // Empty constructor to store typeAffected for an item

    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return meatNames.get(rand.nextInt(meatNames.size()));
    }
}
