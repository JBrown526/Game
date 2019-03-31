package game;

import city.cs.engine.*;
import game.display.ControlPanel;
import game.display.MyView;
import game.display.PlayerScore;
import game.display.Tracker;
import game.entities.Player;
import game.entities.control.Controller;
import game.levels.*;
import game.levels.save.SaveReader;
import game.levels.save.SaveWriter;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game {

    // ---------------------- FIELDS ----------------------
    private static final int LEVEL_COUNT = 3;
    private static final boolean DEBUGGING = false;

    private GameLevel world;
    private MyView view;
    private Controller controller;
    private DebugViewer debug;
    private Tracker tracker;
    private int currentLevel;
    private SoundClip backingTrack;
    private String backingTrackFile;
    private JProgressBar healthProgressBar;

    private int lastLevelHealth;
    private int lastLevelScore;

    private GameLevel[] levels = new GameLevel[LEVEL_COUNT + 1];

    // ---------------------- CONSTRUCTOR ----------------------
    public Game() {
        backingTrackFile = "";
        lastLevelHealth = Player.MAX_HEALTH;
        lastLevelScore = 0;

        // Setup window and world
        levelsSetup();

        world = levels[currentLevel];
        debug();
        setBackingTrack();

        view = new MyView(world, 1000, 480);
        world.populate(this);
        final JFrame window = new JFrame("A Dog and his Bone");

        // Window settings
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.setResizable(false);

        // Controllers
        controller = new Controller(world);
        window.addKeyListener(controller);

        // Step Listeners
        tracker = new Tracker(view, getPlayer());
        world.addStepListener(controller);
        world.addStepListener(tracker);

        PlayerScore playerScore = new PlayerScore(this);
        view.add(playerScore);

        ControlPanel menu = new ControlPanel(this);
        window.add(menu.getMainPanel(), BorderLayout.NORTH);
        window.add(view, BorderLayout.CENTER);
        healthProgressBar = menu.getHealthProgressBar();

        // Start world
        window.pack();
        window.setVisible(true);
        world.start();
    }

    private void levelsSetup() {
        currentLevel = 1;

        levels[0] = new NullLevel();
        levels[1] = new LevelOne();
        levels[2] = new LevelTwo();
        levels[3] = new LevelThree();
    }

    // ---------------------- METHODS ----------------------
    // ---------------------- GETTERS ----------------------

    public Player getPlayer() {
        return world.getPlayer();
    }

    public MyView getView() {
        return view;
    }

    public GameLevel getWorld() {
        return world;
    }

    public JProgressBar getHealthProgressBar() {
        return healthProgressBar;
    }

    // ---------------------- AUDIO ----------------------

    private void setBackingTrack() {
        if (!backingTrackFile.equals(world.backingTrackFile())) {
            backingTrackFile = world.backingTrackFile();
            if (backingTrack != null) {
                backingTrack.stop();
            }
            try {
                backingTrack = new SoundClip(backingTrackFile);
                backingTrack.loop();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    // ---------------------- LEVELS ----------------------
    public void goNextLevel() {
        lastLevelHealth = world.getPlayer().getHealth();
        lastLevelScore = world.getPlayer().getScore();

        if (DEBUGGING) {
            lastLevelHealth = Player.MAX_HEALTH;
            lastLevelScore = 0;
        }

        currentLevel++;
        world.stop();
        world.removeStepListener(tracker);
        if (currentLevel > LEVEL_COUNT) {
            System.exit(0);
        } else {
            levelStart();
        }
    }

    public void resetLevel() {
        System.out.println("reset level");
        world.stop();
        world.removeStepListener(tracker);
        world = null;
        levelStart();
    }

    private void levelStart() {
        System.out.println("Level " + currentLevel);

        // setup world
        world = levels[currentLevel];
        world.populate(this);
        controller.setWorld(world);
        view.setWorld(world);
        setBackingTrack();

        // setup player
        world.getPlayer().setHealth(lastLevelHealth);
        world.getPlayer().setScore(lastLevelScore);
        tracker.setBody(world.getPlayer());

        if (DEBUGGING) {
            debug.setWorld(world);
        }

        world.addStepListener(tracker);
        world.start();
    }

    // ---------------------- SAVES ----------------------

    public void saveGame() {
        SaveWriter saveWriter = new SaveWriter(this);
        saveWriter.writeSave();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getLastLevelHealth() {
        return lastLevelHealth;
    }

    public int getLastLevelScore() {
        return lastLevelScore;
    }

    public void loadGame() {
        SaveReader saveReader = new SaveReader();
        int[] save = saveReader.readSave();
        if (save.length != 3) {
            currentLevel = save[0];
            lastLevelHealth = save[1];
            lastLevelScore = save[2];
            resetLevel();
            System.out.println("game loaded (level: " + save[0] + ", health: " + save[1] + ", score: " + save[2] + ")");
        }
    }

    // ---------------------- DEBUG ----------------------

    private void debug() {
        if (DEBUGGING) {
            currentLevel = 0;
            world = new LevelDebug();
            debug = new DebugViewer(world, 600, 600);
        }
    }

    // ---------------------- RUN ----------------------

    public static void main(String[] args) {
        new Game();
    }
}
