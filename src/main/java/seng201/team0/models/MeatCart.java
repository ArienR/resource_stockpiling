package seng201.team0.models;

public class MeatCart extends Cart{

    public MeatCart(int tempBonusSpeed) {
        super(35 + tempBonusSpeed, 200);
    }

    public int getScoreValue() {
        return 1000;
    }

    public int getMoneyValue() {
        return 200;
    }
}
