package seng201.team0.models;

public class MeatCart extends Cart{

    private String cartType;
    private int scoreValue;
    private int cartCompensation;

    public MeatCart(int changedCartSpeedPercentage) {
        super(20, 20);
        this.cartType = "Meat";
        this.scoreValue = 2000;
        this.cartCompensation = 500;
    }

}
