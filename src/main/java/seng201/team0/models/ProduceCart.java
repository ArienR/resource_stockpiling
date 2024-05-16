package seng201.team0.models;

public class ProduceCart extends Cart{

    private String cartType;

    ProduceCart(int tempCartSpeed, int tempCartCapacity, String tempCartType) {
        super(10, 10);
        this.cartType = tempCartType;
    }
}
