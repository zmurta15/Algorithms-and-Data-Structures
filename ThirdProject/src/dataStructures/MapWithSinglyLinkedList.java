package dataStructures;

import java.io.Serializable;

public class MapWithSinglyLinkedList<K, V> implements Map<K, V> , Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Entry<K,V>> elementos;
	
	public MapWithSinglyLinkedList () {
		elementos = new SinglyLinkedList<Entry <K,V>>();
	}
	@Override
	public boolean isEmpty() {
		return elementos.isEmpty();
	}

	@Override
	public int size() {
		return elementos.size();
	}

	@Override
	public Iterator<K> keys() throws NoElementException {
		List<K> tmp = new SinglyLinkedList<K>();
		int i = 0;
		if (this.isEmpty()) {
			throw new NoElementException();
		} else {
			Iterator <Entry<K,V>> it = elementos.iterator(); 
			while (it.hasNext()) {
				Entry<K,V> e = it.next();
				tmp.add(i, e.getKey());
				i++;
			}
		}
		return tmp.iterator();
	}

	@Override
	public Iterator<V> values() throws NoElementException {
		List<V> tmp = new SinglyLinkedList<V>();
		int i = 0;
		if (this.isEmpty()) {
			throw new NoElementException();
		} else {
			Iterator <Entry<K,V>> it = elementos.iterator(); 
			while (it.hasNext()) {
				Entry<K,V> e = it.next();
				tmp.add(i, e.getValue());
				i++;
			}
		}
		return tmp.iterator();
	}

	@Override
	public V find(K key) {
		int i = 0;
		boolean found = false;
		V tmp = null;
		while (i < elementos.size() && !found) {
			if (elementos.get(i).getKey().equals(key)) {
				found = true;
			} else {
				i++;
			}
			if (found) {
				tmp = elementos.get(i).getValue();
			}
		}
		return tmp;
	}

	@Override
	public V insert(K key, V value) {
		Entry<K,V> e = new EntryClass<K,V>(key, value);
		elementos.addLast(e);
		return value;
	}

	@Override
	public V remove(K key) {
		boolean found =false;
		int i = 0;
		V tmp = null;
		while (i < this.size() && !found) {
			if (elementos.get(i).getKey().equals(key)) {
				found = true;
			} else {
				i++;
			}
			if (found) {
				tmp = elementos.get(i).getValue();
				elementos.remove(i);
			}
		}
		return tmp;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		if (elementos.isEmpty()) {
			throw new NoElementException();
		}
		return elementos.iterator();
	}

}
