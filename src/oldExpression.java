import java.util.List;
import java.util.Objects;

public class oldExpression {
    private class Node {
        private String value;
        private Node left;
        private Node right;

        public Node() {

        }

        public Node(String value) {
            this.value = value;
        }

        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        private boolean isLeaf() {
            return (left == null && right == null);
        }
    }

    private Node root;
    private final List<String> data;
    int position;

    public oldExpression(List<String> data) {
        this.root = null;
        this.position = 0;
        this.data = data;
    }

    private Node readTree() {
        Node n = new Node();

        String character = data.get(position++);
        String tmp = new String(character);
        if (main.isVariable(character) || (tmp.toCharArray().length > 1 && tmp.toCharArray()[1] == '!')) {
            n.value = (character.equals("!") ? null : character);
            n.left = null;
            n.right = null;
        } else if (character.equals("(") || character.equals("!(")) {
            n.left = readTree();
            n.value = data.get(position++);
            n.right = readTree();
            character = data.get(position++);
            if (!Objects.equals(character, ")")) {
                System.out.print("EXPECTED ) at " + position + " \"" + data.get(position - 1) + "\"");
                System.exit(1);
            }
//        } else if (character.equals("!(")){
//            System.out.println("!");
        } else {
            System.out.print("EXPECTED (((((((((((( at " + position + " \"" + data.get(position - 1) + "\"");
            System.exit(1);
        }

        return n;
    }

    public void read() {
        root = readTree();
    }

    public void preorderPrintTree(Node root) {
        if (root.isLeaf()) {
            System.out.print(root.value);
        } else {
//            switch (root.value) {
//                case "&" -> System.out.print("(" + root.value);
//                case "->" -> System.out.print("(" + root.value);
//                case "|" -> System.out.print("(" + root.value);
//            }
            System.out.print("(" + root.value);
            System.out.print(",");
            preorderPrintTree(root.left);
            System.out.print(",");
            preorderPrintTree(root.right);
            System.out.print(")");
        }
    }

    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);
        }
    }


//    private static Expression parseExpression(List<String> data){
//        Expression fullExpr = new Expression(null, null, false, "Z");
//        Expression current = fullExpr;
//
//        for (int i = 0; i < data.size(); i++) {
//            switch(data.get(i)){
//                case "&":
//                case "|":
//                case "->":
//                     break;
//
//                case "(":
//                    break;
//                case "!":
//                default:
//            }
//        }
//    }
//
//    private static Expression parseVarOrSkobka(){
//        Expression result = new Expression(null, null, false, "Z");
//
//        return result;
//    }

}
