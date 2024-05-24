package seng201.team0.services;

import seng201.team0.GameManager;
import seng201.team0.models.*;

import java.util.List;


/**
 * Service which handles the logic for determining if a player can win a given round based on
 * the tower fill rates, cart capacities, and equipped items.
 */
public class RoundLogicService {

    /**
     * The GameManager singleton.
     */
    private final GameManager gameManager;

    /**
     * Constructs a new RoundLogicService associated with a game manager.
     *
     * @param gameManager the game manager instance managing game state and interactions.
     */
    public RoundLogicService(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Determines if the player can win the current round by comparing the required fill rates
     * for each cart type to the towers' fill rates adjusted by the users equipped items.
     *
     * @return true if the main towers and items win the round; false otherwise,
     */
    public boolean canWinRound() {
        GenerateCartsService generateCarts = new GenerateCartsService(gameManager);
        List<Item> items = gameManager.getPlayer().getEquippedItems();

        List<ProduceCart> produceCarts = generateCarts.generateProduceCarts();
        List<MeatCart> meatCarts = generateCarts.generateMeatCarts();
        List<DairyCart> dairyCarts = generateCarts.generateDairyCarts();

        int totalProduceCartCapacity = calculateTotalCartCapacity(produceCarts);
        int totalMeatCartCapacity = calculateTotalCartCapacity(meatCarts);
        int totalDairyCartCapacity = calculateTotalCartCapacity(dairyCarts);

        List<Tower> towers = gameManager.getPlayer().getEquippedTowers();
        boolean canWin = true;
        int trackDistance = gameManager.getTrackDistance();
        // size - 1 used to compensate for time between first cart spawning and last cart finishing
        float kphToMps = 3.6f;
        if (!produceCarts.isEmpty()) {
            float timeAvailableForProduce = trackDistance / ((produceCarts.get(0).getCartSpeed() / kphToMps) + produceCarts.size() - 1);
            float produceFillRateRequired = totalProduceCartCapacity / timeAvailableForProduce;
            float produceTowerFillRate = calculateTowerFillRate(towers, ProduceTower.class, items);
            if (produceTowerFillRate <= produceFillRateRequired) {
                canWin = false;
            }
        }
        if (!meatCarts.isEmpty()) {
            float timeAvailableForMeat = trackDistance / ((meatCarts.get(0).getCartSpeed() / kphToMps) + meatCarts.size() - 1);
            float meatFillRateRequired = totalMeatCartCapacity / timeAvailableForMeat;
            float meatTowerFillRate = calculateTowerFillRate(towers, MeatTower.class, items);
            if (meatTowerFillRate <= meatFillRateRequired) {
                canWin = false;
            }
        }
        if (!dairyCarts.isEmpty()) {
            float timeAvailableForDairy = trackDistance / ((dairyCarts.get(0).getCartSpeed() / kphToMps) + dairyCarts.size() - 1);
            float dairyFillRateRequired = totalDairyCartCapacity / timeAvailableForDairy;
            float dairyTowerFillRate = calculateTowerFillRate(towers, DairyTower.class, items);
            if (dairyTowerFillRate <= dairyFillRateRequired) {
                canWin = false;
            }
        }

        return canWin;
    }

    /**
     * Calculates the fill rate for a specific type of tower, considering equipped items.
     *
     * @param <T>       the type of the tower.
     * @param towers    the list of towers equipped.
     * @param towerType the class of the tower type to calculate the fill rate for.
     * @param items     the list of equipped items that might affect the towers.
     * @return the total fill rate for the given type of towers, adjusted by equipped items.
     */
    public <T extends Tower> float calculateTowerFillRate(List<Tower> towers, Class<T> towerType, List<Item> items) {
        float totalItemIncrease = calculateTotalItemIncrease(towerType, items);

        return calculateTotalTowerFillRate(towers, towerType, totalItemIncrease);
    }

    /**
     * Calculates the total increase factor from equipped items that affect a specific type of tower.
     *
     * @param <T>       the type of the tower.
     * @param towerType the class of towers to check against the items.
     * @param items the items equipped by the player.
     * @return the total increase factor for tower fill rate and collection rate due to items.
     */
    public <T extends Tower> float calculateTotalItemIncrease(Class<T> towerType, List<Item> items) {
        float totalItemIncrease = 1.0f;

        for (Item item : items) {
            if (towerType.isAssignableFrom(item.getTowerTypeAffected().getClass())) {
                totalItemIncrease += (item.getSpeedIncrease() + item.getFillIncrease()) / 100.0f;
            }
        }

        return totalItemIncrease;
    }

    /**
     * Calculates the cumulative fill rate for all towers of a specific type, modified by item effects.
     *
     * @param <T>       the type of the tower.
     * @param towers the list of towers equipped.
     * @param towerType the class of the tower type to calculate for.
     * @param totalItemIncrease the cumulative increase factor from items.
     * @return the cumulative fill rate for the towers.
     */
    public <T extends Tower> float calculateTotalTowerFillRate(List<Tower> towers, Class<T> towerType, float totalItemIncrease) {
        float totalTowerFillRate = 0.0f;

        for (Tower tower : towers) {
            if (towerType.isInstance(tower)) {
                float towerFillRate = (float) (tower.getTowerFillAmount() * tower.getTowerSpeed() * (3 + tower.getTowerLevel())) /4;
                totalTowerFillRate += towerFillRate * totalItemIncrease;
            }
        }

        return totalTowerFillRate;
    }

    /**
     * Calculates the total capacity of a list of carts.
     *
     * @param carts the carts to calculate total capacity for.
     * @return the total capacity of all carts in the list.
     */
    public int calculateTotalCartCapacity(List<? extends Cart> carts) {
        int totalCartCapacity = 0;
        for (Cart cart : carts) {
            totalCartCapacity += cart.getCartCapacity();
        }
        return totalCartCapacity;
    }
}
