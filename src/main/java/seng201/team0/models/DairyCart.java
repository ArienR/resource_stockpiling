package seng201.team0.models;

public class DairyCart extends Cart{

    public DairyCart(int tempBonusSpeed) {
        super(40 + tempBonusSpeed, 300);
    }

    public int getScoreValue() {
        return 1500;
    }

    public int getMoneyValue() {
        return 300;
    }
}
