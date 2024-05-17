package seng201.team0;

import seng201.team0.gui.FXWindow;
import seng201.team0.models.Tower;
import seng201.team0.models.Round;
import seng201.team0.services.Shop;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class GameManager {
    private int numberOfRounds;
    private boolean roundWon = true;
    private int currentRoundNumber;
    private Player player;
    private Shop shop;
    private String gameDifficulty;
    private float difficultyBonus;
    private boolean isRoundEnded;
    private final Consumer<GameManager> setupScreenLauncher;
    private final Consumer<GameManager> upcomingRoundScreenLauncher;
    private final Consumer<GameManager> inventoryScreenLauncher;
    private final Consumer<GameManager> sellShopScreenLauncher;
    private final Consumer<GameManager> buyShopScreenLauncher;

    private final Consumer<GameManager> gameScreenLauncher;
    private final Runnable clearScreen;


    public GameManager(Player player, Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> upcomingRoundScreenLauncher,
                       Consumer<GameManager> inventoryScreenLauncher, Consumer<GameManager> sellShopScreenLauncher, Consumer<GameManager> buyShopScreenLauncher, Consumer<GameManager> gameScreenLauncher,
                       Runnable clearScreen) {
        this.player = player;
        this.gameScreenLauncher = gameScreenLauncher;
        this.currentRoundNumber = 1;
        this.setupScreenLauncher = setupScreenLauncher;
        this.upcomingRoundScreenLauncher = upcomingRoundScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.sellShopScreenLauncher = sellShopScreenLauncher;
        this.buyShopScreenLauncher = buyShopScreenLauncher;
        this.clearScreen = clearScreen;
        this.shop = new Shop();
        launchSetupScreen();
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int tempNumberOfRounds) {
        this.numberOfRounds = tempNumberOfRounds;
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public void setCurrentRoundNumber() {
        this.currentRoundNumber = currentRoundNumber;
    }

    public String getGameDifficulty() {
        return gameDifficulty;
    }

    public void setDifficultyBonus(){
        if (Objects.equals(gameDifficulty, "Easy")){
            difficultyBonus = 1f;
        } else {
            difficultyBonus = 1.25f;
        }
    }

    public float getDifficultyBonus() {
        return difficultyBonus;
    }

    public void setGameDifficulty(String tempGameDifficulty) {
        this.gameDifficulty = tempGameDifficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player tempPlayer) {
        this.player = player;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
        FXWindow.getInstance().setScreenSize(1440, 900);
        launchUpcomingRoundScreen();
    }

    public void launchUpcomingRoundScreen() {
        upcomingRoundScreenLauncher.accept(this);
    }

    public void closeUpcomingRoundScreen() {
        clearScreen.run();
        launchInventoryScreen();
    }

    public void launchInventoryScreen() {
        if (isRoundEnded) {
            resetShop();
            isRoundEnded = false;
        }
        inventoryScreenLauncher.accept(this);
    }

    public void inventoryScreenToGameScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    public void inventoryScreenToSellScreen() {
        clearScreen.run();
        launchSellShopScreen();
    }

    public void inventoryScreenToBuyScreen() {
        clearScreen.run();
        launchBuyShopScreen();
    }

    public void launchSellShopScreen() {
        sellShopScreenLauncher.accept(this);
    }

    public void launchBuyShopScreen() {
        buyShopScreenLauncher.accept(this);
    }

    public void closeSellShopScreen() {
        clearScreen.run();
    }

    public void closeBuyShopScreen() {
        clearScreen.run();
    }

    public void launchGameScreen(){
        gameScreenLauncher.accept(this);
    }

    //still need to implement
    public void closeGameScreen() {
        clearScreen.run();
    }

    public boolean isRoundWon() {
        return roundWon;
    }

    public void setRoundWon(boolean roundWon) {
        this.roundWon = roundWon;
    }

    private void resetShop() {
        this.shop = new Shop();
    }

    public Shop getShop() {
        return shop;
    }
}
