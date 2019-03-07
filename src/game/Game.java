package game;

import city.cs.engine.*;

import javax.swing.*;

public class Game {

    // ---------------------- FIELDS ----------------------
    private static final int LEVEL_COUNT = 3;

    private GameLevel world;
    private UserView view;
    private int currentLevel;

    private GameLevel[] levels = new GameLevel[LEVEL_COUNT + 1];

    // ---------------------- CONSTRUCTOR ----------------------
    public Game() {

        // Setup window and world
        levelsSetup();

        world = levels[currentLevel];
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
        Controller controller = new Controller(world);
        frame.addKeyListener(controller);

        // Step Listeners
        world.addStepListener(controller);
        world.addStepListener(new Tracker(view, world.getPlayer()));

        debug(false);

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
        world.stop();
        if (currentLevel > LEVEL_COUNT) {
            System.exit(0);
        } else {
            currentLevel++;
            world = levels[currentLevel];
            world.populate(this);



        }
    }

    private void debug(boolean debugging) {
        if (debugging) {
            JFrame debug = new DebugViewer(world, 600, 600);
            world = levels[0];
        }
    }

    // ---------------------- RUN ----------------------
    public static void main(String[] args) {
        new Game();
    }
}
