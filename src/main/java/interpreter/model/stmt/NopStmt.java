package interpreter.model.stmt;

import interpreter.model.PrgState;

//reprezinta o intrctiune goala care nu modifica starea programului, dar prog inainteaza cu un pas
public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) {
        return state;
    }

    @Override
    public String toString() {
        return "nop";
    }
}