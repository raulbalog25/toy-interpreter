package interpreter.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamplesTest {
    @Test
    void providesThreeExamplePrograms() {
        assertEquals(3, Examples.getExamples().size());
        assertEquals(3, Examples.getDescriptions().size());
    }
}