package pl.kaminski.okeapp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class QualificationUtil {
    private static File qualiFile = new File("src/main/resources/static/qualification.txt");

    public static List<String> readFromFile(){
        List<String> items = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(qualiFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                items.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

}
