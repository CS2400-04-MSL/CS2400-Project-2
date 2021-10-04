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

   public void checkCapacity(int cap)
   {
      if (topIndex >= cap && cap <= MAX_CAPACITY/2)
      {
         //double size of array
      }
      //capacity is fine, or capacity cannot be doubled
   }

   public void clear()
   {
      for (int i = 0; i < topIndex; i++)
      {
         stack[i] = null;
      }
      topIndex = 0;
   }

   public boolean isEmpty()
   {
      if (stack[0] != null)
         return true;
      return false;
   }

   public T peek()
   {
      return stack[topIndex];
   }

   public T pop()
   {
      if (integrityOK)
      {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      }
      return null;
   }

   public void push(T toPush)
   {
      if (integrityOK)
      {
         topIndex++;
         stack[topIndex] = toPush;
      }
   }

   public double evaluatePostfix()
   {
      
      double result = 0.0;
      //evaluate postfix result of postfix expression
      return result;
   }
} // end ArrayStack
