package seng201.team0;

import seng201.team0.gui.Tower;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GameManager {
    private String name;
    private int numberOfRounds;
    private String gameDifficulty;
    private List<Tower> rocketList;
    private final List<Tower> defaultRockets = new ArrayList<>();
    private final Consumer<GameManager> setupScreenLauncher;
    private final Runnable clearScreen;

    public GameManager(Consumer<GameManager> setupScreenLauncher, Runnable clearScreen) {
        this.setupScreenLauncher = setupScreenLauncher;
        this.clearScreen = clearScreen;
//        defaultRockets.addAll(List.of(new Rocket("Space Shuttle"), new Rocket("Falcon 9"),
//                new Rocket("Falcon Heavy"), new Rocket("Ariane 5"), new Rocket("Saturn 5"),
//                new Rocket("Delta IV Heavy")));
        launchSetupScreen();
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
}
