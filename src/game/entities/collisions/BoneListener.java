package game.entities.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.entities.Bark;
import game.entities.Player;

/**
 * BoneListener is the object responsible for managing collisions with {@link game.entities.Bone} objects.
 */
public class BoneListener implements CollisionListener {

    // ---------------------- FIELDS ----------------------
    private Game game;

    // ---------------------- CONSTRUCTOR ----------------------
    public BoneListener(Game game) {
        this.game = game;
    }

    // ---------------------- METHODS ----------------------

    /**
     * This method manages what happens when a bone registers a collision, if the game's {@link Player} object
     * collides with it it will increase their score and they can advance to the next level, if a {@link Bark}
     * object collides it will destroy the bark.
     *
     * @param e The collision event being checked.
     */
    @Override
    public void collide(CollisionEvent e) {
        Player player = game.getPlayer();
        // bone collision with player
        if (e.getOtherBody() == player) {
            player.updateScore(5);
            game.goNextLevel();
        }
        // bone collision with bark
        if (e.getOtherBody() instanceof Bark) {
            e.getOtherBody().destroy();
            System.out.println("bark destroyed");
        }
    }
}
