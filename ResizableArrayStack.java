import java.util.*;;

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
      //check the capacity of the stack
   }

   public void checkIntegrity()
   {
      //check the integrity of the stack
   }

   public double evaluatePostfix()
   {
      double result = 0.0;
      //evaluate postfix result of postfix expression
      return result;
   }
} // end ArrayStack
