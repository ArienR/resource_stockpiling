package seng201.team0.models;

public class MeatCart extends Cart{

    private String cartType;
    private int scoreValue;
    private int cartCompensation;

    public MeatCart(int changedCartSpeed) {
        super(20, 200);
        this.cartType = "Meat";
        this.scoreValue = 2000;
        this.cartCompensation = 500;
    }

}
