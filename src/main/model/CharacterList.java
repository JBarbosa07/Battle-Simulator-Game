package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of characters created by the player
public class CharacterList {
    List<Character> list;

    // EFFECTS: constructs an empty CharacterList
    public CharacterList() {
        list = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the character into the list
    public void addCharacter(Character c) {
        list.add(c);
    }

    // REQUIRES: character exists in the list
    // MODIFIES: this
    // EFFECTS: removes the character from the list
    public void removeCharacter(Character c) {
        list.remove(c);
    }

    // REQUIRES: character exists in the list
    // EFFECTS: finds the character in the list and returns it
    public Character getCharacter(String name) {
        Character selected = null;
        for (Character c: list) {
            if (c.getName() == name) {
                selected = c;
            }
        }
        return selected;
    }

    // EFFECTS: prints a list of character names in the list
    public String printCharacters() {
        String names = "Here are your current characters:";
        for (Character c : list) {
            names = names + " " + c.getName() + ",";
        }
        return names;
    }

    // Getters
    public int getSize() {
        return list.size();
    }

}
