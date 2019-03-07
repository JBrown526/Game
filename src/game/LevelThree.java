package game;

import org.jbox2d.common.Vec2;

public class LevelThree extends GameLevel {

    @Override
    public void populate(Game game) {
        super.populate(game);
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -9);
    }

    @Override
    public Vec2 bonePosition() {
        return new Vec2(8, -8);
    }
}
