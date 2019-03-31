package game.display;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Tracker is an entity used to follow the movements of a body in the x-axis.
 */
public class Tracker implements StepListener {

    // ---------------------- FIELDS ----------------------
    private WorldView view;
    private Body body;

    // ---------------------- CONSTRUCTOR ----------------------
    public Tracker(WorldView view, Body body) {
        this.view = view;
        this.body = body;
    }

    // ---------------------- METHODS ----------------------
    @Override
    public void preStep(StepEvent e) {
        // unneeded method
    }

    /**
     * This method updates the x position of the view.
     *
     * @param e The step event that causes the view to change every step of the simulation.
     */
    @Override
    public void postStep(StepEvent e) {
        // reposition camera
        view.setCentre(new Vec2(body.getPosition().x, -1));
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
