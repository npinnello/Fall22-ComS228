package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//import edu.iastate.cs228.hw3.StoutList.Node;
//import edu.iastate.cs228.hw3.StoutList.NodeInfo;

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 * 
 * @author Nick Pinnello
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
	
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size()
  {
    
    return this.size;
  }
  /**
   * used to link a new node 
   * @param newNode
   * @param oldNode
   */
  private void link(Node newNode, Node oldNode) {
      newNode.next = oldNode;
      newNode.previous = oldNode.previous;

      newNode.previous.next = newNode;

      oldNode.previous = newNode;
  }
  
  @Override
  public boolean add(E item)
  {
	  if (item == null) throw new NullPointerException();

      if (size > 0 && tail.previous.count < nodeSize) {
          tail.previous.addItem(item);
      }
      else {
          Node newNode = new Node();
          newNode.addItem(item);
          link(newNode, tail);
      }

      size++;

      return true;
  }

  @Override
  public void add(int pos, E item)
  {
	  if (pos < 0 || pos > size) {
			throw new IndexOutOfBoundsException();
	  }
	  if (head.next == tail)
			add(item);

		NodeInfo nodeInfo = finder(pos);
		Node temp = nodeInfo.node;
		int offset = nodeInfo.offset;

		// otherwise if off = 0 and one of the following two cases occurs,
		if (offset == 0) {
			
			if (temp.previous.count < nodeSize && temp.previous != head) {
				temp.previous.addItem(item);
				size++;
				return;
			}
			else if (temp == tail) {
				add(item);
				size++;
				return;
			}
		}
		
		if (temp.count < nodeSize) {
			temp.addItem(offset, item);
		}
		
		else {
			Node newNode = new Node();
			int halfPoint = nodeSize / 2;
			int count = 0;
			while (count < halfPoint) {
				newNode.addItem(temp.data[halfPoint]);
				temp.removeItem(halfPoint);
				count++;
			}

			Node oldNode = temp.next;

			temp.next = newNode;
			newNode.previous = temp;
			newNode.next = oldNode;
			oldNode.previous = newNode;

			if (offset <= nodeSize / 2) {
				temp.addItem(offset, item);
			}
			
			if (offset > nodeSize / 2) {
				newNode.addItem((offset - nodeSize / 2), item);
			}

		}
		
		size++;
	
  }

  @Override
  public E remove(int pos)
  {
	  if (pos < 0 || pos > size)
			throw new IndexOutOfBoundsException();
		NodeInfo nodeInfo = finder(pos);
		Node temp = nodeInfo.node;
		int offset = nodeInfo.offset;
		E nodeValue = temp.data[offset];

		if (temp.next == tail && temp.count == 1) {
			Node predecessor = temp.previous;
			predecessor.next = temp.next;
			temp.next.previous = predecessor;
			temp = null;
		}
		else if (temp.next == tail || temp.count > nodeSize / 2) {
			temp.removeItem(offset);
		}
		else {
			temp.removeItem(offset);
			Node succesor = temp.next;
			
			if (succesor.count > nodeSize / 2) {
				temp.addItem(succesor.data[0]);
				succesor.removeItem(0);
			}
			
			else if (succesor.count <= nodeSize / 2) {
				for (int i = 0; i < succesor.count; i++) {
					temp.addItem(succesor.data[i]);
				}
				temp.next = succesor.next;
				succesor.next.previous = temp;
				succesor = null;
			}
		}
		size--;
    return nodeValue;
  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  E[] arr = (E[]) new Comparable[size];
      for (int i = 0; i < arr.length; i++) {
          arr[i] = this.remove(0);
      }
      insertionSort(arr, new myComparator());

      // Add sorted array back to list
      for (int i = 0; i < arr.length; i++) {
          add(arr[i]);
      }
  }
  /**
   * 
   * Comparator class used for sorting with insertionSort method.
   *
   */
  private class myComparator implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		return o1.compareTo(o2);
	}
	  
  }
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  E[] arr = (E[]) new Comparable[size];
      for (int i = 0; i < arr.length; i++) {
          arr[i] = this.remove(0);
      }
      bubbleSort(arr);

      // Add sorted array back to list
      for (int i = 0; i < arr.length; i++) {
          add(arr[i]);
      }
  }
  
  @Override
  public Iterator<E> iterator()
  {
	  return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
	  return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index)
  {
	  return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item)
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
     
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      count++;
      data[offset] = item;
  
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      count--;
    }    
  }
  
  private class NodeInfo {
		public Node node;
		public int offset;

		public NodeInfo(Node node, int offset) {
			this.node = node;
			this.offset = offset;
		}
	}
  /**
   * helper method to find an item
   * @param position of item
   * @return NodeInfo
   */
  private NodeInfo finder(int pos) {
	  int count = 0;
      Node currNode = head;

      while (currNode.next != null && count <= pos) {
          currNode = currNode.next;
          count += currNode.count;
      }

      return new NodeInfo(currNode, pos - (count - currNode.count));
	}
 
  private class StoutListIterator implements ListIterator<E>
  {
	// constants you possibly use ...   
	  final int LAST_PREV = 0;
	  final int LAST_NEXT = 1;
	  
	// instance variables ... 
	  Node curPos;
	  public E[] dataList;
	  int finalAction;
	  int nextIndex;
	  int nextOff;
    /**
     * Default constructor 
     */
    public StoutListIterator()
    {
        nextIndex = 0;
    	curPos = head.next;
		finalAction = -1;
		
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public StoutListIterator(int pos)
    {
    	nextIndex = pos;
        NodeInfo temp = finder(pos);
    	curPos = temp.node;
		finalAction = 0;
		
    }
    
    // Other methods you may want to add or override that could possibly facilitate 
    // other operations, for instance, addition, access to the previous element, etc.
    // 
    // ...
    // 
    
/**
 * returns wheter iterator has next available.
 */
    @Override
    public boolean hasNext()
    {
    	if (nextIndex >= size)
			return false;
		else
			return true;
    }
/**
 * returns the next value and shifts the position by 1.
 */
    @Override
    public E next()
    {
    	if (!hasNext())
			throw new NoSuchElementException();
    	if (nextOff >= curPos.count) {
            curPos = curPos.next;
            nextOff = 0;
        }

        nextIndex++;
        return curPos.data[nextOff++];
    }
/**
 * removes the last element returned from the list by next() or previous()
 * 
 */
    @Override
    public void remove()
    {
    	switch (finalAction) {
        case 0:
            throw new IllegalStateException();
        case -1:
            StoutList.this.remove(nextIndex);
            break;
        case 1:

            StoutList.this.remove(--nextIndex);
            nextOff = StoutList.this.finder(nextIndex).offset;
            break;
    }

    }
    
   
/**
 * returns wheter iterator has previous value or not
 */
	@Override
	public boolean hasPrevious() {
		return nextIndex - 1 >= 0;
	}
/**
 * returns previous available element in list
 */
	@Override
	public E previous() {
		if (!hasPrevious())
			throw new NoSuchElementException();
		if (nextOff < 1) {
            curPos = curPos.previous;
            nextOff = curPos.count;
        }

        nextIndex--;
        finalAction = -1;
        return curPos.data[--nextOff];
	}
/**
 * returns the index of available element
 */
	@Override
	public int nextIndex() {
		return nextIndex;
	}
/**
 * returns index of previous elememnt
 */
	@Override
	public int previousIndex() {
		return nextIndex - 1;
	}
/**
 * replaces the element at the curPos
 */
	@Override
	public void set(E e) {
		if (finalAction == LAST_NEXT) {
			NodeInfo nodeInfo = finder(nextIndex - 1);
			nodeInfo.node.data[nodeInfo.offset] = e;
			dataList[nextIndex - 1] = e;
			
		} else if (finalAction == LAST_PREV) {
			NodeInfo nodeInfo = finder(nextIndex);
			nodeInfo.node.data[nodeInfo.offset] = e;
			dataList[nextIndex] = e;
			}
		else {
			throw new IllegalStateException();
		}
		
	}
/**
 * adds an element to the end of the list
 */
	@Override
	public void add(E e) {
        NodeInfo n = finder(nextIndex);
        
        next();
        size++;
        finalAction = 0;
	}
    
   
  }
  

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  for (int i = 1; i < arr.length; i++) {
          for (int j = i; j > 0; j--) {
              if (comp.compare(arr[j], arr[j - 1]) < 0) {
            	  E temp = arr[j];
            	  arr[j] = arr[i];
            	  arr[i] = temp;
              }
              else {
                  break;
              }
          }
      }
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (arr[j].compareTo(arr[j + 1]) < 0) {
					E temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

  }
 

}