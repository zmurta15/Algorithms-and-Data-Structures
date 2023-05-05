package dataStructures;

import java.io.Serializable;

public class SinglyLLIterator<E> implements Iterator<E>, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private SListNode<E> firstNode;
	private SListNode<E> nextToReturn;
	
	 public SinglyLLIterator(SListNode<E> head) {
		firstNode = head;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return nextToReturn != null;
	}

	@Override
	public E next() throws NoSuchElementException {
		if( !this.hasNext())
			throw new NoSuchElementException("No more elements.");
		E element = nextToReturn.getElement();
		nextToReturn = nextToReturn.next;
		return element;
	}

	@Override
	public void rewind() {
		nextToReturn = firstNode;

	}

}
