/**
 * A test
 *
 */
import java.util.Arrays;

/**
 * A class containing test methods for the ResizeableArrayBag implementation
 */
public class ArrayStackTest {
    public static void main(String[] args)
    {
        String postfix = "23*42-/56*+";

        System.out.println("postfix expression is: 23*42-/56*+");

        System.out.println("result:" + evaluatePostfix(postfix));

    } // end main

    public static double evaluatePostfix(String postfix)
    {
        /**
         * // Evaluates a postfix expression.
         * valueStack = a new empty stack
         * while (postfix has characters left to parse)
         * {
         *    nextCharacter = next nonblank character of postfix
         *    switch (nextCharacter)
         *    {
         *       case variable:
         *          valueStack.push(value of the variable nextCharacter)
         *          break
         *       case '+' : case '-' : case '*' : case '/' : case '^' :
         *          operandTwo = valueStack.pop()
         *          operandOne = valueStack.pop()
         *          result = the result of the operation in nextCharacter and its operands operandOne and operandTwo
         *          valueStack.push(result)
         *          break
         *       default: break // Ignore unexpected characters
         *    }
         * }
         * return valueStack.peek()
         */

        ResizableArrayStack<Double> valueStack = new ResizableArrayStack<>();
        char nextChar = ' ';
        double result = 0.0;

        for (int i = 0; i < postfix.length(); i++)
        {

            System.out.println("iteration:" + i);
            if (valueStack.isEmpty())
                System.out.println("empty");
            else
                System.out.println(valueStack.peek());

            if(postfix.charAt(i) != ' ')
                nextChar = postfix.charAt(i);
            else
                break;
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
