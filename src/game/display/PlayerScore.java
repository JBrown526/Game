package game.display;

import game.Game;
import game.entities.Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PlayerScore extends JLabel implements ChangeListener {

    private Player player;

    public PlayerScore(Game game) {
        this.player = game.getPlayer();
        player.addChangeListener(this);

        setText("Score: " + player.getScore());
        setForeground(Color.black);
        setBackground(Color.gray);
        setFocusable(false);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        setText("Score: " + player.getScore());
    }
}
