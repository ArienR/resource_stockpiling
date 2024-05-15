package seng201.team0;

import seng201.team0.gui.FXWindow;
import seng201.team0.models.Tower;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class GameManager {
    private int numberOfRounds;
    private Player player;
    private String gameDifficulty;
    private final Consumer<GameManager> setupScreenLauncher;
    private final Consumer<GameManager> upcomingRoundScreenLauncher;
    private final Consumer<GameManager> inventoryScreenLauncher;

    private final Runnable clearScreen;

    public GameManager(Player player, Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> upcomingRoundScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Runnable clearScreen) {
        this.player = player;
        this.setupScreenLauncher = setupScreenLauncher;
        this.upcomingRoundScreenLauncher = upcomingRoundScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;

        this.clearScreen = clearScreen;
//        defaultRockets.addAll(List.of(new Rocket("Space Shuttle"), new Rocket("Falcon 9"),
//                new Rocket("Falcon Heavy"), new Rocket("Ariane 5"), new Rocket("Saturn 5"),
//                new Rocket("Delta IV Heavy")));
        launchSetupScreen();
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int tempNumberOfRounds) {
        this.numberOfRounds = tempNumberOfRounds;
    }

    public String getGameDifficulty() {
        return gameDifficulty;
    }

    public float getGameBonus(){
        if (Objects.equals(gameDifficulty, "Easy")){
            return 0.75f;
        } else {
            return 0.5f;
        }
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
        inventoryScreenLauncher.accept(this);
    }

    public void closeInventoryScreen() {
        clearScreen.run();
    }

}
