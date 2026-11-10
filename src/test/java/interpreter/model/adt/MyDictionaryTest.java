package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyDictionaryTest {
    @Test
    void putLookupAndUpdateWork() throws ADTException {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        dictionary.put("x", 1);
        assertEquals(1, dictionary.lookup("x"));

        dictionary.update("x", 2);
        assertEquals(2, dictionary.lookup("x"));
    }

    @Test
    void lookupMissingKeyThrows() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        assertThrows(ADTException.class, () -> dictionary.lookup("missing"));
    }

    @Test
    void removeDeletesExistingKey() throws ADTException {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("x", 1);

        dictionary.remove("x");

        assertFalse(dictionary.isDefined("x"));
    }

    @Test
    void removeMissingKeyThrows() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();

        assertThrows(ADTException.class, () -> dictionary.remove("missing"));
    }

    @Test
    void toMapReturnsReadOnlySnapshot() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("x", 1);

        Map<String, Integer> snapshot = dictionary.toMap();

        assertEquals(Map.of("x", 1), snapshot);
        assertThrows(
                UnsupportedOperationException.class,
                () -> snapshot.put("y", 2)
        );

        dictionary.put("z", 3);
        assertFalse(snapshot.containsKey("z"));
    }
}