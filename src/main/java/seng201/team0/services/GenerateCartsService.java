package seng201.team0.services;

import seng201.team0.GameManager;
import seng201.team0.models.DairyCart;
import seng201.team0.models.MeatCart;
import seng201.team0.models.ProduceCart;
import seng201.team0.models.Round;

import java.util.ArrayList;
import java.util.List;

/**
 * The service which is responsible for generating carts based on the upcoming round object from GameManager.
 * It creates lists of produce, meat, and dairy carts as determined by the upcoming round's requirements.
 */
public class GenerateCartsService {

    /**
     * The number of produce carts.
     */
    int produceCartCount;

    /**
     * The number of meat carts.
     */
    int meatCartCount;

    /**
     * The number of dairy carts.
     */
    int dairyCartCount;

    /**
     * The changed cart speed as chosen by the user.
     */
    int changedCartSpeed;

    /**
     * Initializes the service with configuration from the upcoming round in the game.
     *
     * @param gameManager The game manager instance that holds state of the game.
     */
    public GenerateCartsService(GameManager gameManager){
        Round upcomingRound = gameManager.getUpcomingRound();
        produceCartCount = upcomingRound.getProduceCount();
        meatCartCount = upcomingRound.getMeatCount();
        dairyCartCount = upcomingRound.getDairyCount();
        changedCartSpeed = upcomingRound.getChangedCartSpeed();
    }

    /**
     * Generates a list of ProduceCart objects based on the count specified in the upcoming round.
     *
     * @return List of ProduceCart objects, each configured with a modified cart speed.
     */
    public List<ProduceCart> generateProduceCarts(){
        List<ProduceCart> listOfProduceCarts = new ArrayList<>();
        for (int i = 0; i < produceCartCount; i++){
            listOfProduceCarts.add(new ProduceCart(changedCartSpeed));
        }
        return listOfProduceCarts;
    }

    /**
     * Generates a list of MeatCart objects based on the count specified in the upcoming round.
     *
     * @return List of MeatCart objects, each configured with a modified cart speed.
     */
    public List<MeatCart> generateMeatCarts(){
        List<MeatCart> listOfMeatCarts = new ArrayList<>();
        for (int i = 0; i < meatCartCount; i++){
            listOfMeatCarts.add(new MeatCart(changedCartSpeed));
        }
        return listOfMeatCarts;
    }


    /**
     * Generates a list of DairyCart objects based on the count specified in the upcoming round.
     *
     * @return List of DairyCart objects, each configured with a modified cart speed.
     */
    public List<DairyCart> generateDairyCarts(){
        List<DairyCart> listOfDairyCarts = new ArrayList<DairyCart>();
        for (int i = 0; i < dairyCartCount; i++){
            listOfDairyCarts.add(new DairyCart(changedCartSpeed));
        }
        return listOfDairyCarts;
    }

    /**
     * Gets the changed speed of carts for the upcoming round.
     *
     * @return The speed adjustment for the carts as determined by the upcoming round.
     */
    public int getChangedCartSpeed(){
        return changedCartSpeed;
    }

}
