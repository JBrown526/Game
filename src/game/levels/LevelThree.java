package game.levels;

import game.entities.BreakablePlatform;
import game.Game;
import org.jbox2d.common.Vec2;

public class LevelThree extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        for (int i = 0; i < 6; i++) {
            BreakablePlatform bp = new BreakablePlatform(this, 0.5f, 5, i * 8 + 5, -9.5f, "forest");
            bp.addCollisionListener(super.getCollisionHandler());
        }

        System.out.println("level populated");
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -9);
    }

    @Override
    public Vec2 bonePosition() {
        return new Vec2(55, -8);
    }

    @Override
    public String backingTrackFile() {
        return "data/audio/music/plains.wav";
    }
}
