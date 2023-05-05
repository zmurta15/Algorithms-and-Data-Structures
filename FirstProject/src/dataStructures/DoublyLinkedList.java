package dataStructures;

public class DoublyLinkedList<E> implements TwoWayList<E>  {
	// Node at the head of the list.
	protected DListNode<E> head;

	// Node at the tail of the list.
	protected DListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;
	
	public DoublyLinkedList( ){
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return currentSize ==0;
	}

	@Override
	public int size() {
		return currentSize;
	}
	

	@Override
	public TwoWayIterator<E> iterator() throws NoElementException {
		if (currentSize==0) throw new NoElementException("List is empty.");
		return new DoublyLLIterator<E>(head,tail);
	}
	

	@Override
	public int find(E element) {
		int pos=0;
		DListNode<E> auxNo;
		boolean found=false;
		auxNo = head;
		while(auxNo != null) {
			if(auxNo.getElement().equals(element)) {
				found = true;
			}
			else {
				auxNo = auxNo.getNext();
				pos++;
			}
		}
		if(found) {
			return pos;
		}
		return -1;
	}
	
	@Override
	public E getFirst() throws NoElementException {
		if (currentSize==0) throw new NoElementException("No such element.");
		return getNode(0).getElement();
	}
	
	@Override
	public E getLast() throws NoElementException {
		if (currentSize==0) throw new NoElementException("No such element.");
		return getNode(currentSize-1).getElement();
	}
	
	@Override
	public E get(int position) throws InvalidPositionException {
		if (position<0 || position>=currentSize) 
			throw new InvalidPositionException("Invalid position.");
		return getNode(position).getElement();
	}
	
	@Override
	public void addFirst(E element) {
		DListNode<E> tmp = new DListNode<E>(element, null, head);
		if(!isEmpty()) {
			head.setPrevious(tmp);
		}
		head = tmp;
		if(isEmpty()) {
			tail = tmp;
		}
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		DListNode<E> tmp = new DListNode<E>(element, tail, null);
		if(!isEmpty()) {
			tail.setNext(tmp);
		}
		tail = tmp;
		if (isEmpty()) {
			head = tmp;
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
	
	
	private void addMiddle(int position, E element) {
		DListNode<E> aux=getNode(position);
		DListNode<E> current = head;
		while ( current != null && !current.equals(aux.getPrevious()))
			current = current.getNext();
		DListNode<E> tmp = new DListNode<E>(element, current, aux);
		current.setNext(tmp);
		aux.setPrevious(tmp);
		currentSize++;
	}

	private E removeMiddle(int position) {
		DListNode<E> aux=getNode(position);
		DListNode<E> current = head;
		while( current != null && !current.equals(aux.getPrevious()))
			current = current.next;
		current.setNext(aux.getNext());
		aux.getNext().setPrevious(current);
		currentSize--;
		return aux.getElement();
	}

	private DListNode<E> getNode(int position){
		DListNode<E> aux=head;
		for(int i=1;i<=position;i++)
			aux=aux.getNext();
		return aux;
	}
	@Override
	public E removeFirst() throws NoElementException {
		if (currentSize==0) 
			throw new NoElementException("No such element.");
		DListNode<E> tmp =head;
		if(head.getNext() == null) {
			currentSize--;
			tail = null;
		}
		else {
			head = head.getNext();
			head.setPrevious(null);
			currentSize--;
		}
		return tmp.getElement();
	}


	@Override
	public E removeLast() throws NoElementException {
		if (currentSize==0) 
			throw new NoElementException("No such element.");
		DListNode<E> tmp =tail;
		tail = tail.getPrevious();
		tail.setNext(null);
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


}
