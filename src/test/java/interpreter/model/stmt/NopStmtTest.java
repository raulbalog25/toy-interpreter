package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class NopStmtTest {
    @Test
    void executesWithoutChangingTheExecutionStack() throws Exception {
        NopStmt statement = new NopStmt();
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                statement
        );

        statement.execute(state);

        assertFalse(state.getExeStack().isEmpty());
    }
}