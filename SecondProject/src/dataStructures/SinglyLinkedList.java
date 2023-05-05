package dataStructures;

import java.io.Serializable;

public class SinglyLinkedList<E> implements List<E>, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// Node at the head of the list.
	protected SListNode<E> head;

	// Node at the tail of the list.
	protected SListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;
		
	
	public SinglyLinkedList() {
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public Iterator<E> iterator() throws NoElementException {
		if (currentSize == 0)
			throw new NoElementException("List is empty.");
		return new SinglyLLIterator<E>(head);
	}

	@Override
	public int find(E element) {
		int pos = 0;
		SListNode<E> auxNo = head;
		boolean found = false;
		while(auxNo != null) {
			if(auxNo.getElement().equals(element)) {
				found = true;
			}
			else {
				auxNo = auxNo.getNext();
				pos++;
			}
		}
		if (found) {
			return pos;
		}
		return -1;
	}

	@Override
	public E getFirst() throws NoElementException {
		if(currentSize ==0) throw new NoElementException("No such element.");
		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		if(currentSize == 0) throw new NoElementException("No such element.");
		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		E e = null;
		if(position < 0 || position >= currentSize)
			throw new InvalidPositionException("Invalid position.");
		else if (position == 0)
			e = getFirst();
		else if( position == currentSize-1) 
			e = getLast();
		else {
			e = getNode(position).getElement();
		}
		return e;
	}

	@Override
	public void addFirst(E element) {
		SListNode<E> tmp = new SListNode<E>(element, head);
		SListNode<E> aux = head;
		if(!isEmpty()) {
			head = tmp;
			head.next = aux;
		} else {
			head = tmp;
			tail = tmp;
		}
		currentSize++;

	}

	@Override
	public void addLast(E element) {
		SListNode<E> tmp = new SListNode<E>(element, null);
		if (!isEmpty()) {
			tail.setNext(tmp);
			tail = tmp;
		} else {
			head = tmp;
			tail = tmp;
		}
		currentSize++;
	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position<0 || position >currentSize) 
			throw new InvalidPositionException("Invalid Position.");
		if (position==0) 
			addFirst(element);
		else if (position==currentSize) 
				addLast(element);
		else {
			addMiddle(position,element);
		}

	}

	@Override
	public E removeFirst() throws NoElementException {
		if (currentSize==0) 
			throw new NoElementException("No such element.");
		SListNode<E> tmp =head;
		if(head.getNext() == null) {
			head = null;
			tail = null;
		}
		else {
			head = head.getNext();
		}
		currentSize--;
		return tmp.getElement();
		
	}

	@Override
	public E removeLast() throws NoElementException {
		if (currentSize==0) 
			throw new NoElementException("No such element.");
		SListNode<E> tmp = getNode (currentSize -2);
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			tmp.next = null;
			tail = tmp;
		}
		currentSize--;
		return tmp.getElement();
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if(position<0 || position>=currentSize)
			throw new InvalidPositionException("Invalid position.");
		if (position==0)
			return removeFirst();
		if (position==currentSize-1)
			return removeLast();
		return removeMiddle(position);
	}
	
	private SListNode<E> getNode(int position){
		SListNode<E> aux=head;
		for(int i=1;i<=position;i++)
			aux=aux.getNext();
		return aux;
	}
	
	private void addMiddle(int position, E element) {
		SListNode<E> aux=getNode(position-1);
		SListNode<E> tmp = new SListNode<E>(element, aux.getNext());
		aux.setNext(tmp);
		currentSize++;
	}
	private E removeMiddle (int position) {
		SListNode<E> aux=getNode(position);
		SListNode<E> tmp = getNode (position -1);
		tmp.setNext(aux.next);
		return aux.getElement();
		
	}
}
