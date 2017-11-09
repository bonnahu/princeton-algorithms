import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Randomized queue. A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen uniformly at random from items 
 * in the data structure. Create a generic data type RandomizedQueue that 
 * implements the following API: 
public class RandomizedQueue<Item> implements Iterable<Item> {
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the randomized queue empty?
   public int size()                        // return the number of items on the randomized queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // remove and return a random item
   public Item sample()                     // return a random item (but do not remove it)
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing (optional)
}


Iterator:Each iterator must return the items in uniformly random order. 
The order of two or more iterators to the same randomized queue must be mutually
independent; each iterator must maintain its own random order. 

Corner cases-Throw the specified exception for the following corner cases:
Throw a java.lang.IllegalArgumentException if the client calls enqueue() with a null argument. 
Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue is empty. 
Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return. 
Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator. 
 * @author Lei
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private int size;
	
	private Item[] internalArr;
	
	// construct an empty randomized queue
	public RandomizedQueue(){
		internalArr =  (Item[])new Object[10]; 
		size=0;
	}
	
	// add the item
	public void enqueue(Item item) {
		if (null==item)
			throw new java.lang.IllegalArgumentException();
		if (internalArr.length==size) {
			resize(internalArr.length*2);
		}	
		internalArr[size]=item;
		size++;
	}
	
	// remove and return a random item
	public Item dequeue() {
		if(isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int randomizedIndex = StdRandom.uniform(size);
		Item retIem= internalArr[randomizedIndex];
		for(int i=randomizedIndex; i < size; i++) {
			internalArr[i]=internalArr[i+1];
		}
		size--;
		if (internalArr.length/4==size) {
			resize(internalArr.length/2);
		}	
		return retIem;
	}
	// return a random item (but do not remove it)
	public Item sample() {
		if(isEmpty()) {
			throw new java.util.NoSuchElementException();
		} 
		int randomizedIndex = StdRandom.uniform(size);
		return internalArr[randomizedIndex];
	}
	    
	
	// is the randomized queue empty?
	public boolean isEmpty(){
		return size==0;
	}
	
	// return the number of items on the randomized queue
	public int size(){
		return size;
	}
    
	// return an independent iterator over items in random order
	@Override
	public Iterator<Item> iterator() {
		return new RandomOrderIterator();
	}
	
	
	private class RandomOrderIterator implements Iterator<Item> {
        //define its own index
		private int index;
		
		private Item[] randomizedinternalArr;
		
		public RandomOrderIterator() {
			index=0;
			// create a copy of the internalArr and shuffle the array
			// to generate the random order
			Item[] randomizedinternalArr =  (Item[])new Object[size];
			for(int i=0; i<size; i++) {
				randomizedinternalArr[i]= internalArr[i];
			}
			//shuffle
			StdRandom.shuffle(randomizedinternalArr);
		}
		
		
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (size>0)
				return true;
			else
				return false;
		}

		@Override
		public Item next() {
			//Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
			if (index==size)
				throw new java.util.NoSuchElementException();
			return randomizedinternalArr[index++];
		}
		
		
		public void remove() {
			//Throw a java.lang.UnsupportedOperationException if the client calls the remove() method in the iterator.
			throw new java.lang.UnsupportedOperationException();
		}
	}
	
	private void resize(int newCapacity) {
		Item[] tmpArr = (Item[])new Object[newCapacity]; 
		for(int i=0; i < newCapacity; i++) {
			tmpArr[i] =internalArr[i];
		}
		internalArr = tmpArr;
	}
	
	public static void main(String[] args) {
		RandomizedQueue<String> testQueue = new RandomizedQueue<>(); 
		testQueue.enqueue("A");
		testQueue.enqueue("B");
		testQueue.enqueue("C");
		testQueue.enqueue("D");
		testQueue.enqueue("E");
	
		System.out.println(testQueue.dequeue());
		System.out.println(testQueue.dequeue());
		System.out.println(testQueue.dequeue());
		
	}
}
