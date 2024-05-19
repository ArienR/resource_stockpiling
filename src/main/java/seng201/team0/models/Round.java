package seng201.team0.models;

public class Round {

    private int produceCount;
    private int meatCount;
    private int dairyCount;
    private int cartSpeedPercentage;


//  Constructor for first round
    public Round(int tempProduceCount, int tempMeatCount, int tempDairyCount, int tempCartSpeedPercentage) {
        this.produceCount = tempProduceCount;
        this.meatCount = tempMeatCount;
        this.dairyCount = tempDairyCount;
        this.cartSpeedPercentage = tempCartSpeedPercentage;
    }

    public int getProduceCount() {
        System.out.println("P" +produceCount);
        return produceCount;
    }

    public int getMeatCount(){
        System.out.println("M" + meatCount);
        return meatCount;
    }

    public int getDairyCount(){
        System.out.println("D" + dairyCount);
        return dairyCount;
    }

    public int getCartSpeedPercentage() {
        return cartSpeedPercentage;
    }
}
