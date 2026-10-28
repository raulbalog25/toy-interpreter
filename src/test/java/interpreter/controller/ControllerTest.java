package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.stmt.NopStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerTest {
    @Test
    void oneStepExecutesNextStatement() throws Exception {
        VarDeclStmt statement = new VarDeclStmt("x", new IntType());
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                statement
        );
        Controller controller = new Controller(new Repository(state), false);

        controller.oneStep();

        IntValue value = (IntValue) state.getSymTable().lookup("x");
        assertEquals(0, value.getVal());
    }

    @Test
    void oneStepRejectsEmptyExecutionStack() throws Exception {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );
        state.getExeStack().pop();

        Controller controller = new Controller(new Repository(state), false);

        assertThrows(MyException.class, controller::oneStep);
    }
}