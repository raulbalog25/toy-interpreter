package interpreter.repository;

import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.stmt.NopStmt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class RepositoryTest {
    @Test
    void returnsCurrentProgramState() {
        PrgState state = PrgState.of(new NopStmt());

        Repository repository = new Repository(state, "repository-test.log");

        assertSame(state, repository.getCrtPrg());
    }
}