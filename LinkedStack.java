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
  
//  < Implementations of the stack operations go here. >
//  . . .

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

   public void clear()
   {
      topNode = null;
   }

   public T peek()
   {
      if (topNode != null)
         return topNode.getData();
      return null;
   }

   public boolean isEmpty()
   {
      if (topNode == null)
         return true;
      return false;
   }

   public T pop()
   {
      if (topNode != null)
      {
         Node toPop = new Node(topNode.getData(), topNode.getNextNode());
         topNode = topNode.getNextNode();
         return toPop.getData();
      }
      return null;
   }

   public void push(T toPush)
   {
      Node pushed = new Node(toPush, topNode);
      topNode = pushed;
   }
} // end LinkedStack
