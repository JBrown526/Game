package game.entities;

import city.cs.engine.*;
import city.cs.engine.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * BreakablePlatform template for creating obstacles that can be removed with a bark. Contains a map for the images
 * to be mapped onto in relation to a particular biome.
 */
public class BreakablePlatform extends Platform {

    // ---------------------- FIELDS ----------------------
    private static final String FOREST_FILE = "data/objects/vines.png";
    private static final String PLAINS_FILE = "data/objects/trunk.png";

    // ---------------------- CONSTRUCTOR ----------------------

    /**
     * This constructor is for when there is an image associated with the biome that can be accessed.
     *
     * @param world      The current level.
     * @param halfWidth  Half the width of the platform.
     * @param halfHeight Half the height of the platform.
     * @param xPos       The x position of the platform's centre.
     * @param yPos       The y position of the platform's centre.
     * @param biome      The biome of the current level.
     */
    public BreakablePlatform(World world, float halfWidth, float halfHeight, float xPos, float yPos, String biome) {
        super(world, new BoxShape(halfWidth, halfHeight), xPos, yPos);

        // the map of possible biomes and their breakable platform objects
        Map<String, String> biomes = new HashMap<>();
        biomes.put("forest", FOREST_FILE);
        biomes.put("plains", PLAINS_FILE);

        addImage(new BodyImage(biomes.get(biome), 2 * halfHeight));
    }

    /**
     * This constructor is for when no image is available for the biome.
     *
     * @param world The current level.
     * @param shape The shape of the platform.
     * @param xPos  xPos The x position of the platform's centre.
     * @param yPos  The y position of the platform's centre.
     */
    public BreakablePlatform(World world, Shape shape, float xPos, float yPos) {
        super(world, shape, xPos, yPos);
        setFillColor(Color.RED);
        setLineColor(Color.RED);
    }
}
