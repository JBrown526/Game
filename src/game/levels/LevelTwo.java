package game.levels;

import city.cs.engine.BoxShape;
import game.Game;
import game.entities.Platform;
import game.entities.Spike;
import game.entities.TennisBall;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LevelTwo extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        Platform platform = new Platform(this, new BoxShape(10, 0.5f), 12, -6.5f);
        platform.addCollisionListener(super.getCollisionHandler());
        platform.setFillColor(getColors().get("plains"));
        platform.setLineColor(getColors().get("forest"));

        for (int i = 0; i < 14; i++) {
            Spike spike = new Spike(this);
            spike.setPosition(new Vec2(2.5f + 1.5f * i, -11));
            spike.addCollisionListener(super.getCollisionHandler());
        }

        TennisBall ball = new TennisBall(this);
        ball.setPosition(new Vec2(3, -8));
        ball.addCollisionListener(super.getCollisionHandler());

        TennisBall ball1 = new TennisBall(this);
        ball1.setPosition(new Vec2(12, 0));
        ball1.addCollisionListener(super.getCollisionHandler());


        game.getView().setBackgroundImage("data/backgrounds/forest.png");
        game.getView().setColor(getColors().get("forest"));

        System.out.println("level populated");
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-2, -9);
    }

    @Override
    public Vec2 bonePosition() {
        return new Vec2(50, -10);
    }

    @Override
    public String backingTrackFile() {
        return "data/audio/music/forest.wav";
    }
}
