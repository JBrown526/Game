package game.levels;

import city.cs.engine.BoxShape;
import game.entities.BreakablePlatform;
import game.Game;
import game.entities.Platform;
import org.jbox2d.common.Vec2;

public class LevelThree extends GameLevel {

    // ---------------------- METHODS ----------------------
    @Override
    public void populate(Game game) {
        super.populate(game);

        Platform ground = new Platform(this, new BoxShape(90, 0.5f), 0, -12);
        ground.addCollisionListener(super.getCollisionHandler());

        for (int i = 0; i < 6; i++) {
            BreakablePlatform bp = new BreakablePlatform(this, 0.5f, 5, i * 8 + 5, -7, BreakablePlatform.Biome.PLAINS);
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
    public boolean newAudio() {
        return false;
    }

    @Override
    public String backingTrackFile() {
        return "data/audio/plains.wav";
    }
}
