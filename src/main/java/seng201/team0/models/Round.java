package seng201.team0.models;

public class Round {

    private int produceCount;
    private int meatCount;
    private int dairyCount;
    private int cartSpeedPercentage;


//  Constructor for first round
    public Round(int tempProduceCount, int tempCartSpeedPercentage) {
        this.produceCount = tempProduceCount;
        this.cartSpeedPercentage = tempCartSpeedPercentage;
    }

    public int getProduceCount() {
        return produceCount;
    }

    public int getCartSpeedPercentage() {
        return cartSpeedPercentage;
    }

    //create method to sort out active towers into their types
}
