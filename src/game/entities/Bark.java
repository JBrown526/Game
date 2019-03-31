package game.entities;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * The object representing the players bark "attack". It implements StepListener in order to destroy itself after a
 * set amount of time in order to free up memory.
 */
public class Bark extends Walker implements StepListener {

    // ---------------------- FIELDS ----------------------
    private static final float BARK_SPEED = 15;
    private static final int BARK_TRAVEL_TIME = 150;
    private static final Shape shape = new PolygonShape(
            -0.314f, 0.747f, -0.023f, 0.75f, 0.319f, 0.405f, 0.316f, -0.405f, -0.035f, -0.747f, -0.314f, -0.753f);

    private AttachedImage image;
    /**
     * The number of steps since timing started.
     */
    private int stepSum;

    // ---------------------- CONSTRUCTOR ----------------------

    /**
     * Creates the bark object and sets it's image, it's direction of movement, and adds it as a step listener to the world.
     *
     * @param world    The current level.
     * @param dirIndex The direction of travel based on the index for the image (0 for Left, 1 for Right).
     */
    public Bark(World world, int dirIndex) {
        super(world, shape);
        stepSum = 0;

        // set attributes
        image = new AttachedImage(this, new BodyImage("data/objects/soundWave.png", 1.5f), 1, 0, new Vec2(0, 0));
        setGravityScale(0);

        // direction of travel
        if (dirIndex == 0) {
            image.flipHorizontal();
            startWalking(-BARK_SPEED);
        } else {
            startWalking(BARK_SPEED);
        }

        world.addStepListener(this);
        System.out.println("bark");
    }

    /**
     * This method governs the amount of time a bark will propagate through the level before it destroys itself.
     *
     * @param stepEvent The step event counting till it's time to destroy the bark.
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        if (stepSum < BARK_TRAVEL_TIME) {
            stepSum++;
        } else {
            System.out.println("bark destroyed");
            destroy();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
        // unneeded method
    }
}
