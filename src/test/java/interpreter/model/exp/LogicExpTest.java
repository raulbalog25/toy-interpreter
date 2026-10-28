package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogicExpTest {
    private final MyDictionary<String, IValue> symTable = new MyDictionary<>();

    private IExp bool(boolean value) {
        return new ValueExp(new BoolValue(value));
    }

    @Test
    void andWorks() throws Exception {
        LogicExp expression = new LogicExp(LogicOp.AND, bool(true), bool(false));

        assertEquals(new BoolValue(false), expression.eval(symTable));
    }

    @Test
    void orWorks() throws Exception {
        LogicExp expression = new LogicExp(LogicOp.OR, bool(true), bool(false));

        assertEquals(new BoolValue(true), expression.eval(symTable));
    }

    @Test
    void nonBooleanOperandThrows() {
        IExp number = new ValueExp(new IntValue(3));
        LogicExp expression = new LogicExp(LogicOp.AND, number, bool(true));

        assertThrows(ExpressionException.class, () -> expression.eval(symTable));
    }
}