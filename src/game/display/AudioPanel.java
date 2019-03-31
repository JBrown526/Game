package game.display;

import game.Game;

import javax.swing.*;

public class AudioPanel {
    private JPanel mainPanel;
    private JButton playPauseButton;
    private JSlider soundEffectSlider;
    private JSlider musicSlider;
    private JLabel musicLabel;
    private JLabel soundEffectLabel;

    private Game game;

    public AudioPanel(Game game) {
        setElementFocus();

        playPauseButton.addActionListener(e -> game.toggleAudio());
        musicSlider.addChangeListener(e -> game.changeMusicVolume(musicSlider.getValue()));
        soundEffectSlider.addChangeListener(e -> game.getPlayer().setVolume(soundEffectSlider.getValue()));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setElementFocus() {
        mainPanel.setFocusable(false);
        playPauseButton.setFocusable(false);
        soundEffectSlider.setFocusable(false);
        musicSlider.setFocusable(false);
        soundEffectLabel.setFocusable(false);
        musicLabel.setFocusable(false);
    }
}
