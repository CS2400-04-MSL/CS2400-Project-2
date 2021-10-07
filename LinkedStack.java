import java.util.EmptyStackException;
/**
    A class of stacks whose entries are stored in a chain of nodes.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain
  
   public LinkedStack()
   {
      topNode = null;
   } // end default constructor

	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node

   public T peek()
   {
      if (isEmpty())
         throw new EmptyStackException();
      else
         return topNode.getData();
   } // end peek

   public T pop()
   {
      T top = peek();  // Might throw EmptyStackException

      // Assertion: topNode != null
      topNode = topNode.getNextNode();

      return top;
   } // end pop

   public void push(T newEntry)
   {
      Node newNode = new Node(newEntry, topNode);
      topNode = newNode;
   } // end push

   public boolean isEmpty()
   {
      return topNode == null;
   } // end isEmpty

   public void clear()
   {
      topNode = null;
   } // end clear

   public String convertToPostfix(LinkedStack<T> infix)
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
               while(!operatorStack.isEmpty()/*&& nextCharacter.precedence <= operatorStack.peek().precedence*/)
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
} // end LinkedStack
