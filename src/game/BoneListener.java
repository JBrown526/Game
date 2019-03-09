package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

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
            game.goNextLevel();
        }
        if (e.getOtherBody() instanceof Bark) {
            System.out.println("It wasn't very effective");
            e.getOtherBody().destroy();
        }
    }
}
