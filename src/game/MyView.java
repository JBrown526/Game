package game;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import city.cs.engine.*;

/**
 * extended view
 */
public class MyView extends UserView {

    private Player player;
    private Image background;

    public MyView(World world, Player player, int width, int height) {
        super(world, width, height);
        this.player = player;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.drawString("Health: " + player.getHealth(), 50, 50);
    }

    public void setBackground(String filePath) {
        background = new ImageIcon(filePath).getImage();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
