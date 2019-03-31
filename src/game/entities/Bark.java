package game.entities;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Bark extends Walker implements StepListener{

    // ---------------------- FIELDS ----------------------
    private static final float BARK_SPEED = 15;
    private static final int BARK_TRAVEL_TIME = 150;
    private static final Shape shape = new PolygonShape(
            -0.314f, 0.747f, -0.023f, 0.75f, 0.319f, 0.405f, 0.316f, -0.405f, -0.035f, -0.747f, -0.314f, -0.753f);

    private AttachedImage image;
    private int stepSum = 0;

    // ---------------------- CONSTRUCTOR ----------------------
    public Bark(World world, Player.Direction dir) {
        super(world, shape);
        image = new AttachedImage(this, new BodyImage("data/objects/soundWave.png", 1.5f), 1, 0, new Vec2(0, 0));
        setGravityScale(0);
        // Direction
        if (dir == Player.Direction.LEFT) {
            image.flipHorizontal();
            startWalking(-BARK_SPEED);
        } else {
            startWalking(BARK_SPEED);
        }

        world.addStepListener(this);
        System.out.println("bark");
    }

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

    }
}
