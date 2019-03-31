package game.levels.save;

import java.io.*;

public class SaveReader {

    public int[] readSave() {
        System.out.println("reading save...");
        File save = new File("data/saves/save.txt");
        if (save.exists()) {
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
            System.out.println("no save file found");
            return null;
        }
    }
}
