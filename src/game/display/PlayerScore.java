package game.display;

import game.Game;
import game.entities.Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * PlayerScore is an entity used to display the players score without having it constantly updating every frame.
 */
public class PlayerScore extends JLabel implements ChangeListener {

    private transient Player player;

    public PlayerScore(Game game) {
        this.player = game.getPlayer();
        player.addChangeListener(this);

        // set the attributes of the JLabel
        setText("Score: " + player.getScore());
        setForeground(Color.red);
        setBackground(Color.gray);
        setFocusable(false);
    }

    /**
     * This method updates the score counter whenever a change event is registered.
     *
     * @param e The change event being listened for.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        setText("Score: " + player.getScore());
    }
}
