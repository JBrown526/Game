package game.entities;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * The Player object is the main entity in the game, and the one used by the player in the game
 */
public class Player extends Walker {

    // ---------------------- FIELDS ----------------------
    public static final int MIN_HEALTH = 0;
    public static final int MAX_HEALTH = 100;

    // Shape
    private static final Shape shape = new PolygonShape(
            -1.649f, -1.152f, -1.655f, 0.61f, -1.367f, 1.16f, 1.26f, 1.146f, 1.642f, 0.677f, 1.642f, -1.119f);
    //-1.488f, -0.966f, -1.488f, 0.522f, -1.158f, 1.092f, -0.6f, 1.092f, 1.434f, 0.372f, 1.434f, -0.402f, 0.804f, -0.954f

    private int health;
    private int score;
    /**
     * The index of the image for the direction of travel, 0 is for left and 1 is for right.
     */
    private int directionIndex;
    /**
     * The index of the image for the action being performed, 0 is for barking, 1 for jumping, 2 for running and 3 for sitting.
     */
    private int actionIndex;
    private double volume;
    private boolean inAir;
    private boolean moving;
    private boolean audioPlaying;

    private List<ChangeListener> listeners;
    private JProgressBar healthProgressBar;

    // Image array [direction][action]
    private static final BodyImage[][] images = new BodyImage[2][4];

    // ---------------------- CONSTRUCTOR ----------------------

    /**
     * The Player constructor initialises some base values, as well as populating the array of possible images
     * and setting the first one.
     *
     * @param world             The current level.
     * @param healthProgressBar The health bar.
     */
    public Player(World world, JProgressBar healthProgressBar) {
        // Initialise values
        super(world, shape);
        this.healthProgressBar = healthProgressBar;
        health = 100;
        volume = 1.0d;
        audioPlaying = true;
        moving = false;
        inAir = false;

        populateImagesArray();

        initialImageAssignment();

        listeners = new LinkedList<>();
    }

    private static void populateImagesArray() {
        images[0][0] = new BodyImage("data/player/dogBarkL.gif", 2.4f);
        images[0][1] = new BodyImage("data/player/dogJumpL.gif", 2.4f);
        images[0][2] = new BodyImage("data/player/dogRunL.gif", 2.4f);
        images[0][3] = new BodyImage("data/player/dogSitL.gif", 2.4f);
        images[1][0] = new BodyImage("data/player/dogBarkR.gif", 2.4f);
        images[1][1] = new BodyImage("data/player/dogJumpR.gif", 2.4f);
        images[1][2] = new BodyImage("data/player/dogRunR.gif", 2.4f);
        images[1][3] = new BodyImage("data/player/dogSitR.gif", 2.4f);
    }

    private void initialImageAssignment() {
        actionIndex = 3;
        directionIndex = 1;
        addImage(images[directionIndex][actionIndex]);
        this.setClipped(true);
    }

    // ---------------------- METHODS ----------------------
    // ---------------------- HEALTH ----------------------

    public int getHealth() {
        return health;
    }

    public void setHealth(int health, JProgressBar healthProgressBar) {
        this.health = health;
        this.healthProgressBar = healthProgressBar;
        healthProgressBar.setValue(health);
        healthProgressBar.setString("Health:" + this.health + "/" + MAX_HEALTH);
    }

    /**
     * Sets the player health to a new value whilst ensuring it does not go above the player's MAX_HEALTH.
     *
     * @param deltaHealth The difference in health.
     */
    public void updateHealth(int deltaHealth) {
        int newHealth = health + deltaHealth;
        // Ensures health cannot exceed MAX_HEALTH
        health = (newHealth <= MAX_HEALTH) ? newHealth : MAX_HEALTH;

        System.out.println("Health: " + health);
        if (health <= MIN_HEALTH) {
            destroy();
            System.out.println("Game over man, game over");
            // game over code
        }

        healthProgressBar.setValue(health);
        healthProgressBar.setString("Health: " + health + "/" + MAX_HEALTH);
    }

    // ---------------------- SCORE ----------------------

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        changed();
    }

    /**
     * Sets the players score to a new value based on the difference provided.
     *
     * @param deltaScore The difference in score.
     */
    public void updateScore(int deltaScore) {
        score += deltaScore;
        changed();
    }

    // ---------------------- JUMPING ----------------------

    public boolean getInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    // ---------------------- MOVING ----------------------

    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    // ---------------------- DIRECTION ----------------------

    public int getDirectionIndex() {
        return directionIndex;
    }

    public void setDirectionIndex(int directionIndex) {
        this.directionIndex = directionIndex;
    }

    // ---------------------- IMAGES ----------------------

    /**
     * Updates the image being displayed based on a new action.
     *
     * @param actionIndex The index of the action in the image array.
     */
    public void updateImage(int actionIndex) {
        this.actionIndex = actionIndex;
        removeAllImages();
        addImage(images[directionIndex][actionIndex]);
    }

    // ---------------------- SOUND ----------------------

    /**
     * Sets the volume of the sound effects. It takes unmapped readings from 0 to 100 and converts them from 0.01 to
     * 2.0 for the volume.
     *
     * @param unmappedVolume The unmapped value from the slider before it is transformed into a value for the volume changer.
     */
    public void setVolume(int unmappedVolume) {
        volume = (unmappedVolume - 0d) / (100d - 0d) * (2.0d - 0.01d) + 0.01d;
        System.out.printf("sound effect volume %.2f%%%n", (volume / 2.0d) * 100d);
    }

    /**
     * Toggles audio on and off.
     */
    public void toggleAudio() {
        audioPlaying = !audioPlaying;
    }

    /**
     * Plays a sound effect for it's full length if audio is toggled on.
     *
     * @param fileName The filepath for the audio sample.
     */
    public void playSound(String fileName) {
        if (audioPlaying) {
            try {
                SoundClip sound = new SoundClip(fileName);
                sound.setVolume(volume);
                sound.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    // ---------------------- CHANGE LISTENER ----------------------

    public void addChangeListener(ChangeListener l) {
        listeners.add(l);
    }

    public void removeChangeListener(ChangeListener l) {
        listeners.remove(l);
    }

    protected void changed() {
        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener l : listeners) {
            l.stateChanged(e);
        }
    }

}
