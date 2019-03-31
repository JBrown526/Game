package game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveWriter {

    private Game game;

    public SaveWriter(Game game) {
        this.game = game;
    }

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
