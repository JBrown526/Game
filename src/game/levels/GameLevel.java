package game.levels;

import city.cs.engine.BoxShape;
import city.cs.engine.World;
import game.Game;
import game.entities.Bark;
import game.entities.Bone;
import game.entities.Platform;
import game.entities.Player;
import game.entities.collisions.BoneListener;
import game.entities.collisions.CollisionHandler;
import org.jbox2d.common.Vec2;

public abstract class GameLevel extends World {

    // ---------------------- FIELDS ----------------------

    private Player player;
    private CollisionHandler collisionHandler;
    private boolean playing;

    // ---------------------- METHODS ----------------------
    public void populate(Game game) {
        if (game.getPlayer() == null) {
            player = new Player(this, game.getHealthProgressBar());
        } else {
            player = game.getPlayer();
        }
        player.setPosition(startPosition());
        collisionHandler = new CollisionHandler(player);

        Bone bone = new Bone(this);
        bone.setPosition(bonePosition());
        bone.addCollisionListener(new BoneListener(game));

        makeGround();
    }

    public abstract Vec2 startPosition();

    public abstract Vec2 bonePosition();

    public abstract String backingTrackFile();

    private void makeGround() {
        Platform ground = new Platform(this, new BoxShape(200, 0.5f), 0, -12);
        ground.addCollisionListener(getCollisionHandler());
    }

    public Player getPlayer() {
        return player;
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public void addBark(Player.Direction dir) {
        Bark bark = new Bark(this, dir);
        player.playSound("data/audio/entity_noises/bark.wav");
        // set position on player and offset to avoid collision
        bark.setPosition(new Vec2(
                player.getPosition().x + ((dir == Player.Direction.LEFT) ? -0.5f : 0.5f),
                player.getPosition().y)
        );
    }

    public void pause() {
        if (playing) {
            this.stop();
            System.out.println("Game paused");
            playing = false;
        } else {
            this.start();
            System.out.println("Game resumed");
            playing = true;
        }
    }
}
