package game.entities.collisions;

import city.cs.engine.Body;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.entities.*;

/**
 * CollisionHandler is the object responsible for managing most of the collisions between objects.
 */
public class CollisionHandler implements CollisionListener {

    // ---------------------- FIELDS ----------------------
    private static final int SPIKE_DAMAGE = -20;
    private Player player;


    // ---------------------- CONSTRUCTOR ----------------------
    public CollisionHandler(Player player) {
        this.player = player;
    }

    // ---------------------- METHODS ----------------------

    /**
     * This method splits up the {@link Player} and {@link Bark} collisions and divides them based on what each type
     * of collision leads to happening.
     *
     * @param e The collision event being checked.
     */
    @Override
    public void collide(CollisionEvent e) {
        // Player collisions
        if (e.getOtherBody() == player) {
            playerCollision(e);
        }
        // Bark collisions
        if (e.getOtherBody() instanceof Bark) {
            barkCollision(e);
        }
    }

    // ---------------------- PLAYER EVENTS ----------------------
    private void playerCollision(CollisionEvent e) {
        final Body target = e.getReportingBody();

        if (target instanceof Platform) {
            playerPlatformCollision();
        }
        if (target instanceof Spike) {
            playerSpikeCollision();
        }
        if (target instanceof TennisBall) {
            playerTennisBallCollision(target);
        }
    }

    private void playerPlatformCollision() {
        // registers when the player is on the ground and updates their image based on their movement
        player.setInAir(false);
        player.updateImage(player.getMoving() ? 2 : 3);
    }

    private void playerSpikeCollision() {
        // deals damage to the player
        player.updateHealth(SPIKE_DAMAGE);
        player.playSound("data/audio/entity_noises/yelp.wav");
    }

    private void playerTennisBallCollision(Body target) {
        // updates the player's score and destroys the tennis ball
        player.updateScore(1);
        player.playSound("data/audio/entity_noises/squeak.wav");
        target.destroy();
        System.out.println("tennis ball collected");
    }

    // ---------------------- BARK EVENTS ----------------------
    private void barkCollision(CollisionEvent e) {
        final Body target = e.getReportingBody();
        final Body bark = e.getOtherBody();

        if (target instanceof BreakablePlatform) {
            // destroys the breakable platform and bark
            target.removeAllImages();
            bark.destroy();
            target.destroy();
            System.out.println("platform destroyed");
        } else if (target instanceof Platform || target instanceof Spike || target instanceof TennisBall) {
            // destroys the bark
            bark.destroy();
            System.out.println("bark destroyed");
        }
    }
}
