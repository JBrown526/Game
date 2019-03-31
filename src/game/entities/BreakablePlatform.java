package game.entities;

import city.cs.engine.*;
import city.cs.engine.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BreakablePlatform extends Platform {

    // ---------------------- FIELDS ----------------------
    private static final String FOREST_FILE = "data/objects/vines.png";
    private static final String PLAINS_FILE = "data/objects/trunk.png";

    private Map<String, String> biomes;

    // ---------------------- CONSTRUCTOR ----------------------
    public BreakablePlatform(World world, float halfWidth, float halfHeight, float xPos, float yPos, String biome) {
        super(world, new BoxShape(halfWidth, halfHeight), xPos, yPos);

        biomes = new HashMap<>();
        biomes.put("forest", FOREST_FILE);
        biomes.put("plains", PLAINS_FILE);

        addImage(new BodyImage(biomes.get(biome), 2 * halfHeight));
    }

    public BreakablePlatform(World world, Shape shape, float xPos, float yPos) {
        super(world, shape, xPos, yPos);
        setFillColor(Color.RED);
        setLineColor(Color.RED);
    }
}
