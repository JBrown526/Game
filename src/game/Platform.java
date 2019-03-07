package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {

    // ---------------------- CONSTRUCTOR ----------------------
    public Platform(World world, Shape shape, float xPos, float yPos) {
        super(world, shape);
        super.setPosition(new Vec2(xPos, yPos));
    }
}
