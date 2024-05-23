package seng201.team0;

import javafx.application.Platform;
import seng201.team0.gui.FXWindow;
import seng201.team0.models.Round;
import seng201.team0.services.Shop;
import java.util.function.Consumer;

/**
 * The GameManager class manages the general flow of the game and the game state, including the player's progress,
 * game rounds, and transitions between UI screens. It interacts with many components of the game such as the player
 * singleton, shop, and screens.
 */
public class GameManager {
    /**
     * The maximum number of rounds to be played.
     */
    private int numberOfRounds;

    /**
     * Indicates if the current round was won.
     */
    private boolean roundWon = true;

    /**
     * The current round number.
     */
    private int currentRoundNumber;

    /**
     * Indicates if the player has won the game.
     */
    private boolean gameWon = false;

    /**
     * Holds the singleton instance of the player.
     */
    private final Player player;

    /**
     * Holds the shop which is updated every round.
     */
    private Shop shop;

    /**
     * The game difficulty string.
     */
    private String gameDifficulty;

    /**
     * Holds the difficulty bonus.
     */
    private float difficultyBonus;

    /**
     * Holds the distance of the track in metres.
     */

    private int trackDistance;

    /**
     * Indicates if the round has ended, used to reset the shop.
     */

    private boolean isRoundEnded;

    /**
     * Holds the upcoming round which was selected by the user.
     */
    private Round upcomingRound;

    /**
     * Consumer for launching the setup screen.
     */
    private final Consumer<GameManager> setupScreenLauncher;

    /**
     * Consumer for launching the upcoming round screen.
     */
    private final Consumer<GameManager> upcomingRoundScreenLauncher;

    /**
     * Consumer for launching the inventory screen.
     */
    private final Consumer<GameManager> inventoryScreenLauncher;

    /**
     * Consumer for launching the sell screen.
     */
    private final Consumer<GameManager> sellShopScreenLauncher;

    /**
     * Consumer for launching the shop screen.
     */
    private final Consumer<GameManager> buyShopScreenLauncher;

    /**
     * Consumer for launching the game screen.
     */
    private final Consumer<GameManager> gameScreenLauncher;

    /**
     * Consumer for launching the after round screen.
     */
    private final Consumer<GameManager> afterRoundScreenLauncher;

    /**
     * Consumer for launching the end screen.
     */
    private final Consumer<GameManager> endScreenLauncher;

    /**
     * Runnable for clearing the current screen.
     */
    private final Runnable clearScreen;

    /**
     * Constructs a new GameManager singleton with the given player and screen launchers.
     *
     * @param player the player singleton
     * @param setupScreenLauncher consumer to launch FXML setup screen
     * @param upcomingRoundScreenLauncher consumer to launch FXML upcoming screen
     * @param inventoryScreenLauncher consumer to launch FXML inventory screen
     * @param sellShopScreenLauncher consumer to launch FXML sell screen
     * @param buyShopScreenLauncher consumer to launch FXML shop screen
     * @param gameScreenLauncher consumer to launch FXML game screen
     * @param afterRoundScreenLauncher consumer to launch FXML after round screen
     * @param endScreenLauncher consumer to launch FXML end screen
     * @param clearScreen runnable which clears the window
     */

    public GameManager(Player player, Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> upcomingRoundScreenLauncher,
                       Consumer<GameManager> inventoryScreenLauncher, Consumer<GameManager> sellShopScreenLauncher, Consumer<GameManager> buyShopScreenLauncher, Consumer<GameManager> gameScreenLauncher,
                       Consumer<GameManager> afterRoundScreenLauncher, Consumer<GameManager> endScreenLauncher,
                       Runnable clearScreen) {
        this.player = player;
        this.gameScreenLauncher = gameScreenLauncher;
        this.endScreenLauncher = endScreenLauncher;
        this.currentRoundNumber = 1;
        this.setupScreenLauncher = setupScreenLauncher;
        this.upcomingRoundScreenLauncher = upcomingRoundScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;
        this.sellShopScreenLauncher = sellShopScreenLauncher;
        this.buyShopScreenLauncher = buyShopScreenLauncher;
        this.afterRoundScreenLauncher = afterRoundScreenLauncher;

        this.clearScreen = clearScreen;
        this.shop = new Shop(this);
        launchSetupScreen();
    }

