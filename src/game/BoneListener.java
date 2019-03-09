package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BoneListener implements CollisionListener {

    private Game game;

    public BoneListener(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        Player player = game.getPlayer();
        if (e.getOtherBody() == player) {
            System.out.println("next level");
            game.goNextLevel();
        }
        if (e.getOtherBody() instanceof Bark) {
            e.getOtherBody().destroy();
        }
    }
}
