//package seng201.team0.services;
//import javafx.animation.Animation;
//import javafx.fxml.Initializable;
//import javafx.scene.Group;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import seng201.team0.models.Cart;
//import javafx.application.Application;
//
//import java.awt.*;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class AnimateCartsService{
//    int cartSpeed;
//    int cartCapacity;
//    String cartType;
//
//    int trackDistance;
//
//    public AnimateCartsService(Cart cart, String cartType, ) {
//        cartSpeed = cart.cartSpeed;
//        cartCapacity = cart.cartCapacity;
//        this.cartType = cartType;
//    }
//
//    @Override
//    public void initialize(){
//
//    }
//
//    public generatePane(){
//        Rectangle cartGui = new Rectangle(100,100,100,100);
//        cartGui.setFill(Color.RED);
//
//        Group root = new Group(cartGui);
//        Scene scene = new Scene(root, 600, 300);
//        stage.setTitle("JavaFX Animation");
//        stage.setScene(scene);
//        stage.show();
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
//}
