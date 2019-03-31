package game.levels;

import game.Game;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class NullLevel extends GameLevel {

    @Override
    public void populate(Game game) {
        super.populate(game);

        game.getView().setBackgroundImage("data/backgrounds/forest.png");
        game.getView().setColor(new Color(24, 48, 52));
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, 0);
    }

    @Override
    public Vec2 bonePosition() {
        return new Vec2(0, 0);
    }

    @Override
    public String backingTrackFile() {
        return "data/audio/music/forest.wav";
    }
}
