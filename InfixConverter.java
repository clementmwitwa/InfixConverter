import java.util.*;

public class InfixConverter {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Function to convert infix to postfix
    static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // If '(', push to stack
            else if (c == '(') {
                stack.push(c);
            }
            // If ')', pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }
            // Operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Function to reverse string
    static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Function to swap brackets
    static String swapBrackets(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(')
                chars[i] = ')';
            else if (chars[i] == ')')
                chars[i] = '(';
        }
        return String.valueOf(chars);
    }

    // Function to convert infix to prefix
    static String infixToPrefix(String expression) {
        String reversed = reverse(expression);
        String swapped = swapBrackets(reversed);
        String postfix = infixToPostfix(swapped);
        return reverse(postfix);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println(" INFIX TO POSTFIX & PREFIX CONVERTER ");
        System.out.println("====================================");

        System.out.print("Enter an infix expression: ");
        String input = scanner.nextLine().replaceAll("\\s+", "");

        String postfix = infixToPostfix(input);
        String prefix = infixToPrefix(input);

        System.out.println("\nResults:");
        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Prefix Expression : " + prefix);

        scanner.close();
    }
}
