package dataStructures;
import java.io.Serializable;

public class AdvancedBST <K extends Comparable<K>, V> extends BST<K,V> implements Serializable {
	
	private static final long serialVersionUID = 1L;

		// metodos comuns a arvores binarias de pesquisa avancada 
		// Operacoes basicas para trocar a forma da arvore tratando
		// de reduzir a sua altura
		
		 /**
	     * Performs a single left rotation rooted at Y node.
	     * Node X was a  left  child  of Y before the  rotation,  
	     * then Y becomes the right child of X after the rotation.
	     * @param Y - root of the rotation
	     * @pre: Y has a left child
	     */
	    protected void rotateLeft( BSTNode<Entry<K,V>> Y){
	        //  a single rotation modifies a constant number of parent-child relationships, 
	    	// it can be implemented in O(1)time
	    	BSTNode<Entry<K,V>> parent = Y.getParent(); //pai de y
	    	BSTNode<Entry<K,V>> B = Y.getRight(); //filho direito de y
	    	if(Y == root) {
	    		root = B;
	    	}
	    	Y.right = B.getLeft(); // o filho direito de Y passa a ser o filho esquerdo de B
	    	if(B.getLeft() != null)
	    		B.getLeft().parent = Y; // o pai do filho esquerdo de B passa a ser o Y
	    	B.left = Y; //o filho esquerdo de B passa a ser o Y
	    	Y.parent = B;
	    	B.parent = parent;
	    	if (parent != null) {
	    		if(parent.getLeft() == Y)
	    			parent.left = B;
	    		else
	    			parent.right = B;
	    	}
	    	
	    }
	    
	    /**
	     * Performs a single right rotation rooted at Y node.
	     * Node X was a  right  child  of Y before the  rotation,  
	     * then Y becomes the left child of X after the rotation.
	     * @param Y - root of the rotation
	     * @pre: Y has a right child
	     */
	    protected void rotateRight( BSTNode<Entry<K,V>> Y){
	        //  a single rotation modifies a constant number of parent-child relationships, 
	    	// it can be implemented in O(1)time
	    	BSTNode<Entry<K,V>> parent = Y.getParent(); //pai de Y
	    	BSTNode<Entry<K,V>> B = Y.getLeft(); //filho esquerdo de Y
	    	if(Y == root) {
	    		root = B;
	    	}
	    	Y.left = B.getRight(); //o filho esquerdo do Y passa a ser o filho direito do B
	    	if(B.getRight() != null) 
	    		B.getRight().parent = Y; // o pai do filho direito de B passa a ser o Y 
	    	B.right = Y; //o filho direito de B passa a ser o Y
	    	Y.parent = B; // o pai do Y passa a ser o B
	    	B.parent = parent; //o pai do B passa a ser o pai guardado inicialmente
	    	if (parent != null) {
	    		if(parent.getLeft() == Y)
	    			parent.left = B;
	    		else
	    			parent.right = B;
	    	}
	    }
	    
	   /** 
	   * Performs a tri-node restructuring (a single or double rotation rooted at X node).
	   * Assumes the nodes are in one of following configurations:
	   *
	   * @param X - root of the rotation
	   * <pre>
	   *          z=c       z=c        z=a         z=a
	   *         /  \      /  \       /  \        /  \
	   *       y=b  t4   y=a  t4    t1  y=c     t1  y=b
	   *      /  \      /  \           /  \         /  \
	   *    x=a  t3    t1 x=b        x=b  t4       t2 x=c
	   *   /  \          /  \       /  \             /  \
	   *  t1  t2        t2  t3     t2  t3           t3  t4
	   * </pre>
	   * @return the new root of the restructured subtree
	   */
	    protected BSTNode<Entry<K,V>> restructure (BSTNode<Entry<K,V>> x) {
	    	// the modification of a tree T caused by a trinode restructuring operation
	    	// can be implemented through case analysis either as a single rotation or as a double rotation.
	    	// The double rotation arises when position x has the middle of the three relevant keys
	    	// and is first rotated above its parent Y, and then above what was originally its grandparent Z. 
	    	// In any of the cases, the trinode restructuring is completed with O(1)running time
	    	BSTNode<Entry<K,V>> y = x.getParent(); //pai do x
	    	BSTNode<Entry<K,V>> z = y.getParent(); //pai do y
	    	BSTNode<Entry<K,V>> subRoot = null;
	    	if(z.getRight() == y && y.getRight() == x) { //se for o 4 caso e so uma simples left rotation
	    		rotateLeft(z);
	    		subRoot = y;
	    	}
	    	else if(z.getLeft() == y && y.getLeft() == x) { // se for o 1 caso e so uma simples right rotation
	    		rotateRight(z);
	    		subRoot = y;
	    	}
	    	else if (z.getLeft() == y && y.getRight() == x) { //se for o 2 caso e rotacao dupla: esquerda + direita, left right case
	    		rotateLeft(y); //primeiro rotacao esquerda com o no y
	    		rotateRight(z); //depois rotacao direita com o no o z
	    		subRoot = x;
	    	}
	    	else if(z.getRight() == y && y.getLeft()==x) { //se for o 3 caso e rotacao dupla: direita + esquerda, right left case
	    		rotateRight(y); //primeiro rotacao direita com o no y
	    		rotateLeft(z); // depois rotacao esquerda com o no z
	    		subRoot = x;
	    	}
	    	
	    	return subRoot;
	    }
	}

