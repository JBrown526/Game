package game;

import city.cs.engine.*;
import city.cs.engine.Shape;

import java.awt.*;

public class BreakablePlatform extends Platform {

    // ---------------------- FIELDS ----------------------
    enum Biome {
        FOREST("data/objects/vines.png"),
        PLAINS("data/objects/trunk.png");

        private final String imageFile;

        Biome(String imageFile) {
            this.imageFile = imageFile;
        }

        public String getImageFile() {
            return imageFile;
        }
    }

    // ---------------------- CONSTRUCTOR ----------------------
    public BreakablePlatform(World world, float halfWidth, float halfHeight, float xPos, float yPos, Biome biome) {
        super(world, new BoxShape(halfWidth, halfHeight), xPos, yPos);
        addImage(new BodyImage(biome.getImageFile(), 2 * halfHeight));
    }

    public BreakablePlatform(World world, Shape shape, float xPos, float yPos) {
        super(world, shape, xPos, yPos);
        setFillColor(Color.RED);
        setLineColor(Color.RED);
    }
}
