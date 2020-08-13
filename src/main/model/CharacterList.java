package model;

import com.google.gson.Gson;
import model.exceptions.CharacterDoesntExistException;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// Represents a list of characters created by the player
public class CharacterList implements Saveable {
    Map<String, Character> list;

    // EFFECTS: constructs an empty character list
    public CharacterList() {
        list = new HashMap<>();
    }

    // REQUIRES: duplicate characters cannot be added
    // MODIFIES: this
    // EFFECTS: adds the character into the list
    public void addCharacter(Character c) {
        list.put(c.getName(), c);
    }

    // MODIFIES: this
    // EFFECTS: removes the character from the list; if character does not exist, throws CharacterDoesntExistException
    public void removeCharacter(Character c) throws CharacterDoesntExistException {
        if (!list.containsValue(c)) {
            throw new CharacterDoesntExistException();
        }
        list.remove(c.getName());
    }

    // EFFECTS: finds the character in the list and returns it; if character does not exist, throws
    // CharacterDoesntExistException
    public Character getCharacter(String name) throws CharacterDoesntExistException {
        if (!list.containsKey(name)) {
            throw new CharacterDoesntExistException();
        }
        return list.get(name);
    }

    // EFFECTS: prints a list of character names in the list
    public String printCharacters() {
        String names = "Here are your current characters:";
        if (list.isEmpty()) {
            return names + " You currently do not have any characters";
        } else {
            for (Character c : list.values()) {
                names = names + " " + c.getName() + ",";
            }
            return names;
        }
    }

    // EFFECTS: if the given name already belongs to a member of the list, return true, otherwise return false
    public boolean isNameTaken(String name) {
        return list.containsKey(name);
    }

    // Getters
    public int getSize() {
        return list.size();
    }

    public boolean contains(Character c) {
        return list.containsValue(c);
    }

    @Override
    public void save(PrintWriter printWriter) {
        Gson g = new Gson();
        printWriter.print(g.toJson(this));
    }
}
