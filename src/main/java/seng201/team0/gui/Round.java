package seng201.team0.gui;

import seng201.team0.GameManager;

public class Round {

    GameManager gameManager;

    private int produceQuantity;
    private int produceProductionRate;
    private int meatQuantity;
    private int meatProductionRate;
    private int breadQuantity;
    private int breadProductionRate;
    private int dairyQuantity;
    private int dairyProductionRate;

//    Constructor for first round
    public Round(int produceQuantity, int produceProductionRate) {
        this.produceQuantity = produceQuantity;
        this.produceProductionRate = produceProductionRate;
    }
}
