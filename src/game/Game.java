package game;

import city.cs.engine.*;

import javax.swing.*;

public class Game {

    // ---------------------- FIELDS ----------------------
    private static final int LEVEL_COUNT = 3;
    private static final boolean DEBUGGING = true;

    private GameLevel world;
    private MyView view;
    private Controller controller;
    private DebugViewer debug;
    private Tracker tracker;
    private int currentLevel;
    private boolean music;

    private GameLevel[] levels = new GameLevel[LEVEL_COUNT + 1];

    // ---------------------- CONSTRUCTOR ----------------------
    public Game() {

        // Setup window and world
        levelsSetup();

        world = levels[currentLevel];
        debug();
        System.out.println("Level" + currentLevel);
        world.populate(this);
        view = new MyView(world, world.getPlayer(), 1000, 480);
        final JFrame frame = new JFrame("A Dog and his Bone");

        // Window settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.add(view);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Controllers
        view.addMouseListener(new MouseHandler(view));
        controller = new Controller(world);
        frame.addKeyListener(controller);

        // Step Listeners
        tracker = new Tracker(view, world.getPlayer());
        world.addStepListener(controller);
        world.addStepListener(tracker);

        // Start world
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

    public boolean getMusic() {
        return music;
    }

    public void setMusic(boolean music) {
        this.music = music;
    }

    public void goNextLevel() {
        int health = world.getPlayer().getHealth();
        currentLevel++;
        world.stop();
        world.removeStepListener(tracker);
        if (currentLevel > LEVEL_COUNT) {
            System.exit(0);
        } else {
            System.out.println("Level " + currentLevel);

            world = levels[currentLevel];
            world.populate(this);
            controller.setWorld(world);
            view.setWorld(world);

            Player player = world.getPlayer();
            player.setHealth(health);
            view.setPlayer(player);
            tracker.setBody(player);

            if (DEBUGGING) {
                debug.setWorld(world);
            }

            world.addStepListener(tracker);
            world.start();
        }
    }

    private void debug() {
        if (DEBUGGING) {
            currentLevel = 0;
            world = levels[currentLevel];
            debug = new DebugViewer(world, 600, 600);
        }
    }

    // ---------------------- RUN ----------------------
    public static void main(String[] args) {
        new Game();
    }
}
