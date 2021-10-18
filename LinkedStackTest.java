import java.util.*;

/**
 * A class containing test methods for the LinkedStack implementation
 */
public class LinkedStackTest
{
    public static void main(String[] args)
    {
        // infix expression
        String infixExpression = "a*b/(c-a)+d*e";
        System.out.println("infix expression is: a*b/(c-a)+d*e");
        System.out.println("postfix expression is: " + convertToPostfix(infixExpression));
    }

    /**
     * A class that converts an infix string to a postfix string
     * @param infix the infix expression
     * @return the postfix expression as a string
     */
    public static String convertToPostfix(String infix)
    {
        //converts current contents to postfix notation from infix
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        String postfix = "";

        //iterate through the infix expression
        while (infix != null && !infix.equals(""))
        {
            char nextCharacter = infix.charAt(0);
            infix = infix.substring(1);

            switch (nextCharacter)
            {
                case 'a': case 'b': case 'c': case 'd': case 'e':
                    postfix += nextCharacter;
                    break;
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-': case '*': case '/':
                    while(!operatorStack.isEmpty()
                            && precedence(nextCharacter) <= precedence(operatorStack.peek())) //check precedence //original: && ((nextCharacter <= operatorStack.peek() && nextCharacter != '*') || (nextCharacter == '*' && (operatorStack.peek() == '*' || operatorStack.peek() == '/')))
                    {
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case '(':
                    operatorStack.push(nextCharacter);
                    break;
                case ')': //stack is not empty if infix expression is valid
                    char topOperator = operatorStack.pop();
                    while(topOperator != '(')
                    {
                        postfix += topOperator;
                        if(!operatorStack.isEmpty())
                            topOperator = operatorStack.pop();
                        else
                            break;
                    }                       
                    break;
                default: break; //ignore unexpected characters
            }
        }
        while(!operatorStack.isEmpty())
        {
            char topOperator = operatorStack.pop();
            postfix = postfix + topOperator;
        }
        return postfix;
    }

    /**
     * a method that assigns a precedence value to an operator
     * @param c the operator to evaluate
     * @return -1 if parenthesis, 2 if power, 1 if multiply or divide
     */
    public static int precedence(char c)
    {
        if (c == '(' || c == ')')
            return -1;
        if (c == '^')
            return 2;
        if (c == '*' || c == '/')
            return 1;
        return 0;
    }
}
