package dataStructures;

import java.io.Serializable;


public class AVL <K extends Comparable<K>,V> extends AdvancedBST<K,V> implements SortedMap<K,V>, Serializable{
	
	private static final long serialVersionUID = 1L;
	
static class AVLNode<E> extends BSTNode<E> implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// Height of the node
		protected int height;
		
		public AVLNode(E elem) {
			super(elem);
			height=1;
		}
		
		public AVLNode( E element, AVLNode<E> parent,AVLNode<E> left, AVLNode<E> right ){ //char balance, 
			super(element, parent,left, right);
			height= 1 + Math.max(getHeight((AVLNode<E>)left),getHeight((AVLNode<E>)right));
		}
		
		protected int getHeight(AVLNode<E> no) {
			if (no==null)
				return 0;
			return no.getHeight();
		}
		
		public int getHeight() {
			return height;
		}

		public boolean isBalance() {
			int dif= getHeight((AVLNode<E>)left)-getHeight((AVLNode<E>)right);
			return dif==0 ||dif==-1 ||dif ==1;
		}
		
		public int setHeight() {
			if(left != null && right != null)
				height= 1 + Math.max(getHeight((AVLNode<E>)left),getHeight((AVLNode<E>)right));
			return height;
		}
}
	protected AVL(AVLNode<Entry<K,V>> n) {
			root=n;
	}
	public AVL() {
		this(null);
	}
 /** 
    * Return a child of p with greater height
    */
	protected AVLNode<Entry<K,V>> tallerChild(AVLNode<Entry<K,V>> p)  {
		AVLNode<Entry<K,V>> taller = null;
		AVLNode<Entry<K,V>> l = (AVLNode<Entry<K, V>>) p.getLeft();
		
		AVLNode<Entry<K,V>> r = (AVLNode<Entry<K, V>>) p.getRight();
		if(l == null ) {
			taller = r;
		}
		else if(r == null) {
			taller = l;
		}
		else {
			if(l.getHeight() > r.getHeight()) { 
				taller = l;
			}
			else if(l.getHeight() < r.getHeight()){
				taller = r;
			}
			else {
				return l;
			}
		}
	return taller;
}
	
