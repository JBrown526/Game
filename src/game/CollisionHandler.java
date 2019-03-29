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
        player.setInAir(false);
        player.updateImage(player.getMoving() ? Player.Action.RUN : Player.Action.SIT);
        System.out.println("on ground");
    }

    private void playerSpikeCollision() {
        player.updateHealth(SPIKE_DAMAGE);
        System.out.println("spike hit");
    }

    private void playerTennisBallCollision(Body target) {
        player.updateScore(1);
        target.destroy();
        System.out.println("bone collected");
    }

    // ---------------------- BARK EVENTS ----------------------
    private void barkCollision(CollisionEvent e) {
        final Body target = e.getReportingBody();
        final Body bark = e.getOtherBody();

        if (target instanceof BreakablePlatform) {
            target.destroy();
            bark.destroy();
            System.out.println("It was super effective");
            System.out.println("The platform fainted");
        } else if (target instanceof Platform || target instanceof Spike || target instanceof TennisBall) {
            bark.destroy();
            System.out.println("It wasn't very effective");
        }

    }
}
