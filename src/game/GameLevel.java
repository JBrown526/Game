package game;

import city.cs.engine.SoundClip;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

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
    }

    public abstract Vec2 startPosition();

    public abstract Vec2 bonePosition();

    public abstract boolean newAudio();

    public abstract String backingTrackFile();

    public Player getPlayer() {
        return player;
    }

    public CollisionHandler getCollisionHandler() {
        return collisionHandler;
    }

    public void addBark(Player.Direction dir) {
        Bark bark = new Bark(this, dir);
        // set position on player and offset to avoid collision
        bark.setPosition(new Vec2(
                player.getPosition().x + ((dir == Player.Direction.LEFT) ? -0.5f : 0.5f),
                player.getPosition().y)
        );
        try {
            SoundClip barkSound = new SoundClip("data/audio/bark.wav");
            barkSound.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
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
