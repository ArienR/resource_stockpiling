package seng201.team0.services;
import javafx.animation.Animation;
import javafx.scene.layout.AnchorPane;
import seng201.team0.models.Cart;

public class AnimateCartsService {
    int cartSpeed;
    int cartCapacity;
    String cartType;

    int trackDistance;

    public AnimateCartsService(Cart cart, String cartType) {
        cartSpeed = cart.cartSpeed;
        cartCapacity = cart.cartCapacity;
        this.cartType = cartType;
    }

    public generatePane(){
        AnchorPane cartPane = new AnchorPane();
    }

}
