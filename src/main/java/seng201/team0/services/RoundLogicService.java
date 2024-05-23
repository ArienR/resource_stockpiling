package seng201.team0.services;

import com.sun.tools.attach.AgentInitializationException;
import seng201.team0.GameManager;
import seng201.team0.gui.GameScreenController;
import seng201.team0.models.*;

import java.util.List;

public class RoundLogicService {

    private GameManager gameManager;
    private float KPH_TO_MPS = 3.6f;

    public RoundLogicService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public boolean canWinRound() {
        GenerateCartsService generateCarts = new GenerateCartsService(gameManager);

        List<ProduceCart> produceCarts = generateCarts.generateProduceCarts(gameManager);
        List<MeatCart> meatCarts = generateCarts.generateMeatCarts(gameManager);
        List<DairyCart> dairyCarts = generateCarts.generateDairyCarts(gameManager);

        int totalProduceCartCapacity = calculateTotalCartCapacity(produceCarts);
        int totalMeatCartCapacity = calculateTotalCartCapacity(meatCarts);
        int totalDairyCartCapacity = calculateTotalCartCapacity(dairyCarts);

        int trackDistance = gameManager.getTrackDistance();
        // size - 1 used to compensate for time between first cart spawning and last cart finishing
        float timeAvailableForProduce = trackDistance / ((produceCarts.get(0).getCartSpeed() / KPH_TO_MPS) + produceCarts.size() - 1);
        float timeAvailableForMeat = trackDistance / ((meatCarts.get(0).getCartSpeed() / KPH_TO_MPS) + meatCarts.size() - 1);
        float timeAvailableForDairy = trackDistance / ((dairyCarts.get(0).getCartSpeed() / KPH_TO_MPS) + dairyCarts.size() - 1);

        float produceFillRateRequired = totalProduceCartCapacity / timeAvailableForProduce;
        float meatFillRateRequired = totalMeatCartCapacity / timeAvailableForMeat;
        float dairyFillRateRequired = totalDairyCartCapacity / timeAvailableForDairy;

        List<Tower> towers = gameManager.getPlayer().getEquippedTowers();
        float produceTowerFillRate = calculateTowerFillRate(towers, ProduceTower.class);
        float meatTowerFillRate = calculateTowerFillRate(towers, MeatTower.class);
        float dairyTowerFillRate = calculateTowerFillRate(towers, DairyTower.class);

        boolean canWin = true;

        if (produceTowerFillRate <= produceFillRateRequired) {
            canWin = false;
        }
        if (meatTowerFillRate <= meatFillRateRequired) {
            canWin = false;
        }
        if (dairyTowerFillRate <= dairyFillRateRequired) {
            canWin = false;
        }

        return canWin;
    }

    private <T extends Tower> float calculateTowerFillRate(List<Tower> towers, Class<T> towerType) {
        float totalTowerFillRate = 0;
        for (Tower tower : towers) {
            if (towerType.isInstance(tower)) {
                totalTowerFillRate = (float) tower.getTowerFillAmount() / tower.getTowerSpeed();
            }
        }
        return totalTowerFillRate;
    }

    private int calculateTotalCartCapacity(List<? extends Cart> carts) {
        int totalCartCapacity = 0;
        for (Cart cart : carts) {
            totalCartCapacity += cart.getCartCapacity();
        }
        return totalCartCapacity;
    }
}
