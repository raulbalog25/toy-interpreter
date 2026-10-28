package interpreter.model.stmt;

import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AssignStmtTest {
    private PrgState createState() {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );
    }

    @Test
    void assignsValueToDeclaredVariable() throws Exception {
        PrgState state = createState();
        new VarDeclStmt("x", new IntType()).execute(state);

        AssignStmt assignment = new AssignStmt(
                "x",
                new ValueExp(new IntValue(7))
        );
        assignment.execute(state);

        IntValue result = (IntValue) state.getSymTable().lookup("x");
        assertEquals(7, result.getVal());
    }

    @Test
    void rejectsAssignmentToUndeclaredVariable() {
        PrgState state = createState();
        AssignStmt assignment = new AssignStmt(
                "x",
                new ValueExp(new IntValue(7))
        );

        assertThrows(StatementException.class, () -> assignment.execute(state));
    }

    @Test
    void rejectsAssignmentWithDifferentType() throws Exception {
        PrgState state = createState();
        new VarDeclStmt("x", new IntType()).execute(state);

        AssignStmt assignment = new AssignStmt(
                "x",
                new ValueExp(new BoolValue(true))
        );

        assertThrows(StatementException.class, () -> assignment.execute(state));
    }
}