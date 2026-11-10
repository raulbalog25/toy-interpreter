package interpreter.model.type;

import interpreter.model.value.StringValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTypeTest {
    @Test
    void defaultValueIsEmptyString() {
        StringType type = new StringType();

        assertEquals(new StringValue(""), type.defaultValue());
        assertEquals("string", type.toString());
    }
}