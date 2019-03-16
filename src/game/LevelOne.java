package game;

import city.cs.engine.BoxShape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LevelOne extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        Platform ground = new Platform(this, new BoxShape(70, 0.5f), 0, -12);
        ground.addCollisionListener(super.getCollisionHandler());

        game.getView().setBackgroundImage("data/backgrounds/forest.png");
        game.getView().setColor(new Color(24, 48, 52));

        System.out.println("level populated");
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -9);
    }

    @Override
    public Vec2 bonePosition() {
        return new Vec2(20, -10);
    }
}
