package game;

import city.cs.engine.BoxShape;
import org.jbox2d.common.Vec2;

public class LevelDebug extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        Platform ground = new Platform(this, new BoxShape(11, 0.5f), 0, -12);
        ground.addCollisionListener(super.getCollisionHandler());

        BreakablePlatform wall = new BreakablePlatform(this, new BoxShape(0.5f, 0.5f), -5, -8);
        wall.addCollisionListener(super.getCollisionHandler());

        Spike spike = new Spike(this);
        spike.setPosition(new Vec2(-8, -11.3f));
        spike.addCollisionListener(super.getCollisionHandler());

        System.out.println("level populated");
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -9);
    }

    @Override
    public Vec2 bonePosition() {
        return new Vec2(8, -8);
    }
}
