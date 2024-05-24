package seng201.team0.models;

public class MeatCart extends Cart{

    private String cartType;
    private int scoreValue = 300;
    private int moneyValue = 200;

    public MeatCart(int changedCartSpeed) {
        super(35, 200);
        this.cartType = "Meat";
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public int getMoneyValue() {
        return moneyValue;
    }
}
