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
        List<Item> items = gameManager.getPlayer().getEquippedItems();

        List<ProduceCart> produceCarts = generateCarts.generateProduceCarts(gameManager);
        List<MeatCart> meatCarts = generateCarts.generateMeatCarts(gameManager);
        List<DairyCart> dairyCarts = generateCarts.generateDairyCarts(gameManager);

        int totalProduceCartCapacity = calculateTotalCartCapacity(produceCarts);
        int totalMeatCartCapacity = calculateTotalCartCapacity(meatCarts);
        int totalDairyCartCapacity = calculateTotalCartCapacity(dairyCarts);

        List<Tower> towers = gameManager.getPlayer().getEquippedTowers();
        boolean canWin = true;
        int trackDistance = gameManager.getTrackDistance();
        // size - 1 used to compensate for time between first cart spawning and last cart finishing
        if (!produceCarts.isEmpty()) {
            float timeAvailableForProduce = trackDistance / ((produceCarts.get(0).getCartSpeed() / KPH_TO_MPS) + produceCarts.size() - 1);
            float produceFillRateRequired = totalProduceCartCapacity / timeAvailableForProduce;
            float produceTowerFillRate = calculateTowerFillRate(towers, ProduceTower.class, items);
            System.out.println("Produce tower fill: " + produceTowerFillRate);
            System.out.println("Produce required fill: " + produceFillRateRequired);
            if (produceTowerFillRate <= produceFillRateRequired) {
                canWin = false;
            }
        }
        if (!meatCarts.isEmpty()) {
            float timeAvailableForMeat = trackDistance / ((meatCarts.get(0).getCartSpeed() / KPH_TO_MPS) + meatCarts.size() - 1);
            float meatFillRateRequired = totalMeatCartCapacity / timeAvailableForMeat;
            float meatTowerFillRate = calculateTowerFillRate(towers, MeatTower.class, items);
            System.out.println("Meat tower fill: " + meatTowerFillRate);
            System.out.println("Meat required fill: " + meatFillRateRequired);
            if (meatTowerFillRate <= meatFillRateRequired) {
                canWin = false;
            }
        }
        if (!dairyCarts.isEmpty()) {
            float timeAvailableForDairy = trackDistance / ((dairyCarts.get(0).getCartSpeed() / KPH_TO_MPS) + dairyCarts.size() - 1);
            float dairyFillRateRequired = totalDairyCartCapacity / timeAvailableForDairy;
            float dairyTowerFillRate = calculateTowerFillRate(towers, DairyTower.class, items);
            System.out.println("Dairy tower fill: " + dairyTowerFillRate);
            System.out.println("Dairy required fill: " + dairyFillRateRequired);
            if (dairyTowerFillRate <= dairyFillRateRequired) {
                canWin = false;
            }
        }

        return canWin;
    }

    private <T extends Tower> float calculateTowerFillRate(List<Tower> towers, Class<T> towerType, List<Item> items) {
        float totalItemIncrease = calculateTotalItemIncrease(towerType, items);
        float totalTowerFillRate = calculateTotalTowerFillRate(towers, towerType, totalItemIncrease);

        return totalTowerFillRate;
    }

    private <T extends Tower> float calculateTotalItemIncrease(Class<T> towerType, List<Item> items) {
        float totalItemIncrease = 1.0f;

        for (Item item : items) {
            if (towerType.isAssignableFrom(item.getTowerTypeAffected().getClass())) {
                totalItemIncrease += (item.getSpeedIncrease() + item.getCollectionIncrease()) / 100.0f;
            }
        }

        return totalItemIncrease;
    }

    private <T extends Tower> float calculateTotalTowerFillRate(List<Tower> towers, Class<T> towerType, float totalItemIncrease) {
        float totalTowerFillRate = 0.0f;

        for (Tower tower : towers) {
            if (towerType.isInstance(tower)) {
                float towerFillRate = tower.getTowerFillAmount() * tower.getTowerSpeed() * (3+tower.getTowerLevel())/4;
                totalTowerFillRate += towerFillRate * totalItemIncrease;
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
