package game;

import city.cs.engine.*;

public class Player extends Walker {

    // ---------------------- FIELDS ----------------------
    private static final int MAX_HEALTH = 100;

    // Shape
    private static final Shape shape = new PolygonShape(
            -1.649f, -1.152f, -1.655f, 0.61f, -1.367f, 1.16f, 1.26f, 1.146f, 1.642f, 0.677f, 1.642f, -1.119f);
    //-1.488f, -0.966f, -1.488f, 0.522f, -1.158f, 1.092f, -0.6f, 1.092f, 1.434f, 0.372f, 1.434f, -0.402f, 0.804f, -0.954f

    private int health;             // Player health
    private boolean inAir;          // Whether character is jumping or on the ground
    private boolean moving;         // Whether character is moving or not
    private Direction direction;    // Direction of movement
    private Action action;          // Current player action

    // Potential directions

    /**
     * The direction the player is facing in
     */
    enum Direction {
        LEFT, RIGHT
    }

    // Potential actions

    /**
     * The action being performed by the player
     */
    enum Action {
        BARK, JUMP, RUN, SIT
    }

    // Image array [direction][action]
    private static final BodyImage[][] images = new BodyImage[2][4];

    // ---------------------- CONSTRUCTOR ----------------------

    /**
     * This method constructs the player object, setting the initial
     * player stats and creating the array of images for display based
     * off of the players current {@link Action} and {@link Direction}
     *
     * @param world the {@link GameLevel} the player is currently in
     */
    public Player(World world) {
        // Initialise values
        super(world, shape);
        moving = false;
        inAir = false;
        health = 100;

        populateImagesArray();

        initialImageAssignment();
    }

    private void populateImagesArray() {
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
        action = Action.SIT;
        direction = Direction.RIGHT;
        addImage(images[getDirectionCode()][getActionCode()]);
        this.setClipped(true);
    }

    // ---------------------- METHODS ----------------------
    // ---------------------- HEALTH ----------------------

    /**
     * This method updates the health of the character, and ensures
     * it cannot exceed the maximum health of the character. If the
     * health reaches zero, the player is destroyed
     *
     * @param deltaHealth the change in health to be applied
     */
    public void updateHealth(int deltaHealth) {
        final int newHealth = health + deltaHealth;
        // Ensures health cannot exceed MAX_HEALTH
        health = (newHealth <= MAX_HEALTH) ? newHealth : MAX_HEALTH;

        System.out.println("Health: " + health);
        if (health <= 0) {
            destroy();
            System.out.println("Game over man, game over");
            // game over code
        }
    }

    // ---------------------- JUMPING ----------------------

    /**
     * This method returns whether the player is on a platform or not
     *
     * @return true if not on platform, false if on platform
     */
    public boolean getInAir() {
        return inAir;
    }

    /**
     * This method sets whether or not the player is on a platform or not
     *
     * @param inAir true if not on a platform, false if on a platform
     */
    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    // ---------------------- MOVING ----------------------

    /**
     * This method returns whether or not the player is moving
     *
     * @return true if the character is moving, false if otherwise
     */
    public boolean getMoving() {
        return moving;
    }

    /**
     * This method sets whether or not the player is moving
     *
     * @param moving true if the player is moving, otherwise false
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    // ---------------------- DIRECTION ----------------------

    /**
     * This method returns the current {@link Direction} of movement
     *
     * @return the direction the character is moving in
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * This method sets the players {@link Direction} of movement
     *
     * @param direction the new direction of movement
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // Convert direction to index for image array

    /**
     * This method gets the numerical index of the {@link Direction} the player
     * is moving in for the purposes of accessing the image array
     *
     * @return the index for the images associated with the direction
     */
    private int getDirectionCode() {
        switch (direction) {
            case LEFT:
                return 0;
            case RIGHT:
                return 1;
            default:
                return -1;
        }
    }

    // ---------------------- ACTIONS ----------------------
    // Convert action to index for image array

    /**
     * This method gets the numerical index of the {@link Action} currently being
     * performed for the purposes of accessing the image array
     *
     * @return the index for the images associated with the action
     */
    private int getActionCode() {
        switch (action) {
            case BARK:
                return 0;
            case JUMP:
                return 1;
            case RUN:
                return 2;
            case SIT:
                return 3;
            default:
                return -1;
        }
    }

    // ---------------------- IMAGES ----------------------

    /**
     * This method will update the image and action of the player character
     *
     * @param action the new {@link Action} the player will be performing
     */
    public void updateImage(Action action) {
        this.action = action;
        removeAllImages();
        addImage(images[getDirectionCode()][getActionCode()]);
    }
}
