package seng201.team0.models;

public class DairyCart extends Cart{

    private String cartType;

    public DairyCart(int changedCartSpeedPercentage) {
        super(30, 30);
        this.cartType = "Dairy";
    }

}
