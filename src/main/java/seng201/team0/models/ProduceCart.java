package seng201.team0.models;

public class ProduceCart extends Cart{

    private String cartType = "Produce";
    private int scoreValue = 70;

    public ProduceCart(int changedCartSpeedPercentage) {
        super(10, 10);
    }

    public String getCartType() {
        return cartType;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
