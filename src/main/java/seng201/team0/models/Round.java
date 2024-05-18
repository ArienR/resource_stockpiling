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

    public int getMeatCount() {
        return meatCount;
    }

    public int getDairyCount() {
        return dairyCount;
    }
}
