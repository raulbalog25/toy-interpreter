package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyDictionaryTest {
    @Test
    void putAndLookupValue() throws ADTException {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("a", 2);

        assertTrue(dictionary.isDefined("a"));
        assertEquals(2, dictionary.lookup("a"));
    }

    @Test
    void updateChangesExistingValue() throws ADTException {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("a", 2);
        dictionary.update("a", 5);

        assertEquals(5, dictionary.lookup("a"));
    }

    @Test
    void lookupMissingKeyThrows() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        assertFalse(dictionary.isDefined("missing"));
        assertThrows(ADTException.class, () -> dictionary.lookup("missing"));
    }
}