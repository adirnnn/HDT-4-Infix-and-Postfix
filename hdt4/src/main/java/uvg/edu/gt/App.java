package uvg.edu.gt;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    private static App instance;
    private Stack<Character> stack;
    private SinglyLinkedList<Character> singlyList;
    private DoublyLinkedList<Character> doublyList;

    private App() {
    }

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    public void setStackImplementation(String stackType) {
        switch (stackType) {
            case "ArrayList":
                stack = new ArrayListStack<>();
                break;
            case "Vector":
                stack = new VectorStack<>();
                break;
            case "Lista":
                selectListImplementation();
                break;
            default:
                System.out.println("Implementación de stack no válida.");
                break;
        }
    }

    private void selectListImplementation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la implementación de lista a emplear:");
        System.out.println("1. Simplemente encadenada");
        System.out.println("2. Doblemente encadenada");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                singlyList = new SinglyLinkedList<>();
                break;
            case 2:
                doublyList = new DoublyLinkedList<>();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public String evaluateInfixFromFile(String fileName) {
        String infixExpression = readExpressionFromFile(fileName);
        if (infixExpression == null) {
            return null;
        }
        String postfixExpression = infixToPostfix(infixExpression);
        return evaluatePostfix(postfixExpression);
    }

    private String readExpressionFromFile(String fileName) {
        StringBuilder expression = new StringBuilder();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                expression.append(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + fileName);
            return null;
        }
        return expression.toString();
    }

    private String infixToPostfix(String infix) {
        if (this.stack == null) {
            this.stack = new ArrayListStack<>();
        }

        StringBuilder postfix = new StringBuilder();
        for (char ch : infix.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // Pop '('
            } else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
    } 

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private String evaluatePostfix(String postfix) {
        Stack<Integer> evalStack = new ArrayListStack<>();
        for (char ch : postfix.toCharArray()) {
            if (Character.isDigit(ch)) {
                evalStack.push(ch - '0');
            } else {
                int operand2 = evalStack.pop();
                int operand1 = evalStack.pop();
                int result = performOperation(ch, operand1, operand2);
                evalStack.push(result);
            }
        }
        return Integer.toString(evalStack.pop());
    }

    private int performOperation(char operator, int operand1, int operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) throw new ArithmeticException("Division by zero");
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
    
    public static void main(String[] args) {
        App calculadora = App.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione la implementación del stack:");
            System.out.println("1. ArrayList");
            System.out.println("2. Vector");
            System.out.println("3. Lista");
            System.out.println("4. Salir");
            int stackChoice = scanner.nextInt();

            switch (stackChoice) {
                case 1:
                    calculadora.setStackImplementation("ArrayList");
                    break;
                case 2:
                    calculadora.setStackImplementation("Vector");
                break;
                case 3:
                    calculadora.setStackImplementation("Lista");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

            if (!exit) {
            String fileName = "src\\main\\java\\uvg\\edu\\gt\\datos.txt";
            String result = calculadora.evaluateInfixFromFile(fileName);
            if (result != null) {
                System.out.println("El resultado de la expresión es: " + result);
                }
            }
        }
    }
}