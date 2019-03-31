package game.display;

import java.awt.*;
import javax.swing.ImageIcon;

import city.cs.engine.*;

public class MyView extends UserView {

    private transient Image backgroundImage;
    private Color color;
    private Rectangle ground;

    public MyView(World world, int width, int height) {
        super(world, width, height);
        ground = new Rectangle(0, 445, 1000, 50);
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(backgroundImage, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(color);
        g.fill(ground);
        g.draw(ground);
    }

    public void setBackgroundImage(String filePath) {
        backgroundImage = new ImageIcon(filePath).getImage();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
