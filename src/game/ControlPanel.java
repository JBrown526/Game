package game;

import javax.swing.*;

public class ControlPanel {
    private JPanel mainPanel;
    private JButton resetButton;
    private JButton pauseButton;
    private JProgressBar healthProgressBar;

    private Player player;

    public ControlPanel(Game game) {
        player = game.getPlayer();
        mainPanel.setFocusable(false);
        resetButton.setFocusable(false);
        pauseButton.setFocusable(false);
        healthProgressBar.setFocusable(false);

        healthProgressBar.setMinimum(Player.MIN_HEALTH);
        healthProgressBar.setMaximum(Player.MAX_HEALTH);
        healthProgressBar.setString(player.getHealth() + "/100");
        healthProgressBar.setStringPainted(true);
        healthProgressBar.setValue(player.getHealth());

        pauseButton.addActionListener(e -> game.getWorld().pause());
        resetButton.addActionListener(e -> game.resetLevel());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JProgressBar getHealthProgressBar() {
        return healthProgressBar;
    }
}
