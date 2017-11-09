import java.util.Iterator;

/**
 * Dequeue. A double-ended queue or deque (pronounced "deck") 
 * is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the 
 * back of the data structure. Create a generic data type
 * Deque that implements the following API
 * 
 * Corner cases: Throw the specified exception for the following corner cases:
 * Throw a java.lang.IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument. 
 * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty. 
 * Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return. 
 * Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator. 
 * @author Lei
 *
 */
public class Deque<Item> implements Iterable<Item>  {
	
	// use a sentinel node
	private Node virHead;
	
	// use a sentinel node
	private Node virTail;
	
	// define the front node
    private Node front;	  
    // define the end node
    private Node end;
		
    private int size;
    
    /**
	 * Define a linkedlist node class, since we 
	 * are going to use linkedlist to implement 
	 * the Deque
	 *
	 * @author Lei
	 *
	 */
	private class Node {
		Item val;
		Node next;
		Node pre;
		public Node(Item value) {
			this.val= value;
		}
	}

	/**
	 * construct an empty deque
	 */
	public Deque() {
		size=0;
		virHead= new Node(null);
		virTail= new Node(null);
		virHead.next = virTail;
		front=virHead;
		end=virTail;
	}
 
	/**
	 * Is the deque empty?
	 * @return
	 */
	public boolean isEmpty() {
		return this.size==0;
	}
	
	/**
	 * return the number of items on the deque
	 * @return
	 */
	public int size() {
		return this.size;
	}
	
	
	/**
	 * add the item to the front
	 * @param item
	 */
	public void addFirst(Item item) {
		if (item==null)
			throw new java.lang.IllegalArgumentException();
		Node curNode =  new Node(item);
		curNode.next = front;
		if(front!=null){
			front.pre = curNode;
		}
		front = curNode;
		// if it is the first added node,
		// we also need to update the end node
		if (end==null)
			end = curNode;
		size++;
	}
	
	//add the item to the end
	public void addLast(Item item){
		if (item==null)
			throw new java.lang.IllegalArgumentException();
		Node curNode =  new Node(item);
		// if it is NOT the first added node,
		// means we already had a end node
		if (end != null){
			end.next = curNode;
			curNode.pre = end;
		}else{
			//if it is the first added node, then we also need to 
			// update the front node
			front=curNode;
		}
		end=curNode;
		size++;
	}
	
	// remove and return the item from the front
	public Item removeFirst(){
		if (this.isEmpty())
			throw new java.util.NoSuchElementException();
		Item retItem=  front.val;
		// if there is only one node
		if(front==end)
			end=front.next;
		front.next.pre= null;
		front=front.next;
		size--;
		return retItem;
	}
	
	// remove and return the item from the end
	public Item removeLast() {
		if (this.isEmpty())
			throw new java.util.NoSuchElementException();
		Item retItem=  end.val;
		end = end.pre;
		size--;
		return retItem;
	}


	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item> {
		private Node current =front;
		@Override
		public boolean hasNext() {
			if (current==null)
				return false;
			else 
				return true;
		}

		@Override
		public Item next() {
			// Throw a java.util.NoSuchElementException if the client calls the next() method 
			// in the iterator when there are no more items to return. 
			if (current==null)
				throw new java.util.NoSuchElementException();
			Item retItem = current.val;
			current= current.next;
			return retItem;
		}
		//* Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		
	}
	
	  public static void main(String[] args) {
		 /* Deque<String> testDeque = new Deque<>();
		  testDeque.addLast("A");
		  testDeque.addLast("B");
		  testDeque.addLast("C");
		  testDeque.addLast("D");
		  
		  Iterator<String> ite= testDeque.iterator();
		  while (ite.hasNext()){
			  System.out.println(ite.next());
		  }*/
		  
		  Deque<Integer> deque = new Deque<Integer>();
			         deque.isEmpty();       
			         deque.isEmpty();       
			         deque.addFirst(2);
			         deque.removeFirst();
		  
	  }
}
