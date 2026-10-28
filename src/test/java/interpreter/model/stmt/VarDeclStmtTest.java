package interpreter.model.stmt;

import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VarDeclStmtTest {

    private PrgState createState(IStmt program) {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                program
        );
    }

    @Test
    void declaresVariableWithDefaultValue() throws Exception {
        VarDeclStmt statement = new VarDeclStmt("x", new IntType());
        PrgState state = createState(statement);

        statement.execute(state);

        IntValue value = (IntValue) state.getSymTable().lookup("x");
        assertEquals(0, value.getVal());
    }

    @Test
    void rejectsDuplicateDeclaration() throws Exception {
        VarDeclStmt statement = new VarDeclStmt("x", new IntType());
        PrgState state = createState(statement);

        statement.execute(state);

        assertThrows(StatementException.class, () -> statement.execute(state));
    }
}