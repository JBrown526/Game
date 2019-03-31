package game.levels.save;

import game.Game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Save writer writes level data to a file.
 */
public class SaveWriter {

    private Game game;

    public SaveWriter(Game game) {
        this.game = game;
    }

    /**
     * Writes the level a player is on and the stats they had at the beginning of the level.
     */
    public void writeSave() {
        File oldSave = new File("data/saves/save.txt");
        System.out.println(oldSave.delete() ? "old save deleted" : "no old save");

        try (FileWriter writer = new FileWriter("data/saves/save.txt", true)) {
            writer.write(game.getCurrentLevel() + "," + game.getLastLevelHealth() + "," + game.getLastLevelScore());
            System.out.println("game saved (level: " + game.getCurrentLevel() + ", health: " + game.getLastLevelHealth() + ", score: " + game.getLastLevelScore() + ")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
