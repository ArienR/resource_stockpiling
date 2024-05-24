package seng201.team0.models;

public class DairyCart extends Cart{

    public DairyCart() {
        super(40, 300);
    }

    public int getScoreValue() {
        return 1500;
    }

    public int getMoneyValue() {
        return 300;
    }
}
