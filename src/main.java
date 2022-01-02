import java.util.*;
import java.util.stream.Collectors;

public class main {
    static int position = 0;
    static List<String> result;
    static List<String> data;
    public static void main(String[] args) {
        String test_1 = "((!A&!B)->!(A|B))";
        //data = parser(test_1);
        data = Arrays.asList("(", "(", "(!A)", "&", "(!B)", ")", "->", "(", "!(", "A", "|", "B", ")", ")", ")");
        parseNot();
        System.out.println(data);
        System.out.println("------UTILITY SOUT ABOVE------");
        Expression expression = new Expression(data);
        expression.read();
        System.out.println(test_1);
        System.out.println("------PREORDER------");
        expression.preorderPrint();
    }

    public static List<String> parser(String input) {
        return zipMetavariables(parserSpaces(input));
    }

    public static List<String> parserSpaces(String input){
        String[] inp = input.split("");
        List<String> data = Arrays.asList(inp);

        return data.stream()
                .filter(i -> !Objects.equals(i, " ") && !Objects.equals(i, ">") && !Objects.equals(i, "\n"))
                .collect(Collectors.toList());
    }

    public static List<String> zipMetavariables(List<String> data){
        for (int i = 0; i < data.size(); i++) {
            if (isVariable(data.get(i)) && i != data.size() - 1 && isVariable(data.get(i+1))) {
                data.set(i, data.get(i) + data.get(i+1));
                data.remove(i+1);
                i--;
                continue;
            }
            if (data.get(i).equals("-")) {
                data.set(i, "->");
            }
        }
        return data;
    }

    public static Boolean isVariable(String theString) {
        return theString.matches("[A-Z]?([0-9A-Z’]*)");
    }

    public static List<String> parseNot(){
        if (position < data.size()){
            String oldCharacter = data.get(position++);
            if (Objects.equals(oldCharacter, "!")) {
                String nextCharacter = data.get(position);
//                data.set(position-1, !Objects.equals(nextCharacter, "(") ? ("(" + oldCharacter + nextCharacter + ")") : ("(" + oldCharacter + nextCharacter));
                data.set(position-1, "(" + oldCharacter + nextCharacter + ")");
                data.remove(position);
            }
            result = parseNot();
        }
        return result;
    }

}

// !A&!B->!(A|B)
// (->,(&,(!A),(!B)),(!(|,A,B)))

//[(, (, (!A), &, (!B), ), ->, !(, A, |, B, ), )]
//        (->,(&,(!A),(!B)),(|,A,B))
//было вот так (!()... а надо чтобы он сделал так: (, !( ... )
// P1’->!QQ->!R10&S|!T&U&V
// (->,P1’,(->,(!QQ),(|,(&,(!R10),S),(&,(&,(!T),U),V))))