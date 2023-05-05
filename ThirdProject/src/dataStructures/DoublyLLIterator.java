package dataStructures;

import java.io.Serializable;

public class DoublyLLIterator<E> implements TwoWayIterator<E>, Serializable{
	
	private static final long serialVersionUID = 1L;

	// Node with the first element in the iteration.
		private DListNode<E> firstNode;
		// Node with the last element in the iteration.
		private DListNode<E> lastNode;
		// Node with the next element in the iteration.
		private DListNode<E> nextToReturn;
		// Node with the previous element in the iteration.
		private DListNode<E> prevToReturn;

		

		public DoublyLLIterator(DListNode<E> head, DListNode<E> tail) {
			firstNode=head;
			lastNode=tail;
			rewind();
		}

		

		@Override
		public boolean hasNext() {
			return nextToReturn!=null;
		}

		@Override
		public E next() throws NoSuchElementException {
			if ( !this.hasNext() )
				throw new NoSuchElementException("No more elements.");
			E element = nextToReturn.getElement();
			nextToReturn = nextToReturn.next;
			return element;
		}

		@Override
		public void rewind() {
			nextToReturn = firstNode;
			prevToReturn = null;
		}

		@Override
		public boolean hasPrevious() {
			return prevToReturn!=null;
		}

		@Override
		public E previous() throws NoSuchElementException {
			if ( !this.hasPrevious() )
				throw new NoSuchElementException("No more elements.");
			E element = prevToReturn.getElement();
			prevToReturn = prevToReturn.previous;
			return element;
		}

		@Override
		public void fullForward() {
			prevToReturn = lastNode;
			nextToReturn = null;
		}


}
