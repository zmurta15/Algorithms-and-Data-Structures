package dataStructures;

import java.io.Serializable;

public class SepChainHashTableIterator<K,V> implements Iterator<Entry<K,V>>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<K,V>[] table;
	
	private int current;
	
	private Iterator<Entry<K,V>> it;
	
	public SepChainHashTableIterator(Map<K,V>[] table) {
		this.table = table;
		this.rewind();
	}
	
	public boolean hasNext() {
		if (it == null) 
			return false;
		return it.hasNext();
	}
	
	@Override
	public Entry<K, V> next() {
		 if ( !this.hasNext() )
	            throw new NoSuchElementException();
		Entry<K, V> next = it.next();
		if (!it.hasNext())
			it = this.nextListIterator(++current);
		return next;
	}

	@Override
	public void rewind() {
		it = this.nextListIterator(0);
	}

	public Iterator<Entry<K, V>> nextListIterator(int position) {
		Map<K, V> list;
		for (int i = position; i < table.length; i++) {
			list = table[i];
			if (!list.isEmpty()) {
				current = i;
				return list.iterator();
			}
		}
		return null;
	}
	
}
