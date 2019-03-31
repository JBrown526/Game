package game.levels;

import city.cs.engine.BoxShape;
import game.entities.BreakablePlatform;
import game.Game;
import game.entities.Platform;
import game.entities.Spike;
import org.jbox2d.common.Vec2;

public class LevelThree extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        Platform platform = new Platform(this, new BoxShape(13, 0.5f), 10.5f, -4);
        platform.addCollisionListener(super.getCollisionHandler());
        platform.setFillColor(getColors().get("plains"));
        platform.setLineColor(getColors().get("forest"));

        Platform wall = new Platform(this, new BoxShape(0.5f, 5), -3, -6.5f);
        wall.addCollisionListener(super.getCollisionHandler());
        wall.setFillColor(getColors().get("plains"));
        wall.setLineColor(getColors().get("forest"));

        BreakablePlatform obstacle1 = new BreakablePlatform(this, 1, 3.5f, 6, -7.5f, "forest");
        obstacle1.addCollisionListener(super.getCollisionHandler());

        Spike spike = new Spike(this);
        spike.setPosition(new Vec2(8, -11));
        spike.addCollisionListener(super.getCollisionHandler());

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
        return new Vec2(13, -10);
    }

    @Override
    public String backingTrackFile() {
        return "data/audio/music/forest.wav";
    }
}
