package game.display;

import game.Game;

import javax.swing.*;

/**
 * AudioPanel is the entity that displays the volume controls at the bottom of the screen.
 */
public class AudioPanel {
    private JPanel mainPanel;
    private JButton playPauseButton;
    private JSlider soundEffectSlider;
    private JSlider musicSlider;
    private JLabel musicLabel;
    private JLabel soundEffectLabel;

    private Game game;

    /**
     * Constructs the AudioPanel, setting all elements as non focusable and adding listeners to the button and sliders.
     *
     * @param game The main {@link Game} object.
     */
    public AudioPanel(Game game) {
        setElementFocus();

        addInterfaceListeners();
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

    private void addInterfaceListeners() {
        playPauseButton.addActionListener(e -> game.toggleAudio());
        musicSlider.addChangeListener(e -> game.changeMusicVolume(musicSlider.getValue()));
        soundEffectSlider.addChangeListener(e -> game.getPlayer().setVolume(soundEffectSlider.getValue()));
    }
}
