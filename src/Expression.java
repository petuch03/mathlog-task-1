public class Expression {
    private Expression leftBranch;
    private Expression rightBranch;
    private Expression parent;
    private final String variable;
    private Operation operation;
    private String negros;

    public Expression(Expression leftBranch, Expression rightBranch, Expression parent, String variable, Operation operation, String negros) {
        this.leftBranch = leftBranch;
        this.rightBranch = rightBranch;
        this.parent = parent;
        this.variable = variable;
        this.operation = operation;
        this.negros = negros;
    }

    public Expression() {
        this.leftBranch = null;
        this.rightBranch = null;
        this.parent = null;
        this.variable = null;
        this.operation = null;
        this.negros = null;
    }

    public void setNegros(String negros) {
        this.negros = negros;
    }

    public enum Operation {
        AND, OR, IMPL, VAR, SKOBKA, ROOT
    }

    public String toString() {
        StringBuilder result = new StringBuilder("");
        int negroslength = negros.length();
        if (negroslength == 0) {
            switch (operation) {
                case OR:
                    result.append("(|,").append(leftBranch != null ? leftBranch.toString() : "").append(",").append(rightBranch != null ? rightBranch.toString() : "").append(")");
                    break;
                case AND:
                    result.append("(&,").append(leftBranch != null ? leftBranch.toString() : "").append(",").append(rightBranch != null ? rightBranch.toString() : "").append(")");
                    break;
                case IMPL:
                    result.append("(->,").append(leftBranch != null ? leftBranch.toString() : "").append(",").append(rightBranch != null ? rightBranch.toString() : "").append(")");
                    break;
                case SKOBKA:
                    result.append(leftBranch != null ? leftBranch.toString() : "");
                    break;
                case VAR:
                    result.append(variable);
                    break;
            }
        } else {
            for (int i = 0; i < negroslength; i++) {
                result.append("(!");
            }

            switch (operation) {
                case SKOBKA:
                    result.append(leftBranch != null ? leftBranch.toString() : "");
                    break;
                case VAR:
                    result.append(variable);
                    break;
            }

            for (int i = 0; i < negroslength; i++) {
                result.append(")");
            }
        }

        return result.toString();
    }

    public Operation getOperation() {
        return operation;
    }

    public Expression getLeftBranch() {
        return leftBranch;
    }

    public Expression getParent() {
        return parent;
    }

    public Expression getRightBranch() {
        return rightBranch;
    }

    public String getNegros() {
        return negros;
    }

    public String getVariable() {
        return variable;
    }

    public void setParent(Expression parent) {
        this.parent = parent;
    }

    public void setLeftBranch(Expression leftBranch) {
        this.leftBranch = leftBranch;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setRightBranch(Expression rightBranch) {
        this.rightBranch = rightBranch;
    }
}