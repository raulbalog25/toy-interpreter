package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class CompStmtTest {
    @Test
    void executesFirstStatementBeforeSecond() throws Exception {
        IStmt first = new NopStmt();
        IStmt second = new NopStmt();
        CompStmt compound = new CompStmt(first, second);

        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                compound
        );

        compound.execute(state);

        assertSame(first, state.getExeStack().pop());
        assertSame(second, state.getExeStack().pop());
    }
}