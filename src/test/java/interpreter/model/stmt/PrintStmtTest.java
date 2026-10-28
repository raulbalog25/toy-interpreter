package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrintStmtTest {
    @Test
    void addsEvaluatedValueToOutput() throws Exception {
        PrintStmt statement = new PrintStmt(new ValueExp(new IntValue(5)));

        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                statement
        );

        statement.execute(state);

        IntValue result = (IntValue) state.getOut().get(0);
        assertEquals(5, result.getVal());
    }
}