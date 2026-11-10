package interpreter.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamplesTest {
    @Test
    void providesFiveExamplePrograms() {
        assertEquals(5, Examples.getExamples().size());
        assertEquals(5, Examples.getDescriptions().size());
    }
}