    /**
     * Gets the total number of rounds the user has selected to play
     *
     * @return the number of rounds
     */
    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    /**
     * Sets the total number of rounds
     *
     * @param tempNumberOfRounds the total number of rounds
     */
    public void setNumberOfRounds(int tempNumberOfRounds) {
        this.numberOfRounds = tempNumberOfRounds;
    }

    /**
     * Gets the current round number
     *
     * @return the current round number
     */
    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    /**
     * Increments the current round number. Method is typically called at the end
     * of a round to reflect the game progression
     */
    public void incrementCurrentRoundNumber() {
        this.currentRoundNumber +=1;
    }

    /**
     * Gets the game difficulty which was set by the player at the setup screen.
     *
     * @return the game difficulty string
     */
    public String getGameDifficulty() {
        return gameDifficulty;
    }

    /**
     * Sets the difficulty bonus which is used to calculate how much a tower sells
     * for based on the difficulty they have chosen.
     *
     * @param difficulty string indicating difficulty
     */
    public void setDifficultyBonus(String difficulty){
        if (difficulty.equals("Easy")){
            difficultyBonus = 0.5f;
        } else {
            difficultyBonus = 0.75f;
        }
    }

    /**
     * Gets the difficulty bonus. Typically used within the sell screen to set the
     * sell price of a Purchasable.
     *
     * @return the difficulty bonus
     */
    public float getDifficultyBonus() {
        return difficultyBonus;
    }

    /**
     * Sets the difficulty string to "Easy" or "Hard" based on what the user has
     * selected.
     *
     * @param tempGameDifficulty string based on setup screen selection
     */
    public void setGameDifficulty(String tempGameDifficulty) {
        this.gameDifficulty = tempGameDifficulty;
    }

    /**
     * Gets the player singleton which keeps track of the users towers and
     * items, money, and score.
     *
     * @return the player singleton
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Launches the setup screen at the start of the game. This is only called once.
     */
    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    /**
     * Closes the setup screen once the player has selected their name, difficulty,
     * and number of rounds.
     */
    public void closeSetupScreen() {
        clearScreen.run();
        FXWindow.getInstance().setScreenSize(1440, 900);
        launchUpcomingRoundScreen();
    }

    /**
     * Launches the upcoming round screen. This will launch after the setup screen and
     * after every round thereafter, (such that you have another round incoming) from which
     * the player will select the conditions for the upcoming round
     */
    public void launchUpcomingRoundScreen() {
        upcomingRoundScreenLauncher.accept(this);
    }

    /**
     * Closes the upcoming round screen, after which the inventory screen will launch.
     */
    public void closeUpcomingRoundScreen() {
        clearScreen.run();
        resetShop();
        launchInventoryScreen();
    }

    /**
     * Launches the inventory screen, from which the player will select their towers
     * for the upcoming round and be able to transition to buying and selling screens.
     */
    public void launchInventoryScreen() {
        if (isRoundEnded) {
            resetShop();
            isRoundEnded = false;
        }
        inventoryScreenLauncher.accept(this);
    }

    /**
     * Closes the inventory scree, after which the game screen will launch.
     */
    public void inventoryScreenToGameScreen() {
        clearScreen.run();
        launchGameScreen();
    }

    /**
     * Provides the transition between the inventory screen and the sell screen,
     * subsequently closing the inventory screen and launching the sell screen.
     */
    public void inventoryScreenToSellScreen() {
        clearScreen.run();
        launchSellShopScreen();
    }

    /**
     * Provides the transition between the inventory screen and the buy screen,
     * subsequently closing the inventory screen and launching the buy screen.
     */
    public void inventoryScreenToBuyScreen() {
        clearScreen.run();
        launchBuyShopScreen();
    }

