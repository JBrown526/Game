package game;

import city.cs.engine.BoxShape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LevelTwo extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        Platform ground = new Platform(this, new BoxShape(70, 0.5f), 0, -12);
        ground.addCollisionListener(super.getCollisionHandler());

        for (int i = 0; i < 6; i++) {
            Spike spike = new Spike(this);
            spike.setPosition(new Vec2(10*i + 5, -11.2f));
            spike.addCollisionListener(super.getCollisionHandler());
        }

        game.getView().setBackground("data/backgrounds/plains.jpg");
        game.getView().setColor(new Color(80, 194, 99));

        System.out.println("level populated");
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -9);
    }

    @Override
    public Vec2 bonePosition() {
        return new Vec2(50, -10);
    }
}
