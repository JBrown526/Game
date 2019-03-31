package game.levels;

import game.Game;
import org.jbox2d.common.Vec2;

public class LevelFour extends GameLevel {

    @Override
    public void populate(Game game) {
        super.populate(game);

        getGame().getView().setBackgroundImage("data/backgrounds/plains.jpg");
        getGame().getView().setColor(getColors().get("plains"));

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -9);
    }

    @Override
    public Vec2 bonePosition() {
        return new Vec2(15, -11);
    }

    @Override
    public String backingTrackFile() {
        return "data/audio/music/plains.wav";
    }
}
