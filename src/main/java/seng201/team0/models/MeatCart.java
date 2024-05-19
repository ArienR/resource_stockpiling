package seng201.team0.models;

public class MeatCart extends Cart{

    private String cartType;

    public MeatCart(int changedCartSpeedPercentage) {
        super(20, 20);
        this.cartType = "Meat";
    }

}
