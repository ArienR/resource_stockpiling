package seng201.team0;

import seng201.team0.gui.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameManager {
    private String name;
    private int numberOfRounds;
    private String gameDifficulty;
    private List<Tower> towerList;
//    private final List<Tower> defaultRockets = new ArrayList<>();
    private final Consumer<GameManager> setupScreenLauncher;
    private final Consumer<GameManager> upcomingRoundScreenLauncher;
    private final Consumer<GameManager> inventoryScreenLauncher;

    private final Runnable clearScreen;

    public GameManager(Consumer<GameManager> setupScreenLauncher, Consumer<GameManager> upcomingRoundScreenLauncher, Consumer<GameManager> inventoryScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.upcomingRoundScreenLauncher = upcomingRoundScreenLauncher;
        this.inventoryScreenLauncher = inventoryScreenLauncher;

        this.clearScreen = clearScreen;
//        defaultRockets.addAll(List.of(new Rocket("Space Shuttle"), new Rocket("Falcon 9"),
//                new Rocket("Falcon Heavy"), new Rocket("Ariane 5"), new Rocket("Saturn 5"),
//                new Rocket("Delta IV Heavy")));
        launchSetupScreen();
    }

    public String getName() {
        return name;
    }

    public void setName(String tempName) {
        this.name = tempName;
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

    public void setGameDifficulty(String tempGameDifficulty) {
        this.gameDifficulty = tempGameDifficulty;
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }

    public void closeSetupScreen() {
        clearScreen.run();
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
