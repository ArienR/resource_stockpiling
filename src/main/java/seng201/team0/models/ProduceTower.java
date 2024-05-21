package seng201.team0.models;

import java.util.List;
import java.util.Random;

public class ProduceTower extends Tower {

    private static final List<String> produceNames = List.of("Apple Tower", "Banana Tower", "Orange Tower", "Grape Tower");

    public ProduceTower(int towerSpeed, int towerFillAmount, int buyPrice, int gameBonus) {
        super(towerSpeed, towerFillAmount, buyPrice, gameBonus);
    }

    @Override
    protected String generateRandomName() {
        Random rand = new Random();
        return produceNames.get(rand.nextInt(produceNames.size()));
    }
}