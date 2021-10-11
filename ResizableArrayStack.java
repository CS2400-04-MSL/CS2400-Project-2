import java.util.*;

/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class ResizableArrayStack<T> implements StackInterface<T>
{
   private T[] stack;    // Array of stack entries
   private int topIndex; // Index of top entry
   private boolean integrityOK = false;
   private static final int DEFAULT_CAPACITY = 50;
   private static final int MAX_CAPACITY = 10000;
  
   public ResizableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
   public ResizableArrayStack(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      integrityOK = true;
   } // end constructor

   public void clear()
   {
      for (int i = 0; i < topIndex; i++)
      {
         stack[i] = null;
      }
      topIndex = 0;
   }

   public void push(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      stack[topIndex + 1] = newEntry;
      topIndex++;
   } // end push

   private void ensureCapacity()
   {
      if (topIndex >= stack.length - 1) // If array is full, double its size
      {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      } // end if
   } // end ensureCapacity

   public T peek()
   {
      checkIntegrity();
      if (isEmpty())
         throw new EmptyStackException();
      else
         return stack[topIndex];
   } // end peek

   public T pop()
   {
      checkIntegrity();
      if (isEmpty())
         throw new EmptyStackException();
      else
      {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      } // end if
   } // end pop

   public boolean isEmpty()
   {
      return topIndex < 0;
   } // end isEmpty

   public void checkCapacity(int cap)
   {
      if (cap > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose " +
                 "capacity exceeds allowed maximum of " + MAX_CAPACITY);
   }

   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("ResizeableArrayStack object is corrupt.");
   }

   public double evaluatePostfix(String postfix)
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

      while (!postfix.isEmpty())
      {
         if(postfix.charAt(0) != ' ')
            nextChar = postfix.charAt(0);
         else
            postfix = postfix.substring(1);
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
               result = valueStack.pop() - valueStack.pop();
               valueStack.push(result);
               break;
            case '*': 
               result = valueStack.pop() * valueStack.pop();
               valueStack.push(result);
               break;
            case '/': 
               result = valueStack.pop() / valueStack.pop();
               valueStack.push(result);
               break;
            case '^':
               result = Math.pow(valueStack.pop(), valueStack.pop());
               valueStack.push(result);
               break;
            default: break; //Ignore unexpected characters
         }
      }
      //evaluate postfix result of postfix expression
      result = valueStack.peek();
      return result;
   }
} // end ArrayStack
