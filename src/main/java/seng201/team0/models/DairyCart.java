package seng201.team0.models;

public class DairyCart extends Cart{

    private String cartType;

    DairyCart(int tempCartSpeed, int tempCartCapacity, String tempCartType) {
        super(10, 10);
        this.cartType = tempCartType;
    }

}
