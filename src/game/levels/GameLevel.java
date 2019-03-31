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

/**
 * GameLevel is the template for the level classes.
 */
public abstract class GameLevel extends World {

    // ---------------------- FIELDS ----------------------

    private Player player;
    private CollisionHandler collisionHandler;
    private boolean playing;

    // ---------------------- METHODS ----------------------

    /**
     * Creates a new player if none has been created before, and initialises their position as well as that of the
     * ground and the bone. It also adds collision handlers.
     *
     * @param game The main {@link Game} object.
     */
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

    /**
     * The start position implementation of the player character.
     *
     * @return A 2D vector with position information.
     */
    public abstract Vec2 startPosition();

    /**
     * The start position implementation of the bone.
     *
     * @return A 2D vector with position information.
     */
    public abstract Vec2 bonePosition();

    /**
     * The background music file path.
     *
     * @return The file path string.
     */
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

    /**
     * Adds a bark to the world.
     *
     * @param dirIndex The direction the bark will move in, based off of the Player's direction.
     */
    public void addBark(int dirIndex) {
        Bark bark = new Bark(this, dirIndex);
        player.playSound("data/audio/entity_noises/bark.wav");

        // set position on player and offset to avoid collision
        bark.setPosition(new Vec2(
                player.getPosition().x + ((dirIndex == 0) ? -0.5f : 0.5f),
                player.getPosition().y)
        );
    }

    /**
     * Pauses the world temporarily.
     */
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
