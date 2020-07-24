package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterListTest {
    CharacterList list;
    Character character;
    Character otherCharacter;

    @BeforeEach
    public void runBefore() {
        list = new CharacterList();
        character = new Character();
        otherCharacter = new Character();

        character.setName("Name");
        character.setHP(250);
        character.setATK(150);
        character.setDEF(100);
        character.setQuote("Hyah!");
    }

    @Test
    public void testCharacterList() {
        assertEquals(0, list.getSize());
    }

    @Test
    public void testAddCharacter() {
        list.addCharacter(character);
        assertEquals(1, list.getSize());
    }

    @Test
    public void testAddTwoCharacters() {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        assertEquals(2, list.getSize());
    }

    @Test
    public void testRemoveCharacter() {
        list.addCharacter(character);
        list.removeCharacter(character);
        assertEquals(0, list.getSize());
    }

    @Test
    public void testRemoveTwoCharacters() {
        list.addCharacter(character);
        list.addCharacter(otherCharacter);
        list.removeCharacter(character);
        assertEquals(1, list.getSize());
        list.removeCharacter(otherCharacter);
        assertEquals(0, list.getSize());
    }

    @Test
    public void testGetCharacter() {
        list.addCharacter(character);
        assertEquals(character, list.getCharacter(character));
    }
}
