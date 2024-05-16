package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class DairyTower extends Tower {
    private static final List<String> dairyNames = List.of("Milk Tower", "Cheese Tower", "Yogurt Tower", "Butter Tower");

    public DairyTower(float towerSpeed, int towerFillAmount, int buyPrice, int gameBonus) {
        super(towerSpeed, towerFillAmount, buyPrice, gameBonus);
    }

    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return dairyNames.get(rand.nextInt(dairyNames.size()));
    }
}
