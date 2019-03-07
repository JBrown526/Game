package game;

import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public abstract class GameLevel extends World {

    private Player player;

    public void populate(Game game) {
        player = new Player(this);
        player.setPosition(startPosition());
        Bone bone = new Bone(this);
        bone.setPosition(bonePosition());
        bone.addCollisionListener(new BoneListener(game));
    }

    public abstract Vec2 startPosition();

    public abstract Vec2 bonePosition();

    public Player getPlayer() {
        return player;
    }

    public void addBark(Player.Direction dir) {
        Bark bark = new Bark(this, dir);
        // set position on player and offset to avoid collision
        bark.setPosition(new Vec2(
                player.getPosition().x + ((dir == Player.Direction.LEFT) ? -0.5f : 0.5f),
                player.getPosition().y)
        );
    }
}
