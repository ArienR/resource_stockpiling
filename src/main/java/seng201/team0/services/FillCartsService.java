package seng201.team0.services;

import seng201.team0.GameManager;
import seng201.team0.models.DairyCart;
import seng201.team0.models.MeatCart;
import seng201.team0.models.ProduceCart;

import java.util.List;

public class FillCartsService {

    private GameManager gameManager;

    private List<ProduceCart> listOfProduceCarts;
    private List<MeatCart> listOfMeatCarts;
    private List<DairyCart> listOfDairyCarts;
    public FillCartsService(GameManager tempGameManager){
        this.gameManager = tempGameManager;
    }
    public void fillProduceCarts(List<ProduceCart> listOfProduceCarts){
        this.listOfProduceCarts = listOfProduceCarts;
        System.out.println(listOfProduceCarts);
    }

    public void fillMeatCarts(List<MeatCart> listOfMeatCarts){
        this.listOfMeatCarts = listOfMeatCarts;
        System.out.println(listOfMeatCarts);
    }

    public void fillDairyCarts(List<DairyCart> listOfDairyCarts){
        this.listOfDairyCarts = listOfDairyCarts;
        System.out.println(listOfDairyCarts);
    }

}
