package game.levels.save;

import java.io.*;

/**
 * SaveReader reads save files and returns a player to the start of their chosen level.
 */
public class SaveReader {

    /**
     * Reads a save file and outputs it as an integer array.
     *
     * @return an int array 3 long (if file exists) containing level, start of level health and start of level score.
     */
    public int[] readSave() {
        System.out.println("reading save...");
        File save = new File("data/saves/save.txt");
        if (save.exists()) {
            // if a save file has been made before
            int[] values = null;
            try (BufferedReader br = new BufferedReader(new FileReader("data/saves/save.txt"))) {
                String line = br.readLine();
                while (line != null) {
                    String[] tokens = line.split(",");
                    values = new int[tokens.length];
                    for (int i = 0; i < tokens.length; i++) {
                        values[i] = Integer.parseInt(tokens[i]);
                    }
                    line = br.readLine();
                }
                System.out.println("...save read");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return values;
        } else {
            // if this is the first time the game has been saved
            System.out.println("no save file found");
            return new int[0];
        }
    }
}
