package dataStructures;

import java.io.Serializable;

public class StackInList<E> implements Stack<E>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Memory of the stack: an array.
	protected List<E> elements;
		
	public StackInList() {
		elements=new SinglyLinkedList<E>();
	}
	
	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public E top() throws NoElementException {
		if (isEmpty())
			throw new NoElementException("Stack is empty.");
		return elements.getFirst();
	}

	@Override
	public void push(E element) {
		elements.addFirst(element);
	}

	@Override
	public E pop() throws NoElementException {
		if (isEmpty())
			throw new NoElementException("Stack is empty.");
		return elements.removeFirst();
	}

}
