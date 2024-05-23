package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class DairyTower extends Tower {
    private static final List<String> dairyNames = List.of(
            "Milk Tower", "Cheese Tower", "Butter Tower", "Yogurt Tower",
            "Cream Tower", "Ice Cream Tower", "Cottage Cheese Tower", "Sour Cream Tower",
            "Whipped Cream Tower", "Buttermilk Tower", "Ricotta Tower", "Mozzarella Tower",
            "Cheddar Tower", "Parmesan Tower", "Feta Tower", "Gouda Tower",
            "Brie Tower", "Swiss Cheese Tower", "Blue Cheese Tower", "Provolone Tower");

    public DairyTower(int towerSpeed, int towerFillAmount, int buyPrice, float difficultyBonus) {
        super(towerSpeed, towerFillAmount, buyPrice, difficultyBonus);
    }

    public DairyTower() {} // Empty constructor to store typeAffected for an item

    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return dairyNames.get(rand.nextInt(dairyNames.size()));
    }
}
