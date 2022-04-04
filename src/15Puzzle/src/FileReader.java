import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static List<List<Integer>> read(File file) {
        try {
            String barisFile;
            List<List<Integer>> ret = new ArrayList<List<Integer>>(4);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                barisFile = reader.nextLine();
                List<Integer> kontenBaris = new ArrayList<Integer>(4);
                for (String elemen: barisFile.split(" ")) {
                    if (elemen.equals("-")) {
                        kontenBaris.add(16);
                    } else {
                        kontenBaris.add(Integer.parseInt(elemen));
                    }
                }
                ret.add(kontenBaris);
            }
            reader.close();
            return ret;

        } catch (FileNotFoundException e) {
            System.out.println("file tidak ada.");
            return null;
        }
    }
}
