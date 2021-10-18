import java.util.Arrays;
/**
 * A class containing test methods for the ResizeableArrayStack implementation
 */
public class ArrayStackTest {
    public static void main(String[] args)
    {
        String postfix = "23*42-/56*+";

        System.out.println("postfix expression is: 23*42-/56*+");

        System.out.println("result: " + evaluatePostfix(postfix));

    } // end main

    /**
     * Evaluates a postfix string
     * @param postfix the postfix string to be evaluated
     * @return the result of the postfix string
     */
    public static double evaluatePostfix(String postfix)
    {
        // Creates a new ArrayStack
        ResizableArrayStack<Double> valueStack = new ResizableArrayStack<>();
        char nextChar = ' ';
        double result = 0.0;

        // iterates through the postfix expression
        for (int i = 0; i < postfix.length(); i++)
        {
            if(postfix.charAt(i) != ' ')
                nextChar = postfix.charAt(i);
            else
                break;  // skip empty characters
            switch (nextChar)
            {
                case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                valueStack.push((double) (nextChar - 48)); //pushes value of nextChar as a double (0 -> 0.0, 1 -> 1.0, etc.)
                break;
                case '+':
                    result = valueStack.pop() + valueStack.pop();
                    valueStack.push(result);
                    break;
                case '-':
                    result = -1 * (valueStack.pop() - valueStack.pop());
                    valueStack.push(result);
                    break;
                case '*':
                    result = valueStack.pop() * valueStack.pop();
                    valueStack.push(result);
                    break;
                case '/':
                    double divisor = valueStack.pop();
                    double dividend = valueStack.pop();
                    result = dividend / divisor;
                    valueStack.push(result);
                    break;
                case '^':
                    double exponent = valueStack.pop();
                    double number = valueStack.pop();
                    result = Math.pow(number,exponent);
                    valueStack.push(result);
                    break;
                default: break; //Ignore unexpected characters
            }
        }
        //evaluate postfix result of postfix expression
        result = valueStack.peek();
        return result;
    }
}
