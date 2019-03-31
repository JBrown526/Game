package game.levels;

import city.cs.engine.BoxShape;
import game.Game;
import game.entities.Platform;
import game.entities.TennisBall;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LevelOne extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        Platform ground = new Platform(this, new BoxShape(70, 0.5f), 0, -12);
        ground.addCollisionListener(super.getCollisionHandler());

        TennisBall ball = new TennisBall(this);
        ball.setPosition(new Vec2(10, -10));
        ball.addCollisionListener(super.getCollisionHandler());

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

    @Override
    public boolean newAudio() {
        return true;
    }

    @Override
    public String backingTrackFile() {
        return "data/audio/forest.wav";
    }
}