    /**
     * Launches the sell screen, which will be activated by the user when they
     * choose to transition from the inventory screen to the sell screen.
     */
    public void launchSellShopScreen() {
        sellShopScreenLauncher.accept(this);
    }

    /**
     * Launches the buy screen, which will be activated by the user when they
     * choose to transition from the inventory screen to the buy screen.
     */
    public void launchBuyShopScreen() {
        buyShopScreenLauncher.accept(this);
    }

    /**
     * Closes the sell screen, after which the inventory screen will be launched
     * from the sell screen controller.
     */
    public void closeSellShopScreen() {
        clearScreen.run();
    }

    /**
     * Closes the buy screen, after which the inventory screen will be launched
     * from the sell screen controller.
     */
    public void closeBuyShopScreen() {
        clearScreen.run();
    }

    /**
     * Launches the game screen once the player has selected their towers and items
     * and confirmed they would like to start the round.
     */
    public void launchGameScreen(){
        gameScreenLauncher.accept(this);
    }

    /**
     * Provides a transition between the game screen and the after round screen. The
     * game round will end automatically triggering this.
     */
    public void gameScreenToAfterRoundScreen() {
        clearScreen.run();
        launchAfterRoundScreen();
    }

    /**
     * Launches the after round screen. Providing the stats of the players towers and
     * any random events that may have occurred.
     */
    public void launchAfterRoundScreen(){
        afterRoundScreenLauncher.accept(this);
    }

    /**
     * Closes the after round screen from which the upcoming rounds screen will be launched.
     */
    public void closeAfterRoundScreen() {
        clearScreen.run();
        launchUpcomingRoundScreen();
    }

    /**
     * Once the player has completed all their rounds or lost the end screen will launch which
     * will display their stats.
     */
    public void gameScreenToEndScreen() {
        clearScreen.run();
        launchEndScreen();
    }

    /**
     * Closes the ends screen which, quitting the application.
     */
    public void closeEndScreen() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Launches the end screen which will be used once the game has come to a
     * conclusion.
     */
    public void launchEndScreen() {
        endScreenLauncher.accept(this);
    }

    /**
     * Returns a boolean indicating if the current rounds was won or not. This
     * is used to determine if the end screen will launch.
     *
     * @return true if player won the round, false otherwise
     */
    public boolean isRoundWon() {
        return roundWon;
    }

    /**
     * Sets the round won attribute to true to trigger either the upcoming round
     * screen on the end game screen.
     *
     * @param roundWon boolean based on the outcome of the previous round
     */
    public void setRoundWon(boolean roundWon) {
        this.roundWon = roundWon;
    }

    /**
     * Resets the shop, method typically triggered every time a round has ended.
     */
    private void resetShop() {
        this.shop = new Shop(this);
    }

    /**
     * Gets the shop object which will be used in the shop screen to display the
     * towers and items available to buy.
     *
     * @return shop with generate items
     */
    public Shop getShop() {
        return shop;
    }

    /**
     * Checks the boolean depending on if the player has won the game or not.
     * This will be used ot determine the message the player receives on the
     * end game screen.
     *
     * @return true if player won the game, false otherwise
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Sets the game won variable after every round to assure consistency.
     *
     * @param gameWon boolean indicating if the player has won the game
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    /**
     * Gets the round object which was selected by the player on the upcoming
     * round screen. Used to generate the carts for the upcoming round.
     *
     * @return upcoming round
     */
    public Round getUpcomingRound() {
        return upcomingRound;
    }

    /**
     * Sets the upcoming round to that which the user has picked from the upcoming
     * round screen.
     *
     * @param upcomingRound round based on current round number
     */
    public void setUpcomingRound(Round upcomingRound) {
        this.upcomingRound = upcomingRound;
    }

    public int getTrackDistance() {
        return trackDistance;
    }

    public void setTrackDistance(int trackDistance) {
        this.trackDistance = trackDistance;
    }
}
