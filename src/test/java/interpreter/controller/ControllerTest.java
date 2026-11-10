package interpreter.controller;

import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.stmt.IStmt;
import interpreter.model.stmt.NopStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;
import interpreter.view.Examples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {
    @TempDir
    Path dir;

    private PrgState runProgram(IStmt program) throws Exception {
        PrgState state = PrgState.of(program);
        Repository repository =
                new Repository(state, dir.resolve("controller-test.log").toString());

        new Controller(repository, false).allStep();

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
    void fileExampleReadsBothNumbersAndClosesFile() throws Exception {
        Path input = dir.resolve("numbers.in");
        Files.writeString(input, "15\n50\n");

        PrgState state = runProgram(Examples.fileExample(input.toString()));

        assertEquals(new IntValue(15), state.getOut().get(0));
        assertEquals(new IntValue(50), state.getOut().get(1));
        assertTrue(state.getFileTable().toMap().isEmpty());
    }

    @Test
    void fileExamplePrintsZerosForEmptyFile() throws Exception {
        Path input = dir.resolve("empty.in");
        Files.writeString(input, "");

        PrgState state = runProgram(Examples.fileExample(input.toString()));

        assertEquals(new IntValue(0), state.getOut().get(0));
        assertEquals(new IntValue(0), state.getOut().get(1));
    }

    @Test
    void example5PrintsFive() throws Exception {
        PrgState state = runProgram(Examples.getExamples().get(4));

        assertEquals(new IntValue(5), state.getOut().get(0));
    }

    @Test
    void oneStepExecutesNextStatement() throws Exception {
        VarDeclStmt statement = new VarDeclStmt("x", new IntType());
        PrgState state = PrgState.of(statement);
        Repository repository =
                new Repository(state, dir.resolve("one-step.log").toString());

        new Controller(repository, false).oneStep(state);

        IntValue value = (IntValue) state.getSymTable().lookup("x");
        assertEquals(0, value.getVal());
    }

    @Test
    void oneStepOnEmptyStackThrows() throws Exception {
        PrgState state = PrgState.of(new NopStmt());
        state.getExeStack().pop();

        Repository repository =
                new Repository(state, dir.resolve("empty-stack.log").toString());
        Controller controller = new Controller(repository, false);

        assertThrows(
                StatementException.class,
                () -> controller.oneStep(state)
        );
    }

    @Test
    void logFileContainsFourSectionsInOrder() throws Exception {
        Path logPath = dir.resolve("log.txt");
        PrgState state = PrgState.of(new NopStmt());
        Repository repository = new Repository(state, logPath.toString());

        new Controller(repository, false).allStep();

        String log = Files.readString(logPath);
        int exeStack = log.indexOf("ExeStack:");
        int symTable = log.indexOf("SymTable:");
        int out = log.indexOf("Out:");
        int fileTable = log.indexOf("FileTable:");

        assertTrue(
                exeStack >= 0
                        && exeStack < symTable
                        && symTable < out
                        && out < fileTable
        );
    }
}