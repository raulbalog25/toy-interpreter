package interpreter.model.stmt;

import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IfStmtTest {
    private PrgState createState(IStmt program) {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                program
        );
    }

    @Test
    void pushesThenBranchWhenConditionIsTrue() throws Exception {
        IStmt thenStatement = new NopStmt();
        IStmt elseStatement = new NopStmt();
        IfStmt statement = new IfStmt(
                new ValueExp(new BoolValue(true)),
                thenStatement,
                elseStatement
        );
        PrgState state = createState(statement);

        statement.execute(state);

        assertSame(thenStatement, state.getExeStack().pop());
    }

    @Test
    void pushesElseBranchWhenConditionIsFalse() throws Exception {
        IStmt thenStatement = new NopStmt();
        IStmt elseStatement = new NopStmt();
        IfStmt statement = new IfStmt(
                new ValueExp(new BoolValue(false)),
                thenStatement,
                elseStatement
        );
        PrgState state = createState(statement);

        statement.execute(state);

        assertSame(elseStatement, state.getExeStack().pop());
    }

    @Test
    void rejectsNonBooleanCondition() {
        IfStmt statement = new IfStmt(
                new ValueExp(new IntValue(1)),
                new NopStmt(),
                new NopStmt()
        );
        PrgState state = createState(statement);

        assertThrows(StatementException.class, () -> statement.execute(state));
    }
}