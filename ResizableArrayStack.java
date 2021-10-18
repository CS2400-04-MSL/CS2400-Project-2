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

   /**
    * Default Constructor
    */
   public ResizableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor

   /**
    * creates an array with a specified capacity
    * @param initialCapacity
    */
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

   /** Removes all entries from this stack. */
   public void clear()
   {
      for (int i = 0; i < topIndex; i++)
      {
         stack[i] = null;
      }
      topIndex = 0;
   }

   /** Adds a new entry to the top of this stack.
    @param newEntry  An object to be added to the stack. */
   public void push(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      stack[topIndex + 1] = newEntry;
      topIndex++;
   } // end push

   /**
    * Doubles the size of the array if the array is full
    */
   private void ensureCapacity()
   {
      if (topIndex >= stack.length - 1) // If array is full, double its size
      {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      } // end if
   } // end ensureCapacity

   /** Retrieves this stack's top entry.
    @return  The object at the top of the stack.
    @throws  EmptyStackException if the stack is empty. */
   public T peek()
   {
      checkIntegrity();
      if (isEmpty())
         throw new EmptyStackException();
      else
         return stack[topIndex];
   } // end peek

   /** Removes and returns this stack's top entry.
    @return  The object at the top of the stack.
    @throws  EmptyStackException if the stack is empty before the operation. */
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

   /** Detects whether this stack is empty.
    @return  True if the stack is empty. */
   public boolean isEmpty()
   {
      return topIndex < 0;
   } // end isEmpty

   /**
    * checks whether the MAX CAPACITY has been exceeded.
    * @param cap size of the array
    */
   public void checkCapacity(int cap)
   {
      if (cap > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose " +
                 "capacity exceeds allowed maximum of " + MAX_CAPACITY);
   }

   /**
    * Checks if the array is still valid
    */
   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("ResizeableArrayStack object is corrupt.");
   }
} // end ArrayStack
