package seng201.team0.models;

public class MeatCart extends Cart{

    private String cartType;

    MeatCart(int tempCartSpeed, int tempCartCapacity, String tempCartType) {
        super(10, 10);
        this.cartType = tempCartType;
    }

}