/**  
 * Rebalance method called by insert and remove.  Traverses the path from 
 * zPos to the root. For each node encountered, we recompute its height 
 * and perform a trinode restructuring if it's unbalanced.
 * the rebalance is completed with O(log n)running time
 */
  protected void rebalance(AVLNode<Entry<K,V>> zPos) {
    if(zPos.isInternal())
       zPos.setHeight();
    // Melhorar se possivel
    while (zPos!=null && zPos.getParent() != null) {  // traverse up the tree towards the root
      zPos = (AVLNode<Entry<K, V>>) zPos.getParent();
      zPos.setHeight();
      if (!zPos.isBalance()) { 
    	  // perform a trinode restructuring at zPos's tallest grandchild
    	  //If yPos (tallerChild(zPos)) denote the child of zPos with greater height. 
    	  //Finally, let xPos be the child of yPos with greater height
    	  AVLNode<Entry<K,V>> xPos =  tallerChild((AVLNode<Entry<K, V>>) tallerChild(zPos));
    	  zPos = (AVLNode<Entry<K, V>>) restructure(xPos); // tri-node restructure (from parent class)
    	  ((AVLNode<Entry<K, V>>) zPos.getLeft()).setHeight();  // recompute heights
    	  ((AVLNode<Entry<K, V>>) zPos.getRight()).setHeight();
    	  zPos.setHeight();
      		}
    	}
  } 
 
  @Override
  public V insert (K key, V value) {
	  Entry<K,V> e = new EntryClass<K,V>(key, value);
	  V valueToReturn = null;
	  if(root == null) {
		  root = new AVLNode<Entry<K,V>>(e);
		  valueToReturn = value;
		  currentSize++;
	  }
	  else {
		  AVLNode<Entry<K,V>> newNode = null;
		  if(find(key) == null) {
			  AVLNode<Entry<K,V>> parent = null;
			  AVLNode<Entry<K,V>> current = (AVLNode<Entry<K, V>>) root;
			  newNode = new AVLNode<Entry<K,V>>(e);
			  while (current != null) {
				  parent = current;
				  int num = current.getElement().getKey().compareTo(key);
					if(num < 0) 
						current = (AVLNode<Entry<K, V>>) current.getRight();
					else if (num > 0)
						current = (AVLNode<Entry<K, V>>) current.getLeft();
			  }
			  int num2 = parent.getElement().getKey().compareTo(key);
			  if(num2 < 0) {
				  parent.right = newNode;
				  newNode.parent = parent;
			  }
			  else if (num2>0) {
				  parent.left = newNode;
				  newNode.parent = parent;
			  }
			  currentSize++;
			  valueToReturn = value;
		  }
		  else if (find(key) != null) {
			  valueToReturn = value;
			  findNode(root, key).getElement().setValue(value);
		  }
		  if(newNode != null)  {//(if find(key)==null 
				rebalance(newNode); // rebalance up from the insertion node
		}
	  }
	  return valueToReturn;
  }
  
  @Override
  public V remove(K key) {
	  if(find(key) == null) {
		  return null;
	  }
	  V valueToReturn = null;
	  AVLNode<Entry<K,V>> fatherNode = (AVLNode<Entry<K, V>>) super.findFatherNode(key);
	  AVLNode<Entry<K,V>> node = (AVLNode<Entry<K, V>>) findNode(root, key);
	  valueToReturn = node.getElement().getValue();
	  if(numChildren(node) == 0) {
		  if(node == root) {
			  root = null;
		  }
		  else if(fatherNode.getLeft() == node)
			  fatherNode.left = null;
		  else if(fatherNode.getRight() == node)
			  fatherNode.right = null;
	  }
	  else if (numChildren(node) == 1) {
		  if(node == root) {
			  if(node.getLeft() != null) {
				  root = node.getLeft();
				  root.parent = null;
			  }
			  else if(node.getRight() != null) {
				  root = node.getRight();
				  root.parent = null;
			  }
			  
			  currentSize--;
			  return valueToReturn;
		  } else {
			  AVLNode<Entry<K,V>> tempChild = null;
			  if (node.getRight() != null) {
				  tempChild = (AVLNode<Entry<K, V>>) node.getRight();
				  if (fatherNode.getLeft() == node) {
					  fatherNode.left = tempChild;
					  fatherNode.getLeft().parent = fatherNode;
				  } else if (fatherNode.getRight() == node) {
					  fatherNode.right = tempChild;
					  fatherNode.getRight().parent = fatherNode; 
				  }  
			  } else if (node.getLeft() != null) {
				  tempChild = (AVLNode<Entry<K, V>>) node.getLeft();
				  if (fatherNode.getLeft() == node) {
					  fatherNode.left = tempChild;
					  fatherNode.getLeft().parent = fatherNode;
				  } else if (fatherNode.getRight() == node) {
					  fatherNode.right = tempChild;
					  fatherNode.getRight().parent = fatherNode; 
				  }  
			  }
		  }  
	  } else if (numChildren(node) == 2) {
		  if (node == root) {
			  AVLNode<Entry<K,V>> minNode = (AVLNode<Entry<K, V>>) minNode(node.getRight());
			  AVLNode<Entry<K,V>> ParentMinNode = (AVLNode<Entry<K, V>>) minNode.parent;
			  root = minNode;
			  root.left = node.getLeft();
			  root.getLeft().parent = root;
			  root.right = node.getRight();
			  root.getRight().parent = root;
			  root.parent = null;
			  if (ParentMinNode == node) {
				 root.right = null; 
			  } else { 
				  if (ParentMinNode.getLeft() == minNode) {
					  ParentMinNode.left = null;
				  } else if (ParentMinNode.getRight() == minNode) {
					  ParentMinNode.right = null;
				  }
			  	}
		  } else {
			 AVLNode<Entry<K, V>> minNode = (AVLNode<Entry<K, V>>) minNode(node.getRight());
			 AVLNode<Entry<K, V>> ParentMinNode = (AVLNode<Entry<K, V>>) minNode.parent;
			 minNode.left = node.getLeft();
			 minNode.getLeft().parent = minNode;
			 minNode.right = node.getRight();
			 minNode.getRight().parent = minNode;
			 minNode.parent = node.getParent();
			 node = minNode;
			 if (ParentMinNode == node) {
				 node.right = null;
			 } else {
				 if (ParentMinNode.getLeft() == minNode) {
					  ParentMinNode.left = null;
				  } else if (ParentMinNode.getRight() == minNode) {
					  ParentMinNode.right = null;
				  }
			 }
		  }
	  }
	  if(node != null) //(if find(key)==null)
			rebalance(node); // rebalance up from the node
	  currentSize--;
	  return valueToReturn;
  }

}
