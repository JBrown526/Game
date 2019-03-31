package game.entities;

import city.cs.engine.*;

public class TennisBall extends StaticBody {

    private static final Shape shape = new CircleShape(0.35f);

    private static final BodyImage image = new BodyImage("data/objects/tennisBall.png", 0.7f);

    public TennisBall(World world) {
        super(world, shape);
        addImage(image);
    }
}
