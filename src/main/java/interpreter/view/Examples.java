package interpreter.view;

import interpreter.model.exp.ArithExp;
import interpreter.model.exp.ArithOp;
import interpreter.model.exp.RelOp;
import interpreter.model.exp.RelationalExp;
import interpreter.model.exp.ValueExp;
import interpreter.model.exp.VarExp;
import interpreter.model.stmt.AssignStmt;
import interpreter.model.stmt.CloseRFileStmt;
import interpreter.model.stmt.CompStmt;
import interpreter.model.stmt.IfStmt;
import interpreter.model.stmt.IStmt;
import interpreter.model.stmt.OpenRFileStmt;
import interpreter.model.stmt.PrintStmt;
import interpreter.model.stmt.ReadFileStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.type.StringType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;

import java.util.List;

public class Examples {
    public static IStmt fileExample(String fileName) {
        return new CompStmt(
                new VarDeclStmt("varf", new StringType()),
                new CompStmt(
                        new AssignStmt(
                                "varf",
                                new ValueExp(new StringValue(fileName))
                        ),
                        new CompStmt(
                                new OpenRFileStmt(new VarExp("varf")),
                                new CompStmt(
                                        new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(
                                                new ReadFileStmt(
                                                        new VarExp("varf"),
                                                        "varc"
                                                ),
                                                new CompStmt(
                                                        new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(
                                                                new ReadFileStmt(
                                                                        new VarExp("varf"),
                                                                        "varc"
                                                                ),
                                                                new CompStmt(
                                                                        new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFileStmt(new VarExp("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static List<IStmt> getExamples() {
        IStmt ex1 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))
                )
        );

        IStmt ex2 = new CompStmt(
                new VarDeclStmt("a", new IntType()),
                new CompStmt(
                        new VarDeclStmt("b", new IntType()),
                        new CompStmt(
                                new AssignStmt(
                                        "a",
                                        new ArithExp(
                                                ArithOp.ADD,
                                                new ValueExp(new IntValue(2)),
                                                new ArithExp(
                                                        ArithOp.MUL,
                                                        new ValueExp(new IntValue(3)),
                                                        new ValueExp(new IntValue(5))
                                                )
                                        )
                                ),
                                new CompStmt(
                                        new AssignStmt(
                                                "b",
                                                new ArithExp(
                                                        ArithOp.ADD,
                                                        new VarExp("a"),
                                                        new ValueExp(new IntValue(1))
                                                )
                                        ),
                                        new PrintStmt(new VarExp("b"))
                                )
                        )
                )
        );

        IStmt ex3 = new CompStmt(
                new VarDeclStmt("a", new BoolType()),
                new CompStmt(
                        new VarDeclStmt("v", new IntType()),
                        new CompStmt(
                                new AssignStmt(
                                        "a",
                                        new ValueExp(new BoolValue(true))
                                ),
                                new CompStmt(
                                        new IfStmt(
                                                new VarExp("a"),
                                                new AssignStmt(
                                                        "v",
                                                        new ValueExp(new IntValue(2))
                                                ),
                                                new AssignStmt(
                                                        "v",
                                                        new ValueExp(new IntValue(3))
                                                )
                                        ),
                                        new PrintStmt(new VarExp("v"))
                                )
                        )
                )
        );

        IStmt ex4 = fileExample("test.in");

        IStmt ex5 = new CompStmt(
                new VarDeclStmt("a", new IntType()),
                new CompStmt(
                        new VarDeclStmt("b", new IntType()),
                        new CompStmt(
                                new AssignStmt(
                                        "a",
                                        new ValueExp(new IntValue(5))
                                ),
                                new CompStmt(
                                        new AssignStmt(
                                                "b",
                                                new ValueExp(new IntValue(7))
                                        ),
                                        new IfStmt(
                                                new RelationalExp(
                                                        RelOp.LT,
                                                        new VarExp("a"),
                                                        new VarExp("b")
                                                ),
                                                new PrintStmt(new VarExp("a")),
                                                new PrintStmt(new VarExp("b"))
                                        )
                                )
                        )
                )
        );

        return List.of(ex1, ex2, ex3, ex4, ex5);
    }

    public static List<String> getDescriptions() {
        return List.of(
                "Ex1: int v; v = 2; print(v)",
                "Ex2: int a; int b; a = 2 + 3 * 5; b = a + 1; print(b)",
                "Ex3: bool a; int v; a = true; if (a) v = 2 else v = 3; print(v)",
                "Ex4: read two integers from a file and print them",
                "Ex5: compare 5 and 7, then print the smaller value"
        );
    }
}