package interpreter.model.stmt;

import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileStmtTest {
    @TempDir
    Path dir;

    private PrgState state;
    private String fileName;
    private IExp fileExpression;

    @BeforeEach
    void setUp() throws Exception {
        Path file = dir.resolve("numbers.in");
        Files.writeString(file, "7\n9\n");

        fileName = file.toString();
        fileExpression = new ValueExp(new StringValue(fileName));
        state = PrgState.of(new NopStmt());
    }

    @AfterEach
    void closeOpenReaders() throws Exception {
        for (BufferedReader reader : state.getFileTable().toMap().values()) {
            reader.close();
        }
    }

    @Test
    void openAddsFileToFileTable() throws Exception {
        new OpenRFileStmt(fileExpression).execute(state);

        assertTrue(state.getFileTable().isDefined(new StringValue(fileName)));
    }

    @Test
    void openRejectsNonStringExpression() {
        IExp nonString = new ValueExp(new IntValue(7));

        assertThrows(
                StatementException.class,
                () -> new OpenRFileStmt(nonString).execute(state)
        );
    }

    @Test
    void openRejectsAlreadyOpenFile() throws Exception {
        new OpenRFileStmt(fileExpression).execute(state);

        assertThrows(
                StatementException.class,
                () -> new OpenRFileStmt(fileExpression).execute(state)
        );
    }

    @Test
    void openRejectsMissingFile() {
        IExp missingFile = new ValueExp(
                new StringValue(dir.resolve("missing.in").toString())
        );

        assertThrows(
                StatementException.class,
                () -> new OpenRFileStmt(missingFile).execute(state)
        );
    }

    @Test
    void readGetsNumbersInOrderAndReturnsZeroAtEnd() throws Exception {
        new VarDeclStmt("x", new IntType()).execute(state);
        new OpenRFileStmt(fileExpression).execute(state);

        ReadFileStmt read = new ReadFileStmt(fileExpression, "x");

        read.execute(state);
        assertEquals(new IntValue(7), state.getSymTable().lookup("x"));

        read.execute(state);
        assertEquals(new IntValue(9), state.getSymTable().lookup("x"));

        read.execute(state);
        assertEquals(new IntValue(0), state.getSymTable().lookup("x"));
    }

    @Test
    void readRejectsNonStringExpression() throws Exception {
        new VarDeclStmt("x", new IntType()).execute(state);
        IExp nonString = new ValueExp(new IntValue(7));

        assertThrows(
                StatementException.class,
                () -> new ReadFileStmt(nonString, "x").execute(state)
        );
    }

    @Test
    void readRejectsUndeclaredVariable() throws Exception {
        new OpenRFileStmt(fileExpression).execute(state);

        assertThrows(
                StatementException.class,
                () -> new ReadFileStmt(fileExpression, "missing").execute(state)
        );
    }

    @Test
    void readRejectsVariableThatIsNotInteger() throws Exception {
        new VarDeclStmt("flag", new BoolType()).execute(state);
        new OpenRFileStmt(fileExpression).execute(state);

        assertThrows(
                StatementException.class,
                () -> new ReadFileStmt(fileExpression, "flag").execute(state)
        );
    }

    @Test
    void readRejectsFileThatIsNotOpen() throws Exception {
        new VarDeclStmt("x", new IntType()).execute(state);

        assertThrows(
                StatementException.class,
                () -> new ReadFileStmt(fileExpression, "x").execute(state)
        );
    }

    @Test
    void readRejectsNonIntegerLine() throws Exception {
        Path badFile = dir.resolve("bad.in");
        Files.writeString(badFile, "hello\n");

        IExp badFileExpression = new ValueExp(
                new StringValue(badFile.toString())
        );

        new VarDeclStmt("x", new IntType()).execute(state);
        new OpenRFileStmt(badFileExpression).execute(state);

        assertThrows(
                StatementException.class,
                () -> new ReadFileStmt(badFileExpression, "x").execute(state)
        );
    }

    @Test
    void closeRemovesFileFromFileTable() throws Exception {
        new OpenRFileStmt(fileExpression).execute(state);

        new CloseRFileStmt(fileExpression).execute(state);

        assertFalse(state.getFileTable().isDefined(new StringValue(fileName)));
    }

    @Test
    void closeRejectsNonStringExpression() {
        IExp nonString = new ValueExp(new IntValue(7));

        assertThrows(
                StatementException.class,
                () -> new CloseRFileStmt(nonString).execute(state)
        );
    }

    @Test
    void closeRejectsFileThatIsNotOpen() {
        assertThrows(
                StatementException.class,
                () -> new CloseRFileStmt(fileExpression).execute(state)
        );
    }
}