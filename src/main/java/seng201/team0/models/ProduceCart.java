package seng201.team0.models;

public class ProduceCart extends Cart{

    private String cartType = "Produce";
    private int scoreValue;
    private int cartCompensation;

    public ProduceCart(int changedCartSpeedPercentage) {
        super(10, 10);
        this.scoreValue = 1000;
        this.cartCompensation = 300;
    }

    public String getCartType() {
        return cartType;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
