package game;

import city.cs.engine.*;

import javax.swing.*;

public class Game {

    // ---------------------- FIELDS ----------------------
    private static final int LEVEL_COUNT = 1;

    private GameWorld world;
    private UserView view;
    private int level;

    // ---------------------- CONSTRUCTOR ----------------------
    public Game() {

        // Setup window and world
        level = 1;
        world = new GameWorld();
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
        KeyHandler controller = new KeyHandler(world);
        frame.addKeyListener(controller);

        // Step Listeners
        world.addStepListener(controller);
        world.addStepListener(new Tracker(view, world.getPlayer()));

        // Debug
//         JFrame debug = new DebugViewer(world, 600, 600);

        // Start world
        world.start();
    }

    public Player getPlayer(){
        return world.getPlayer();
    }

    public void goNextLevel() {
        world.stop();
        if (level > LEVEL_COUNT) {
            System.exit(0);
        } else {
            level++;

        }
    }

    // ---------------------- RUN ----------------------
    public static void main(String[] args) {
        new Game();
    }
}
