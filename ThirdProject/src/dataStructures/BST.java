package dataStructures;

import java.io.Serializable;

public class BST<K extends Comparable<K>,V> implements SortedMap<K,V>, Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static class BSTNode<E> implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected BSTNode<E> parent;
		protected BSTNode<E> left;
		protected BSTNode<E> right;
		protected E element;

		
		BSTNode(E elem,BSTNode<E> parent,BSTNode<E> left,BSTNode<E> right){
			this.parent=parent;
			this.left=left;
			this.right=right;
			element=elem;
		}
		BSTNode(E elem){
			this(elem,null,null,null);
		}
		
		E getElement() {
			return element;
		}
		//...
		BSTNode<E> getLeft() {
			return left;
		}
		
		BSTNode<E> getRight() {
			return right;
		}

		BSTNode<E> getParent() {
			return parent;
		}
		boolean isInternal() {
			if(getLeft() != null || getRight() != null)
				return true;
			else
				return false;
		}
		
	}

	//The root
	protected BSTNode<Entry<K,V>> root;
		
	//Number of elements
	protected int currentSize;
	
	public BST() {
		root = null;
		currentSize = 0;
	}
	
	@Override
	public Iterator<Entry<K,V>> iterator() {
		return new BSTOrderIterator<K,V>(root);
	}
	
	
	@Override
	/*
	 * Metodo nao utilizado
	 */
	public Iterator<K> keys() throws NoElementException {
		return null;
	}

	@Override
	/*
	 * Metodo nao utilizado
	 */
	public Iterator<V> values() throws NoElementException {
		return null;
	}
	
	protected BSTNode<Entry<K,V>> findNode(BSTNode<Entry<K,V>> n, K key) {
		BSTNode<Entry<K,V>> res=null;
		if (n!=null) {
			int num= n.getElement().getKey().compareTo(key);	
			if (num==0)
				res=n;
			else if (num>0)
				res=findNode(n.getLeft(),key);
			else
				res=findNode(n.getRight(),key);
		}
		return res;
	}
	
	
	protected BSTNode<Entry<K,V>> findFatherNode(K key) {
		return findFatherNode(null,root,key);
	}
	
	protected BSTNode<Entry<K,V>> findFatherNode(BSTNode<Entry<K,V>> p, BSTNode<Entry<K,V>> n, K key) {
		BSTNode<Entry<K,V>> res=p;
		if (n!=null) {
			int num= n.getElement().getKey().compareTo(key);	
			if (num==0)
				res=n.getParent();
			else if (num<0)
				res=findFatherNode(n,n.getLeft(),key);
			else
				res=findFatherNode(n,n.getRight(),key);
		}
		return res;
	}
	
	@Override
	public V find(K key) {
		BSTNode<Entry<K,V>> res=findNode(root,key);
		if (res==null)
			return null;
		return res.getElement().getValue();
	}
	@Override
	public V insert(K key, V value) {
		Entry<K,V> e = new EntryClass<K,V>(key,value);
		if(root == null) {
			root = new BSTNode<Entry<K,V>>(e);
			return value;
		}
		else {
			BSTNode<Entry<K,V>> parent = null;
			BSTNode<Entry<K,V>> current = root;
			//percorremos a arvore ate chegarmos ao no onde queres adicionar um filho
			while(current != null) {
				parent = current;
				int num = current.getElement().getKey().compareTo(key);
				if(num <= 0) 
					current = current.getRight();
				else //if num > 0
					current = current.getLeft();
			}
			//chegamos ao no onde queremos adicionar, o parent e onde queremos adicionar um filho
			int num2 = parent.getElement().getKey().compareTo(key);
			BSTNode<Entry<K,V>> son = new BSTNode<Entry<K,V>>(e);
			if(num2<=0) {
				parent.right = son;
				son.parent = parent;
			}	
			else {
				parent.left = son;
				son.parent = parent;
			}
			currentSize++;
			return value;
		}
	}
	
	
	
	@Override
	public V remove(K key) {
		BSTNode<Entry<K,V>> node = findNode(root, key);
		V valueToReturn = node.getElement().getValue();
		BSTNode<Entry<K,V>> fatherNode = findFatherNode(key);
		if(numChildren(node) == 0) { //ou seja se o no nao tem fihos
			if(node == root) {
				root = null;
			}	
			else if(fatherNode.left == node)
				fatherNode.left = null;
			else if(fatherNode.right == node) 
				fatherNode.right = null;
		}
		//ver o caso da raiz
		else if (numChildren(node) == 1) { //se tiver 1 filho
			if(node == root && node.getLeft() != null) {
				root = node.getLeft();
				root.parent = null;
			}
			else if(node == root && node.getRight() != null) {
				root = node.getRight();
				root.parent = null;
			}
			BSTNode<Entry<K,V>> tempChild = null;
			if(node.getLeft() != null)
				tempChild = node.getLeft();
			else if(node.getRight() != null)
				tempChild = node.getRight();
			if(node == root)
				root = tempChild;
			if(fatherNode != null) {
				if(fatherNode.getLeft() == node) {
					fatherNode.left = tempChild;
				}
				else if(fatherNode.getRight() == node &&fatherNode != null) {
					fatherNode.right = tempChild;
				}
			}
			tempChild.parent = fatherNode;
		}
		else { //se tiver dois filhos
			BSTNode<Entry<K,V>> temp = minNode(node.getRight());
			BSTNode<Entry<K,V>>  aux = temp;
			BSTNode<Entry<K,V>> tempParent = temp.getParent();
			if(node == root) {
				root = temp;
				root.parent= null;
				if(numChildren(temp) == 0) {
					tempParent.left = null;
				}
				else if(numChildren(temp) == 1) {
					tempParent.left = temp.getRight();
					tempParent.getLeft().parent = tempParent;
				}
			}
			else {
				if(fatherNode.getLeft() == node) {
					fatherNode.left = aux;
				}
				else if(fatherNode.getRight() == node) {
					fatherNode.right = aux;
				}
				aux.parent = fatherNode;
				//remove(temp.getElement().getKey()); //para nao ficar com chaves repetidas
				if(numChildren(temp) == 0) {
					tempParent.left = null;
				}
				else if(numChildren(temp) == 1) {
					tempParent.left = temp.getRight();
					tempParent.getLeft().parent = tempParent;
				}
			}
		}
	currentSize--;
	return valueToReturn;
	}
	
	@Override
	public Entry<K, V> minEntry() throws NoElementException {
		if( this.isEmpty())
			throw new NoElementException();
		return this.minNode(root).getElement();
	}
	
	protected BSTNode<Entry<K,V>> minNode(BSTNode<Entry<K,V>> node) {
		if(((BSTNode<Entry<K,V>>) node).getLeft() == null)
			return node;
		return this.minNode(node.getLeft());
	}
	
	
	@Override
	public Entry<K, V> maxEntry() throws NoElementException {
		if ( this.isEmpty() )
			throw new NoElementException();
		return this.maxNode(root).getElement();
	}
	// Precondition: node != null.
	protected BSTNode<Entry<K,V>> maxNode( BSTNode<Entry<K,V>> node ){
		if ( node.getRight() == null )
			return node;
		return this.maxNode(node.getRight());
	}

	@Override
	public boolean isEmpty() {
		return currentSize==0;
	}

	@Override
	public int size() {
		return currentSize;
	}
	
	protected int numChildren(BSTNode<Entry<K,V>> n) {
		int count=0;
		if (n.getLeft()!=null)
			count++;
		if (n.getRight()!=null)
			count++;
		return count;
	}
	

}
