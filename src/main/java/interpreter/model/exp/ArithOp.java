package interpreter.model.exp;

//enumera operatii aritmetice acceptate
public enum ArithOp {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/");

    private final String symbol;

    ArithOp(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}