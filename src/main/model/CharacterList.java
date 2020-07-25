package model;

import exceptions.CharacterAlreadyExistsException;
import exceptions.CharacterDoesntExistException;

import java.util.ArrayList;
import java.util.List;

// Represents a list of characters created by the player
public class CharacterList {
    List<Character> list;

    // EFFECTS: constructs an empty CharacterList
    public CharacterList() {
        list = new ArrayList<>();
    }

    // REQUIRES: duplicate characters cannot be added
    // MODIFIES: this
    // EFFECTS: adds the character into the list
    public void addCharacter(Character c) {
        list.add(c);
    }

    // MODIFIES: this
    // EFFECTS: removes the character from the list
    public void removeCharacter(Character c) throws CharacterDoesntExistException {
        if (!list.contains(c)) {
            throw new CharacterDoesntExistException();
        }
        list.remove(c);
    }

    // EFFECTS: finds the character in the list and returns it
    public Character getCharacter(String name) throws CharacterDoesntExistException {
        for (Character c : list) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        throw new CharacterDoesntExistException();
    }

    // EFFECTS: prints a list of character names in the list
    public String printCharacters() {
        String names = "Here are your current characters:";
        for (Character c : list) {
            names = names + " " + c.getName() + ",";
        }
        return names;
    }

    // EFFECTS: if the given name already belongs to a member of the list, return true, otherwise return false
    public boolean isNameTaken(String name) {
        for (Character c : list) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Getters
    public int getSize() {
        return list.size();
    }

}
