package game.display;

import game.Game;
import game.entities.Player;

import javax.swing.*;

/**
 * ControlPanel is the entity that displays the control bar and health of the player at the top of the screen.
 */
public class ControlPanel {
    private JPanel mainPanel;
    private JButton resetButton;
    private JButton playPauseButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton nextLevelButton;
    private JProgressBar healthProgressBar;

    private Player player;

    /**
     * Constructs the ControlPanel, setting all the elements as non focusable, initialising the values of the health
     * bar, and adding action listeners.
     *
     * @param game The main {@link Game} object.
     */
    public ControlPanel(Game game) {
        player = game.getPlayer();

        setElementFocus();

        healthProgressBarAttributes();

        addButtonActionListeners(game);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JProgressBar getHealthProgressBar() {
        return healthProgressBar;
    }

    private void setElementFocus() {
        mainPanel.setFocusable(false);
        resetButton.setFocusable(false);
        playPauseButton.setFocusable(false);
        loadButton.setFocusable(false);
        saveButton.setFocusable(false);
        nextLevelButton.setFocusable(false);
        healthProgressBar.setFocusable(false);
    }

    private void healthProgressBarAttributes() {
        healthProgressBar.setMinimum(Player.MIN_HEALTH);
        healthProgressBar.setMaximum(Player.MAX_HEALTH);
        healthProgressBar.setString(player.getHealth() + "/" + Player.MAX_HEALTH);
        healthProgressBar.setStringPainted(true);
        healthProgressBar.setValue(player.getHealth());
    }

    private void addButtonActionListeners(Game game) {
        playPauseButton.addActionListener(e -> game.getWorld().pause());
        resetButton.addActionListener(e -> game.resetLevel());
        loadButton.addActionListener(e -> game.loadGame());
        saveButton.addActionListener(e -> game.saveGame());
        nextLevelButton.addActionListener(e -> game.goNextLevel());
    }
}
