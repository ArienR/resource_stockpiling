package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class MeatTower extends Tower {
    private static final List<String> meatNames = List.of("Beef Tower", "Chicken Tower", "Pork Tower", "Lamb Tower");

    public MeatTower(int towerSpeed, int towerFillAmount, int buyPrice, int gameBonus) {
        super(towerSpeed, towerFillAmount, buyPrice, gameBonus);
    }

    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return meatNames.get(rand.nextInt(meatNames.size()));
    }
}
