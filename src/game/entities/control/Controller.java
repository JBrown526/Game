package game.entities.control;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.levels.GameLevel;
import game.entities.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter implements StepListener {

    // ---------------------- FIELDS ----------------------

    // Movement variables
    private static final float WALKING_SPEED = 4;
    private static final float JUMP_SPEED = 11;
    private static final int BARK_TIME = 40;

    private GameLevel world;
    private transient Player player;
    private int stepSum;
    private boolean barking;

    // ---------------------- CONSTRUCTOR ----------------------
    public Controller(GameLevel world) {
        this.world = world;
        player = world.getPlayer();
        barking = false;
    }

    // ---------------------- METHODS ----------------------
    // ---------------------- GENERAL ----------------------
    public void setWorld(GameLevel world) {
        this.world = world;
        player = world.getPlayer();
    }

    // ---------------------- MOVEMENT ----------------------
    @Override
    public void keyPressed(KeyEvent e) {
        final int code = e.getKeyCode();

        if (code == KeyEvent.VK_SPACE) {
            bark();
        } else if (code == KeyEvent.VK_W) {
            jump();
        } else if (code == KeyEvent.VK_A) {
            move(Player.Direction.LEFT);
        } else if (code == KeyEvent.VK_D) {
            move(Player.Direction.RIGHT);
        }
    }

    private void bark() {
        if (!barking) {
            player.updateImage(Player.Action.BARK);
            barking = true;
            stepSum = 0;
            world.addBark(player.getDirection());
        }
    }

    private void jump() {
        player.updateImage(Player.Action.JUMP);
        player.setInAir(true);
        player.jump(JUMP_SPEED);
    }

    private void move(Player.Direction dir) {
        player.setMoving(true);
        player.setDirection(dir);
        player.updateImage(player.getInAir() ? Player.Action.JUMP : Player.Action.RUN);
        player.startWalking((dir == Player.Direction.LEFT ? -1 : 1) * WALKING_SPEED);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_D) {
            stopMove();
        }
    }

    private void stopMove() {
        player.updateImage(player.getInAir() ? Player.Action.JUMP : Player.Action.SIT);
        player.setMoving(false);
        player.stopWalking();
    }

    // ---------------------- TIMING ----------------------
    @Override
    public void preStep(StepEvent e) {
        // barking timer
        if (barking) {
            if (stepSum < BARK_TIME) {
                stepSum++;
            }
            // after timing finished check player action and update image as required
            else {
                postBark();
            }
        }
    }

    @Override
    public void postStep(StepEvent e) {
        // Unneeded
    }

    private void postBark() {
        if (player.getInAir()) {
            player.updateImage(Player.Action.JUMP);
        } else if (player.getMoving()) {
            player.updateImage(Player.Action.RUN);
        } else {
            player.updateImage(Player.Action.SIT);
        }
        barking = false;
    }
}
