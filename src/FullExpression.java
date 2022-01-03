public class FullExpression {
    public static Expression allTree = null;
    public static Expression current = null;

    public static void getCurrentToRoot() {
        if (current != null) {
            while (current.getParent() != null) {
                current = current.getParent();
            }
        }
    }

    public static String toStringAllTree() {
        getCurrentToRoot();
        return current.toString();
    }




    /*
     *
     * */


    public static void addVarNode(Expression expression) {
        if (current == null) {
            allTree = expression;
            current = allTree;
        } else {
            switch (current.getOperation()) {
                case OR, AND -> {   // работа если в корне был OR or AND
                    if (current.getLeftBranch() == null) {
                        current.setLeftBranch(expression);
                        current.getLeftBranch().setParent(current);
                        current = current.getLeftBranch();
                    } else if (current.getRightBranch() == null) {
                        current.setRightBranch(expression);
                        current.getRightBranch().setParent(current);
                        current = current.getRightBranch();
                    } else {
                        //doFindEmpty();
                    }
                }
                case IMPL -> { // работа если в корне был IMPL
                    if (current.getRightBranch() == null) {
                        current.setRightBranch(expression);
                        current.getRightBranch().setParent(current);
                        current = current.getRightBranch();
                    } else if (current.getLeftBranch() == null) {
                        current.setLeftBranch(expression);
                        current.getLeftBranch().setParent(current);
                        current = current.getLeftBranch();
                    } else {
                        //doFindEmpty();
                    }
                }
                default -> {
                    System.out.println("пришло два подряд значения без операции");
                    System.exit(4);
                }
            }
        }
    }

//    public static void doFindEmpty() {
//        int counter = 1;
//        while (counter == 1) {
//            if (current.getParent() != null) {
//                current = current.getParent();
//                if (current.getRightBranch() == null || current.getLeftBranch() == null) {
//                    counter = 0;
//                }
//            } else {
//                // пришли в корень
//                counter = 0;
//            }
//        }
//    }

    public static void addOperNode(Expression expression) {
        if (current.getParent() == null) {
            current.setParent(expression);
            // TODO подумать всегда ли в левого ребенка
            expression.setLeftBranch(current);
            current = current.getParent();
            return;
        }

        Expression.Operation parentOper = current.getParent().getOperation();
        Expression parentOfCurr = current.getParent();
        Expression.Operation newOper = expression.getOperation();

        switch (newOper) {
            case AND -> {
                if (parentOper == Expression.Operation.AND) {
                    // TODO логика прокидывания наверх через все AND пока что не реализована, заменена логикой вставки вниз справа(else)
                    parentOfCurr.setRightBranch(expression);
                    expression.setParent(parentOfCurr);
                    expression.setLeftBranch(current);
                    current.setParent(expression);
                    current = current.getParent();
                } else {
                    // TODO возможно не только на место правого возможна подстановка
                    parentOfCurr.setRightBranch(expression);
                    expression.setParent(parentOfCurr);
                    expression.setLeftBranch(current);
                    current.setParent(expression);
                    current = current.getParent();
                }
            }
            case OR -> {
                if (parentOper == Expression.Operation.IMPL) {
                    parentOfCurr.setRightBranch(expression);
                    expression.setParent(parentOfCurr);
                    expression.setLeftBranch(current);
                    current.setParent(expression);
                    current = current.getParent();
                } else {
                    // TODO если родитель имеет больший приоритет(дизъюнкция и конъюнкция)
                }
            }
            case IMPL -> {
                // TODO всегда идет прокидывание наверх для поиска вставки
                // TODO вставка для импликации при двух импликациях будет таковой, что родитель имеет дочернего в ПРАВОМ ребенке
            }
        }
    }
}
