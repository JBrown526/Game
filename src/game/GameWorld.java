package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {

    // ---------------------- FIELDS ----------------------
    private Player player;

    // ---------------------- CONSTRUCTOR ----------------------
    public GameWorld() {
        super();

        // Create player
        player = new Player(this);
        player.setPosition(new Vec2(0, -9));
        CollisionHandler collisionHandler = new CollisionHandler(player);

        // Create spike(s)
        Spike spike = new Spike(this);
        spike.setPosition(new Vec2(-8, -10.8f));
        spike.addCollisionListener(collisionHandler);

        // Create bone
        Bone bone = new Bone(this);
        bone.setPosition(new Vec2(8, -8));
        bone.addCollisionListener(collisionHandler);

        // Create platform(s)
        Platform ground = new Platform(this, new BoxShape(11, 0.5f), 0, -11.5f);
        ground.addCollisionListener(collisionHandler);

        // Create breakable platform(s)
        BreakablePlatform wall = new BreakablePlatform(this, new BoxShape(0.5f, 0.5f), -5, -8);
        wall.addCollisionListener(collisionHandler);
    }

    // ---------------------- METHODS ----------------------
    // Get player object
    public Player getPlayer() {
        return player;
    }

    // Add a bark
    public void addBark(Player.Direction dir) {
        Bark bark = new Bark(this, dir);
        // set position on player and offset to avoid collision
        bark.setPosition(new Vec2(
                player.getPosition().x + ((dir == Player.Direction.LEFT) ? -0.5f : 0.5f),
                player.getPosition().y)
        );
    }
}
