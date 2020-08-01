package persistence;

import com.google.gson.Gson;
import model.CharacterList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

// NOTE: Utilized Gson for the implementation of code; used following link as reference:
// https://www.java67.com/2016/10/3-ways-to-convert-string-to-json-object-in-java.html

// A reader that can read account data from a file
public class Reader {

    // EFFECTS: returns character list parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static CharacterList readList(File file) throws IOException {
        List<String> fileContent = readFile(file);
        String converted = fileContent.get(0);
        Gson g = new Gson();
        return g.fromJson(converted, CharacterList.class);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }
}
