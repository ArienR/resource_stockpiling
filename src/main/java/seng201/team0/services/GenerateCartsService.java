package seng201.team0.services;

import seng201.team0.GameManager;
import seng201.team0.models.DairyCart;
import seng201.team0.models.MeatCart;
import seng201.team0.models.ProduceCart;
import seng201.team0.models.Round;

import java.util.ArrayList;
import java.util.List;

public class GenerateCartsService {

    int produceCartCount;
    int meatCartCount;
    int dairyCartCount;
    int changedCartSpeedPercentage;

    public GenerateCartsService(GameManager gameManager){
        Round upcomingRound = gameManager.getUpcomingRound();
        produceCartCount = upcomingRound.getProduceCount();
        meatCartCount = upcomingRound.getMeatCount();
        dairyCartCount = upcomingRound.getDairyCount();
        changedCartSpeedPercentage = upcomingRound.getCartSpeedPercentage();
    }

    public List<ProduceCart> generateProduceCarts(GameManager gameManager){
        List<ProduceCart> listOfProduceCarts = new ArrayList<>();
        for (int i = 0; i < produceCartCount; i++){
            listOfProduceCarts.add(new ProduceCart(changedCartSpeedPercentage));
        }
        return listOfProduceCarts;
    }

    public List<MeatCart> generateMeatCarts(GameManager gameManager){
        List<MeatCart> listOfMeatCarts = new ArrayList<>();
        for (int i = 0; i < meatCartCount; i++){
            listOfMeatCarts.add(new MeatCart(changedCartSpeedPercentage));
        }
        return listOfMeatCarts;
    }



    public List<DairyCart> generateDairyCarts(GameManager gameManager){
        List<DairyCart> listOfDairyCarts = new ArrayList<DairyCart>();
        for (int i = 0; i < dairyCartCount; i++){
            listOfDairyCarts.add(new DairyCart(changedCartSpeedPercentage));
        }
        return listOfDairyCarts;
    }

}
