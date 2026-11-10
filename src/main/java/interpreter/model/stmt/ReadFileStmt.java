package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public record ReadFileStmt(IExp exp, String varName) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        if (!(exp.eval(state.getSymTable()) instanceof StringValue fileName)) {
            throw new StatementException(
                    "readFile: " + exp + " is not a string"
            );
        }

        if (!state.getSymTable().isDefined(varName)) {
            throw new StatementException(
                    "readFile: variable " + varName + " is not declared"
            );
        }

        IValue variableValue = state.getSymTable().lookup(varName);
        if (!(variableValue.getType() instanceof IntType)) {
            throw new StatementException(
                    "readFile: variable " + varName + " is not an integer"
            );
        }

        if (!state.getFileTable().isDefined(fileName)) {
            throw new StatementException(
                    "readFile: " + fileName + " is not open"
            );
        }

        BufferedReader reader = state.getFileTable().lookup(fileName);

        try {
            String line = reader.readLine();
            int value = line == null ? 0 : Integer.parseInt(line.strip());
            state.getSymTable().update(varName, new IntValue(value));
        } catch (IOException e) {
            throw new StatementException(
                    "readFile: I/O error: " + e.getMessage()
            );
        } catch (NumberFormatException e) {
            throw new StatementException(
                    "readFile: line is not an integer"
            );
        }

        return state;
    }

    @Override
    public String toString() {
        return "readFile(" + exp + ", " + varName + ")";
    }
}