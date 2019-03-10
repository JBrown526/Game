package game;

import java.awt.*;
import javax.swing.ImageIcon;
import city.cs.engine.*;

/**
 * extended view
 */
public class MyView extends UserView {

    private Player player;
    private Image background;
    private Color color;
    private Rectangle ground;

    public MyView(World world, int width, int height) {
        super(world, width, height);
        ground = new Rectangle(0, 445, 1000, 50);
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(color);
        g.fill(ground);
        g.draw(ground);
    }

    public void setBackground(String filePath) {
        background = new ImageIcon(filePath).getImage();
        System.out.println("update background");
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
