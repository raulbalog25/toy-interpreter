package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.IValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogicExpTest {
    private IExp bool(boolean value) {
        return new ValueExp(new BoolValue(value));
    }

    private IDictionary<String, IValue> emptySymTable() {
        return new MyDictionary<>();
    }

    @Test
    void evaluatesAnd() throws Exception {
        IExp expression = new LogicExp(LogicOp.AND, bool(true), bool(false));

        assertFalse(((BoolValue) expression.eval(emptySymTable())).getVal());
    }

    @Test
    void evaluatesOr() throws Exception {
        IExp expression = new LogicExp(LogicOp.OR, bool(false), bool(true));

        assertTrue(((BoolValue) expression.eval(emptySymTable())).getVal());
    }

    @Test
    void nonBooleanFirstOperandThrows() {
        IExp expression = new LogicExp(
                LogicOp.AND,
                new ValueExp(new IntValue(1)),
                bool(true)
        );

        assertThrows(
                ExpressionException.class,
                () -> expression.eval(emptySymTable())
        );
    }

    @Test
    void nonBooleanSecondOperandThrows() {
        IExp expression = new LogicExp(
                LogicOp.OR,
                bool(false),
                new ValueExp(new IntValue(1))
        );

        assertThrows(
                ExpressionException.class,
                () -> expression.eval(emptySymTable())
        );
    }
}