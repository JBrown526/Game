package game.levels;

import game.Game;
import game.entities.Spike;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LevelTwo extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        for (int i = 0; i < 6; i++) {
            Spike spike = new Spike(this);
            spike.setPosition(new Vec2(10 * i + 5, -11.2f));
            spike.addCollisionListener(super.getCollisionHandler());
        }

        game.getView().setBackgroundImage("data/backgrounds/plains.jpg");
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

    @Override
    public String backingTrackFile() {
        return "data/audio/music/plains.wav";
    }
}
