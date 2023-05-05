package dataStructures;
import java.util.SortedMap;


public class IteratorEntrySortedMapJavaClass<K,V> implements Iterator<Entry<K,V>> {
	
	private java.util.Iterator<java.util.SortedMap.Entry<K,V>> it;
	private java.util.SortedMap<K,V> sortedMap;
	
	public IteratorEntrySortedMapJavaClass (SortedMap<K,V> sortedMap) {
		it = sortedMap.entrySet().iterator();
		this.sortedMap = sortedMap;
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if (!it.hasNext()) {
			throw new NoSuchElementException();
		}
		java.util.SortedMap.Entry<K,V> e = it.next();
		Entry<K,V> entry = new EntryClass<K,V>(e.getKey(), e.getValue());
		return entry;
	}

	@Override
	public void rewind() {
		it = sortedMap.entrySet().iterator();
	}
	

}
