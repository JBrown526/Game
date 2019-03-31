package game.entities;

import city.cs.engine.*;

public class Spike extends StaticBody {

    // ---------------------- FIELDS ----------------------
    private static final Shape shape = new PolygonShape(
            0.487f, 0.618f, 0.803f, -0.145f, -0.75f, -0.152f, -0.441f, 0.612f);

    private static final BodyImage image = new BodyImage("data/objects/spike.png", 2.0f);

    // ---------------------- CONSTRUCTOR ----------------------
    public Spike(World world) {
        super(world, shape);
        addImage(image);
    }
}
