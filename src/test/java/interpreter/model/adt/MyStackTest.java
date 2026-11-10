package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyStackTest {
    @Test
    void stackIsLifo() throws ADTException {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void poppingEmptyStackThrows() {
        MyStack<Integer> stack = new MyStack<>();

        assertThrows(ADTException.class, stack::pop);
    }

    @Test
    void toListReturnsTopElementFirst() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);

        assertEquals(List.of(2, 1), stack.toList());
    }
}