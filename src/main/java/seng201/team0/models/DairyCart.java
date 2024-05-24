package seng201.team0.models;

public class DairyCart extends Cart{

    private String cartType;
    private int scoreValue = 1500;
    private int moneyValue = 300;

    public DairyCart(int changedCartSpeed) {
        super(40, 300);
        this.cartType = "Dairy";
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public int getMoneyValue() {
        return moneyValue;
    }
}
