package seng201.team0.models;

public class ProduceCart extends Cart{

    private String cartType = "Produce";
    private int scoreValue = 500;

    private int moneyValue = 100;
    private int cartCompensation;

    public ProduceCart(int changedCartSpeedPercentage) {
        super(30, 100);
        this.cartCompensation = 300;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public int getMoneyValue() {
        return moneyValue;
    }
}
