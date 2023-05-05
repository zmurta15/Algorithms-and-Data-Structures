package dataStructures;

import java.io.Serializable;

import dataStructures.BST.BSTNode;

public class BSTOrderIterator<K,V> implements Iterator<Entry<K,V>>, Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BSTNode<Entry<K,V>> root;
	private Stack<BSTNode<Entry<K,V>>> s;

	public BSTOrderIterator(BSTNode<Entry<K, V>> root) {
		this.root = root;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return (!s.isEmpty());
	}

	@Override
	public Entry<K,V> next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		BSTNode<Entry<K,V>> node = s.pop();
		Entry<K,V> entry = node.getElement();
		if(node.getRight() != null)
			pushMinimum(node.getRight());
		
		return entry;
	}

	@Override
	public void rewind() {
		s = new StackInList<BSTNode<Entry<K,V>>>();
		pushMinimum(root);
	}

	private void pushMinimum(BSTNode<Entry<K,V>> node) {
		while (node != null) {
			s.push(node);
			node = node.getLeft();
		}
	}
}
