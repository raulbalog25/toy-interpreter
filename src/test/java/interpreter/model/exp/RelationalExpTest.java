package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RelationalExpTest {
    private BoolValue evaluate(RelOp op, int left, int right) throws MyException {
        IExp expression = new RelationalExp(
                op,
                new ValueExp(new IntValue(left)),
                new ValueExp(new IntValue(right))
        );

        return (BoolValue) expression.eval(new MyDictionary<>());
    }

    @Test
    void allOperatorsReturnTrueAndFalse() throws MyException {
        assertEquals(new BoolValue(true), evaluate(RelOp.LT, 2, 3));
        assertEquals(new BoolValue(false), evaluate(RelOp.LT, 3, 2));

        assertEquals(new BoolValue(true), evaluate(RelOp.LE, 2, 2));
        assertEquals(new BoolValue(false), evaluate(RelOp.LE, 3, 2));

        assertEquals(new BoolValue(true), evaluate(RelOp.EQ, 2, 2));
        assertEquals(new BoolValue(false), evaluate(RelOp.EQ, 2, 3));

        assertEquals(new BoolValue(true), evaluate(RelOp.NE, 2, 3));
        assertEquals(new BoolValue(false), evaluate(RelOp.NE, 2, 2));

        assertEquals(new BoolValue(true), evaluate(RelOp.GT, 3, 2));
        assertEquals(new BoolValue(false), evaluate(RelOp.GT, 2, 3));

        assertEquals(new BoolValue(true), evaluate(RelOp.GE, 2, 2));
        assertEquals(new BoolValue(false), evaluate(RelOp.GE, 2, 3));
    }

    @Test
    void nonIntegerLeftOperandThrows() {
        IExp expression = new RelationalExp(
                RelOp.LT,
                new ValueExp(new BoolValue(true)),
                new ValueExp(new IntValue(3))
        );

        assertThrows(
                ExpressionException.class,
                () -> expression.eval(new MyDictionary<>())
        );
    }

    @Test
    void nonIntegerRightOperandThrows() {
        IExp expression = new RelationalExp(
                RelOp.LT,
                new ValueExp(new IntValue(2)),
                new ValueExp(new BoolValue(true))
        );

        assertThrows(
                ExpressionException.class,
                () -> expression.eval(new MyDictionary<>())
        );
    }
}