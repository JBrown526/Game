package game.display;

import java.awt.*;
import javax.swing.ImageIcon;

import city.cs.engine.*;

/**
 * MyView is an entity used to display images in the background and foreground.
 */
public class MyView extends UserView {

    private transient Image backgroundImage;
    private Color color;
    private Rectangle ground;

    public MyView(World world, int width, int height) {
        super(world, width, height);
        ground = new Rectangle(0, 445, 1000, 50);
    }

    /**
     * This method paints in the background image for the level.
     *
     * @param g The graphical object for rendering the image in the view.
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        // paints the background image
        g.drawImage(backgroundImage, 0, 0, this);
    }

    /**
     * This method paints a rectangle over the ground platform and colours it in appropriately based off the level theme.
     *
     * @param g The graphical object for rendering the rectangle in the view.
     */
    @Override
    protected void paintForeground(Graphics2D g) {
        // paints the bottom of the screen to hide the ground platform
        g.setColor(color);
        g.fill(ground);
        g.draw(ground);
    }

    /**
     * This method sets the background image.
     *
     * @param filePath The path of the image file.
     */
    public void setBackgroundImage(String filePath) {
        // updates the background image
        backgroundImage = new ImageIcon(filePath).getImage();
    }

    /**
     * This method sets the colour of the ground level rectangle.
     *
     * @param color The colour for the rectangle.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
