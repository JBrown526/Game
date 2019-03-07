package game;

import city.cs.engine.*;
import city.cs.engine.Shape;

import java.awt.*;

public class BreakablePlatform extends Platform {

    // ---------------------- CONSTRUCTOR ----------------------
    public BreakablePlatform(World world, Shape shape, float xPos, float yPos){
        super(world, shape, xPos, yPos);
        // Colour
        setFillColor(Color.RED);
        setLineColor(Color.RED);
    }
}
