package game.entities;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * The Platform entity is one that the player can stand on.
 */
public class Platform extends StaticBody {

    // ---------------------- CONSTRUCTOR ----------------------
    public Platform(World world, Shape shape, float xPos, float yPos) {
        super(world, shape);
        super.setPosition(new Vec2(xPos, yPos));
    }
}
