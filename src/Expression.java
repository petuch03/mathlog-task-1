public class Expression {
    private Expression leftBranch;
    private Expression rightBranch;
    private Expression parent;
    private String variable;
    private Operation operation;
    private String negros;

    public Expression(Expression leftBranch, Expression rightBranch, Expression parent, String variable, Operation operation, String negros){
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
        AND, OR, IMPL, VAR
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        while(negros.length() > 0) {
            switch (operation){
                case OR -> result.append("(!" + "(|,").append(leftBranch.toString()).append(",").append(rightBranch.toString()).append("))");
                case AND -> result.append("(!" + "(&,").append(leftBranch.toString()).append(",").append(rightBranch.toString()).append("))");
                case IMPL -> result.append("(!" + "(->,").append(leftBranch.toString()).append(",").append(rightBranch.toString()).append("))");
                case VAR -> result.append("(!").append(variable).append(")");
            }
            negros = negros.substring(negros.length() - 1);
        }
        switch (operation){
            case OR -> result.append("(|,").append(leftBranch.toString()).append(",").append(rightBranch.toString()).append(")");
            case AND -> result.append("(&,").append(leftBranch.toString()).append(",").append(rightBranch.toString()).append(")");
            case IMPL -> result.append("(->,").append(leftBranch.toString()).append(",").append(rightBranch.toString()).append(")");
            case VAR -> result.append(variable);
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
