package persistence;

import model.Character;
import model.CharacterList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {
    private static final String TEST_FILE = "./data/testCharacterList1.txt";
    private Writer testWriter;
    private CharacterList list;
    private Character p1;
    private Character p2;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer((new File(TEST_FILE)));
        list = new CharacterList();
        p1 = new Character();
        p2 = new Character();
        p1.setName("Player1");
        p1.setQuote("Blarg");
        p2.setName("Player2");
        p2.setQuote("Haha");
        list.addCharacter(p1);
        list.addCharacter(p2);
    }

    @Test
    void testWriteCharacterList() {
        testWriter.write(list);
        testWriter.close();

        try {
            CharacterList expectedList = Reader.readList(new File(TEST_FILE));
            assertTrue(expectedList.isContained(p1));
            assertTrue(expectedList.isContained(p2));
            assertEquals(2, expectedList.getSize());
        } catch (IOException e) {
            fail("unexpected call to exception");
        }
    }


}
