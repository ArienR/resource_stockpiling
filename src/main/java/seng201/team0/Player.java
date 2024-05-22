package seng201.team0;

import seng201.team0.models.Item;
import seng201.team0.models.Tower;

import java.util.ArrayList;
import java.util.List;
public class Player {

    /**
     * Holds the players chosen name.
     */
    private String name;

    /**
     * Holds all the player's towers.
     */
    private final List<Tower> towerList;

    /**
     * Holds all the player's item.
     */
    private final List<Item> itemList;

    /**
     * Holds the list of purchased towers, so the user isn't able to buy duplicates.
     */
    private final List<Tower> purchasedTowers;

    /**
     * Holds the list of purchased items, so the user isn't able to buy duplicates.
     */
    private final List<Item> purchasedItems;

    /**
     * Holds the list of towers the player has chosen to use for the upcoming round.
     */
    private List<Tower> equippedTowers = new ArrayList<>();

    /**
     * Holds the list of items the player has chosen to use for the upcoming round.
     */
    private List<Item> equippedItems = new ArrayList<>();

    /**
     * Holds the players current money.
     */
    private int playerMoney;

    /**
     * Holds the total money accumulated by the player.
     */
    private int totalPlayerMoney;

    /**
     * Holds the players score value.
     */
    private int playerScore;

    /**
     * Creates a new player with a specified name. Initialises the score and
     * money to zero, and creates a new empty array list for items and towers.
     *
     * @param name chosen name by the user
     */
    public Player(String name) {
        this.name = name;
        this.playerScore = 0;
        this.totalPlayerMoney = 0;
        this.towerList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.purchasedTowers = new ArrayList<>();
        this.purchasedItems = new ArrayList<>();
    }

    /**
     * Gets the player's name.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name to that which they have chosen.
     *
     * @param tempName the players chosen name
     */
    public void setName(String tempName) {
        this.name = tempName;
    }

    /**
     * Gets all the player's currently owned towers.
     *
     * @return the list of the player's towers.
     */
    public List<Tower> getTowerList() {
        return towerList;
    }

    /**
     * Gets the player's current money amount.
     *
     * @return the player's current money.
     */
    public int getPlayerMoney() {
        return playerMoney;
    }

    /**
     * Sets the players money when an item or is bought
     * or sold.
     *
     * @param playerMoney the player's new amount of money
     */
    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    /**
     * Gets the player's currently owned items.
     *
     * @return the player's items
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Gets the towers the player has purchased from a particular shop.
     *
     * @return purchased towers from a particular shop.
     */
    public List<Tower> getPurchasedTowers() {
        return purchasedTowers;
    }

    /**
     * Gets the items the player has purchased from a particular shop.
     *
     * @return purchased items from a particular shop.
     */
    public List<Item> getPurchasedItems() {
        return purchasedItems;
    }

    /**
     * Gets the towers the player has chosen to use for the upcoming round.
     *
     * @return list of selected towers
     */
    public List<Tower> getEquippedTowers() {
        return equippedTowers;
    }

    /**
     * Sets the list of equipped towers to that which the player has chosen.
     *
     * @param equippedTowers the players chosen towers for the upcoming round.
     */
    public void setEquippedTowers(List<Tower> equippedTowers) {
        this.equippedTowers = equippedTowers;
    }

    /**
     * Gets the items the player has chosen to use for the upcoming round.
     *
     * @return list of selected items
     */
    public List<Item> getEquippedItems() {
        return equippedItems;
    }

    /**
     * Sets the list of equipped items to that which the player has chosen.
     *
     * @param equippedItems the players chosen items for the upcoming round.
     */
    public void setEquippedItems(List<Item> equippedItems) {
        this.equippedItems = equippedItems;
    }

    /**
     * Gets the player's current accumulated score.
     *
     * @return the player's current score
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * Gets the player's total accumulated money, used to display at the
     * end of the game.
     *
     * @return player's total accumulated money
     */
    public int getTotalPlayerMoney() {
        return totalPlayerMoney;
    }
}
