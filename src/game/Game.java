package game;

import city.cs.engine.*;

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
    private JProgressBar healthProgressBar;
    private JLabel scoreLabel;

    private int lastLevelHealth;

    private GameLevel[] levels = new GameLevel[LEVEL_COUNT + 1];

    // ---------------------- CONSTRUCTOR ----------------------
    public Game() {

        // Setup window and world
        levelsSetup();

        world = levels[currentLevel];
        debug();
        setBackingTrack();
        System.out.println("Level" + currentLevel);


        view = new MyView(world, 1000, 480);
        world.populate(this);
        view.setPlayer(getPlayer());
        final JFrame window = new JFrame("A Dog and his Bone");

        // Window settings
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.setResizable(false);

        // Controllers
        view.addMouseListener(new MouseHandler(view));
        controller = new Controller(world);
        window.addKeyListener(controller);

        // Step Listeners
        tracker = new Tracker(view, getPlayer());
        world.addStepListener(controller);
        world.addStepListener(tracker);

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

        levels[0] = new LevelDebug();
        levels[1] = new LevelOne();
        levels[2] = new LevelTwo();
        levels[3] = new LevelThree();
    }

    // ---------------------- METHODS ----------------------

    public Player getPlayer() {
        return world.getPlayer();
    }

    public MyView getView() {
        return view;
    }

    public GameLevel getWorld() {
        return world;
    }

    private void setBackingTrack() {
        if (world.newAudio()) {
            if (backingTrack != null) {
                backingTrack.stop();
            }
            try {
                backingTrack = new SoundClip(world.backingTrackFile());
                backingTrack.loop();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }


    public void goNextLevel() {
        lastLevelHealth = world.getPlayer().getHealth();
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
        world.stop();
        world.removeStepListener(tracker);
        world = null;
        levelStart();
    }

    private void levelStart() {
        System.out.println("Level " + currentLevel);

        world = levels[currentLevel];
        world.populate(this);
        controller.setWorld(world);
        view.setWorld(world);
        setBackingTrack();

        Player player = world.getPlayer();
        player.setHealth(lastLevelHealth);
        view.setPlayer(player);
        tracker.setBody(player);

        if (DEBUGGING) {
            debug.setWorld(world);
        }

        world.addStepListener(tracker);
        world.start();
    }

    private void debug() {
        if (DEBUGGING) {
            currentLevel = 0;
            world = levels[currentLevel];
            debug = new DebugViewer(world, 600, 600);
        }
    }

    public JProgressBar getHealthProgressBar() {
        return healthProgressBar;
    }

    // ---------------------- RUN ----------------------
    public static void main(String[] args) {
        new Game();
    }
}
