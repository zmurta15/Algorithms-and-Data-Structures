package dataStructures;

import java.io.Serializable;

public class MapWithJavaClass<K,V> implements Map<K,V>, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected java.util.Map<K,V> elementos;
	//protected int capPrevista;

	public MapWithJavaClass() {
		elementos = new java.util.HashMap<K,V>();
		//capPrevista =prevusers;
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
		else {
			return  (Iterator<K>) elementos.keySet().iterator();
		}
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<V> values() throws NoElementException {
		if(isEmpty())
			throw new NoElementException();
		else {
			return (Iterator<V>) elementos.values().iterator();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		if(isEmpty()) 
			throw new NoElementException();
		else {
			return (Iterator<Entry<K, V>>) elementos.entrySet().iterator();
		}
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

}
