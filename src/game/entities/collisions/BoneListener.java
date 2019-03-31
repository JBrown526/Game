package game.entities.collisions;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;
import game.entities.Bark;
import game.entities.Player;

public class BoneListener implements CollisionListener {

    // ---------------------- FIELDS ----------------------
    private Game game;

    // ---------------------- CONSTRUCTOR ----------------------
    public BoneListener(Game game) {
        this.game = game;
    }

    // ---------------------- METHODS ----------------------
    @Override
    public void collide(CollisionEvent e) {
        Player player = game.getPlayer();
        if (e.getOtherBody() == player) {
            player.updateScore(5);
            game.goNextLevel();
        }
        if (e.getOtherBody() instanceof Bark) {
            e.getOtherBody().destroy();
            System.out.println("bark destroyed");
        }
    }
}
