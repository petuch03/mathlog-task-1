import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ParsingSystem {
    private static int position = 0;
    // TODO присвоить значение List из MAIN обязательно
    private static List<String> data = new LinkedList<>();

    public Expression parserEntryPoint() {
        Expression rootExpression = null;//defaultParser();

        return rootExpression;
    }

    public /*Expression*/ static void defaultParser() {
        try {
            String nowValue = ParsingSystem.data.get(ParsingSystem.position++);

            if (nowValue.equals("&") || nowValue.equals("|") || nowValue.equals("->")) {
                FullExpression.addOperNode(ParsingSystem.getTreeFromOper(nowValue));
            } else {
                if (nowValue.equals("(")) {
                    // TODO обязательно обработать внутри случай с ! до закрытия скобки
                } else {
                    FullExpression.addVarNode(ParsingSystem.getTreeFromVar(nowValue));
                }
            }

            defaultParser();
        } catch (RuntimeException e) {
            return;
        }
//        StringBuilder negros = new StringBuilder();
//        if (Objects.equals(data.get(position), "!")) {
//            position++;
//            negros.append("!");
//        }
//        while (Objects.equals(data.get(position), "!")) {
//            position++;
//            negros.append("!");
//        }
//        if (Objects.equals(data.get(position), "(")) {
//            position++;
//            newExpression result = parserEntryPoint();
//            position++;
//            result.setNegros(negros.toString());
//            return result;
//        } else {
//            return new newExpression(null, null, null, data.get(position), newExpression.Operation.VAR, negros.toString());
//        }
    }

    public static Boolean isVariable(String theString) {
        return theString.matches("[A-Z]?([0-9A-Z’]*)");
    }

    public static Expression getTreeFromVar(String var) {
        return new Expression(null, null, null, var, Expression.Operation.VAR, "");
    }

    public static Expression getTreeFromOper(String oper) {
        Expression.Operation operation = null;
        switch (oper) {
            case "&" -> {
                operation = Expression.Operation.AND;
            }
            case "->" -> {
                operation = Expression.Operation.IMPL;
            }
            case "|" -> {
                operation = Expression.Operation.OR;
            }
        }
        return new Expression(null, null, null, oper, operation, "");
    }


    public static void test1() {
        data = Arrays.asList("egor", "->", "A", "|", "B", "&", "C"); // (->,egor,(|,A,(&,B,C)))
        defaultParser();
    }
}
