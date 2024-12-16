import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Calc{
	public static int eval(String express) {
		Stack<Integer> stack = new Stack<>();
		String[] x = express.split(" ");
		
		for (String y : x) {
			if (isOperator(y)) {
				int b = stack.pop();
				int a = stack.pop();
				
				stack.push(applyOperator(a, b, y));
			}
			else {
				stack.push(Integer.parseInt(y));
			}
		}
		
		return stack.pop();
	}
	private static boolean isOperator(String y) {
        return y.equals("+") || y.equals("-") || y.equals("*") || y.equals("/") || y.equals("%");
    }
	
	private static int applyOperator(int a, int b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Error! Division by zero.");
                return a / b;
            case "%":
                if (b == 0) throw new ArithmeticException("Error! Division by zero.");
                return a % b;
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
	
	public static void evaluateExpressionsFromFile(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        String line;
	            while ((line = br.readLine()) != null) {
	                try {
	                    // Evaluate and print result
	                    int result = eval(line);
	                    System.out.println("Expression: " + line + " = " + result);
	                } catch (Exception e) {
	                    System.out.println("Error evaluating expression: " + line + " - " + e.getMessage());
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Error reading file: " + e.getMessage());
	            }
	        }

	public static void main(String[] args) {
	        // Read from a file and evaluate them
	        String fileName = "C:\\Users\\maste\\eclipse-workspace\\Calculator\\src\\expressions.txt";
	        evaluateExpressionsFromFile(fileName);
	    }
}