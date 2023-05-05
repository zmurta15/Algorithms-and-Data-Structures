package dataStructures;

import java.io.Serializable;

public class SortedMapWithJavaClass<K extends Comparable<K>, V> implements SortedMap<K , V>, Serializable{

	private static final long serialVersionUID = 1L;
	
	protected java.util.SortedMap<K,V> elementos;
	//protected int capPrevista;


	public SortedMapWithJavaClass() {
		elementos = new java.util.TreeMap<K,V>();
		
		//this.capPrevista = capPrevista;
	}
	
	@Override
	public boolean isEmpty() {
		return elementos.isEmpty();
	}

	@Override
	public int size() {
		return elementos.size();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<K> keys() throws NoElementException {
		if(isEmpty())
			throw new NoElementException();
		return (Iterator<K>) elementos.keySet().iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<V> values() throws NoElementException {
		if(isEmpty())
			throw new NoElementException();
		return (Iterator<V>) elementos.values().iterator();
	}

	
	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		if(isEmpty())
			throw new NoElementException();
		return new IteratorEntrySortedMapJavaClass<K, V>(elementos);
	}

	@Override
	public V find(K key) {
		return elementos.get(key);
	}

	@Override
	public V insert(K key, V value) {
		return elementos.put(key, value);
	}

	@Override
	public V remove(K key) {
		return elementos.remove(key);
	}
	

	@Override
	public Entry<K, V> minEntry() throws NoElementException {
		if(isEmpty()) {
			throw new NoElementException();
		}
		Iterator<Entry<K,V>> it = this.iterator();
		return it.next();
		
	}

	@Override
	public Entry<K, V> maxEntry() throws NoElementException {
		if(isEmpty() ) {
			throw new NoElementException();
		}
		Iterator<Entry<K,V>> it = this.iterator();
		Entry<K,V> aux = null;
		while(it.hasNext()) {
			aux = it.next();
		}
		return aux;
	}
	
}


