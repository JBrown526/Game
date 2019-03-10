package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Tracker implements StepListener {

    // ---------------------- FIELDS ----------------------
    private WorldView view;
    private WorldView dView;
    private Body body;
    private boolean debugging;

    // ---------------------- CONSTRUCTOR ----------------------
    public Tracker(WorldView view, Body body){
        this.view = view;
        this.body = body;
        debugging = false;
    }

    // ---------------------- METHODS ----------------------
    @Override
    public void preStep(StepEvent e){
    }

    @Override
    public void postStep(StepEvent e){
        // reposition camera
        view.setCentre(new Vec2(body.getPosition().x, -1));
        if (debugging) {
            dView.setCentre(new Vec2(body.getPosition().x, -1));
        }
    }

    public void setBody(Body body) {
        this.body = body;
    }

}
