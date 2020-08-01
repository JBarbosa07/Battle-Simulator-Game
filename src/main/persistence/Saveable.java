package persistence;

import java.io.PrintWriter;

// NOTE: Used TellerApp as a reference for the implementation of code

// Represents data that can be saved to file
public interface Saveable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
