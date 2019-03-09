package game;

import city.cs.engine.*;

import javax.swing.*;

public class Game {

    // ---------------------- FIELDS ----------------------
    private static final int LEVEL_COUNT = 3;
    private static final boolean DEBUGGING = true;

    private GameLevel world;
    private UserView view;
    private Controller controller;
    private DebugViewer debug;
    private int currentLevel;

    private GameLevel[] levels = new GameLevel[LEVEL_COUNT + 1];

    // ---------------------- CONSTRUCTOR ----------------------
    public Game() {

        // Setup window and world
        levelsSetup();

        world = levels[currentLevel];
        debug(DEBUGGING);
        world.populate(this);
        view = new UserView(world, 600, 600);
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
        world.addStepListener(controller);
        world.addStepListener(new Tracker(view, world.getPlayer()));

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

    public Player getPlayer(){
        return world.getPlayer();
    }

    public void goNextLevel() {
        Player player = world.getPlayer();
        world.stop();
        if (currentLevel > LEVEL_COUNT) {
            System.exit(0);
        } else {
            currentLevel++;
            world = levels[currentLevel];
            world.populate(this);
            controller.setPlayer(player);
            view.setWorld(world);
            if (DEBUGGING) {
                debug.setWorld(world);
            }
            world.start();
        }
    }

    private void debug(boolean debugging) {
        if (debugging) {
            world = levels[0];
            debug = new DebugViewer(world, 600, 600);
        }
    }

    // ---------------------- RUN ----------------------
    public static void main(String[] args) {
        new Game();
    }
}
