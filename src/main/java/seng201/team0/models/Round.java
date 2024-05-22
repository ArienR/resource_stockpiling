package seng201.team0.models;

public class Round {

    private int produceCount;
    private int meatCount;
    private int dairyCount;
    private int cartSpeedChange;


//  Constructor for first round
    public Round(int tempProduceCount, int tempMeatCount, int tempDairyCount, int tempCartSpeedChange) {
        this.produceCount = tempProduceCount;
        this.meatCount = tempMeatCount;
        this.dairyCount = tempDairyCount;
        this.cartSpeedChange = tempCartSpeedChange;
    }

    public int getProduceCount() {
        return produceCount;
    }

    public int getMeatCount(){
        return meatCount;}

    public int getDairyCount(){
        return dairyCount;}

    public int getChangedCartSpeed() {
        return cartSpeedChange;
    }
}
