package seng201.team0.models;

public class DairyCart extends Cart{

    private String cartType;
    private int scoreValue;
    private int cartCompensation;

    public DairyCart(int changedCartSpeedPercentage) {
        super(30, 30);
        this.cartType = "Dairy";
        this.scoreValue = 3000;
        this.cartCompensation = 700;
    }
}
