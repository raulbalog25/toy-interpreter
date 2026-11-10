package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyListTest {
    @Test
    void addGetAndSizeWork() throws ADTException {
        MyList<Integer> list = new MyList<>();
        list.add(10);
        list.add(20);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    void getInvalidIndexThrows() {
        MyList<Integer> list = new MyList<>();

        assertThrows(ADTException.class, () -> list.get(0));
    }

    @Test
    void toListPreservesInsertionOrder() {
        MyList<Integer> list = new MyList<>();
        list.add(10);
        list.add(20);

        assertEquals(List.of(10, 20), list.toList());
    }
}