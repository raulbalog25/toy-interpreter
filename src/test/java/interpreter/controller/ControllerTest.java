package interpreter.controller;

import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.stmt.IStmt;
import interpreter.model.stmt.NopStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;
import interpreter.view.Examples;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerTest {
    private PrgState runProgram(IStmt program) throws Exception {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                program
        );

        Controller controller = new Controller(new Repository(state), false);
        controller.allStep();

        return state;
    }

    @Test
    void example1PrintsTwo() throws Exception {
        PrgState state = runProgram(Examples.getExamples().get(0));

        assertTrue(state.getExeStack().isEmpty());
        assertEquals(2, ((IntValue) state.getOut().get(0)).getVal());
    }

    @Test
    void example2PrintsEighteen() throws Exception {
        PrgState state = runProgram(Examples.getExamples().get(1));

        assertTrue(state.getExeStack().isEmpty());
        assertEquals(18, ((IntValue) state.getOut().get(0)).getVal());
    }

    @Test
    void example3PrintsTwo() throws Exception {
        PrgState state = runProgram(Examples.getExamples().get(2));

        assertTrue(state.getExeStack().isEmpty());
        assertEquals(2, ((IntValue) state.getOut().get(0)).getVal());
    }

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

        controller.oneStep(state);

        IntValue value = (IntValue) state.getSymTable().lookup("x");
        assertEquals(0, value.getVal());
    }

    @Test
    void oneStepOnEmptyStackThrows() throws Exception {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );
        state.getExeStack().pop();

        Controller controller = new Controller(new Repository(state), false);

        assertThrows(StatementException.class, () -> controller.oneStep(state));
    }
}