import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        StringBuilder operacionTerminada = new StringBuilder();
        Stack<Character> pila = new Stack<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe la operación");
        String frase = sc.nextLine();

        for (int i = 0; i < frase.length(); i++) {
            char token = frase.charAt(i);

            if (Character.isLetterOrDigit(token)) {
                operacionTerminada.append(token);
            } else if (token == '(' || token == '[' || token == '{') {
                pila.push(token);
            } else if (token == ')' || token == ']' || token == '}') {
                while (!pila.isEmpty() && (pila.peek() != '(' && pila.peek() != '{' && pila.peek() != '[')) {
                    operacionTerminada.append(pila.pop());
                }
                if (!pila.isEmpty() && (pila.peek() == '(' || pila.peek() == '{' || pila.peek() == '[')) {
                    pila.pop();
                }
            } else if ((token == '+' || token == '-' || token == '*' || token == '/')) {
                while (!pila.isEmpty() &&
                        (pila.peek() == '+' || pila.peek() == '-' || pila.peek() == '*' || pila.peek() == '/') &&
                        precedenciaMayor(pila.peek(), token)) {
                    operacionTerminada.append(pila.pop());
                }
                pila.push(token);
            }
        }

        while (!pila.isEmpty()) {
            operacionTerminada.append(pila.pop());
        }

        System.out.println("Operación en notación posfija: " + operacionTerminada.toString());
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }

    private static boolean precedenciaMayor(char op1, char op2) {
        int precedence1 = getPrecedence(op1);
        int precedence2 = getPrecedence(op2);
        return precedence1 >= 0 && precedence2 >= 0 && precedence1 >= precedence2;
    }
}