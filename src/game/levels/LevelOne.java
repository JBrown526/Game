package game.levels;

import game.Game;
import game.entities.TennisBall;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LevelOne extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        for (int i = 0; i < 3; i++) {
            TennisBall ball = new TennisBall(this);
            ball.setPosition(new Vec2(4+ 3 * i, -10));
            ball.addCollisionListener(super.getCollisionHandler());
        }
        TennisBall ball = new TennisBall(this);
        ball.setPosition(new Vec2(10, -5));
        ball.addCollisionListener(super.getCollisionHandler());

        getGame().getView().setBackgroundImage("data/backgrounds/forest.png");
        getGame().getView().setColor(getColors().get("forest"));

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
    public String backingTrackFile() {
        return "data/audio/music/forest.wav";
    }
}
