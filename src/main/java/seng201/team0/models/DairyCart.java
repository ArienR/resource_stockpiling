package seng201.team0.models;

public class DairyCart extends Cart{

    private String cartType;
    private int scoreValue;
    private int cartCompensation;

    public DairyCart(int changedCartSpeed) {
        super(40, 300);
        this.cartType = "Dairy";
        this.scoreValue = 3000;
        this.cartCompensation = 700;
    }
}
