package game;

import city.cs.engine.*;

public class Bone extends StaticBody {

    // ---------------------- FIELDS ----------------------
    private static final Shape shape = new PolygonShape(
            -0.868f, 0.384f, -1.024f, 0.288f, -1.016f, -0.312f, -0.868f, -0.38f, 0.736f, -0.376f, 0.872f, -0.264f, 0.876f, 0.232f, 0.724f, 0.38f);

    private static final BodyImage image = new BodyImage("data/objects/Bone.png", 1.0f);

    // ---------------------- CONSTRUCTOR ----------------------
    public Bone(World world) {
        super(world, shape);
        addImage(image);
    }
}
