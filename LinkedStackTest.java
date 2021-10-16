
import java.util.*;

public class LinkedStackTest
{
    public static void main(String[] args)
    {
        // infix expression
        String infixExpression = "a*b/(c-a)+d*e";
        System.out.println("infix expression is: a*b/(c-a)+d*e");
        System.out.println("postfix expression is: " + convertToPostfix(infixExpression));
    }


    public static String convertToPostfix(String infix)
    {
        //converts current contents to postfix notation from infix
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        String postfix = "";


        //operatorStack.push();

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
