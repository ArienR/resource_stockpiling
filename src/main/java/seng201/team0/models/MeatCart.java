package seng201.team0.models;

public class MeatCart extends Cart{

    public MeatCart() {
        super(35, 200);
    }

    public int getScoreValue() {
        return 1000;
    }

    public int getMoneyValue() {
        return 200;
    }
}
