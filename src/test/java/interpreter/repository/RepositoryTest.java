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
    void storesAndReturnsProgramState() {
        PrgState firstState = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );
        PrgState secondState = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );

        Repository repository = new Repository(firstState);

        assertSame(firstState, repository.getPrgState());

        repository.setPrgState(secondState);

        assertSame(secondState, repository.getPrgState());
    }
}