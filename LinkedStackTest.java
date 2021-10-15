
import java.util.*;

public class LinkedStackTest
{
    public static void main(String[] args)
    {
        // infix expression
        String infixExpression = "a*b/(c-a)+d*e";

        // create a LinkedStack and populate it with the infixExpression
        LinkedStack<Character> infix = new LinkedStack<Character>();
        for (int i = 0; i < infixExpression.length(); i++)
            infix.push(infixExpression.charAt(i));

        System.out.println("infix expression is: a*b/(c-a)+d*e");

        /** convertToPostfix() method */
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        String postfix = "";
        while (infix != null && !infix.isEmpty())
        {
            char nextCharacter = (char)infix.peek();
            switch (nextCharacter)
            {
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-': case '*': case '/':
                while(!operatorStack.isEmpty()
                        && ((nextCharacter <= operatorStack.peek() && nextCharacter != '*') || (nextCharacter == '*' && (operatorStack.peek() == '*' || operatorStack.peek() == '/')))) //check precedence
                {
                    postfix = postfix + operatorStack.peek();
                    operatorStack.pop();
                }
                operatorStack.push(nextCharacter);
                break;
                case ')': //stack is not empty if infix expression is valid
                    char topOperator = operatorStack.pop();
                    while(topOperator != '(')
                    {
                        postfix = postfix + topOperator;
                        topOperator = operatorStack.pop();
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

        System.out.println("postfix expression is: " + postfix);


        //String postFix = convertToPostfix(stack1);
        //System.out.println("over" + postFix);

    }



    /**
    public static <T> String convertToPostfix(LinkedStack<T> infix)
    {
        //converts current contents to postfix notation from infix
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        String postfix = "";
        while (infix != null && !infix.isEmpty())
        {
            char nextCharacter = (char)infix.peek();
            switch (nextCharacter)
            {
                case '^':
                    operatorStack.push(nextCharacter);
                    break;
                case '+': case '-': case '*': case '/':
                while(!operatorStack.isEmpty()
                        && ((nextCharacter <= operatorStack.peek() && nextCharacter != '*') || (nextCharacter == '*' && (operatorStack.peek() == '*' || operatorStack.peek() == '/')))) //check precedence
                {
                    postfix = postfix + operatorStack.peek();
                    operatorStack.pop();
                }
                operatorStack.push(nextCharacter);
                break;
                case ')': //stack is not empty if infix expression is valid
                    char topOperator = operatorStack.pop();
                    while(topOperator != '(')
                    {
                        postfix = postfix + topOperator;
                        topOperator = operatorStack.pop();
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
     */

}
