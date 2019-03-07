package game;

import city.cs.engine.Body;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CollisionHandler implements CollisionListener {

    // ---------------------- FIELDS ----------------------
    private static final int SPIKE_DAMAGE = -20;
    private Player player;


    // ---------------------- CONSTRUCTOR ----------------------
    public CollisionHandler(Player player) {
        this.player = player;
    }

    // ---------------------- METHODS ----------------------
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

    private void playerCollision(CollisionEvent e) {
        final Body target = e.getReportingBody();

        if (target instanceof Platform) {
            playerPlatformCollision();
        }
        if (target instanceof Spike) {
            playerSpikeCollision();
        }
    }

    private void barkCollision(CollisionEvent e) {
        final Body target = e.getReportingBody();
        final Body bark = e.getOtherBody();

        if (target instanceof Platform || target instanceof Spike) {
            bark.destroy();
        }
        if (target instanceof BreakablePlatform) {
            target.destroy();
        }
    }

    private void playerPlatformCollision() {
        player.setInAir(false);
        player.updateImage(player.getMoving() ? Player.Action.RUN : Player.Action.SIT);
        System.out.println("on ground");
    }

    private void playerSpikeCollision() {
        player.updateHealth(SPIKE_DAMAGE);
    }
}
