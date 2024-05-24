package seng201.team0.models;

public class ProduceCart extends Cart{

    public ProduceCart() {
        super(30, 100);
    }

    public int getScoreValue() {
        return 500;
    }

    public int getMoneyValue() {
        return 100;

    }
}
