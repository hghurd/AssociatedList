import java.util.List;
import java.util.Set;

/**
 * An object that maps keys to values.
 * A map cannot contain duplicate keys; each key can map to at most one value.
 */
public interface SimpleMap {
	/**
	 * A map entry (key-value pair).
	 */
	public class Entry {
		private String key;
		private Integer value;

		/**
		 * @return the key corresponding to this entry.
		 */
		public String getKey() { return key; }

		/**
		 *
		 * @return the value corresponding to this entry.
		 */
		public Integer getValue( ) { return value; }

		/**
		 * Replaces the value corresponding to this entry with the specified value
		 * @param value - new value to be stored in this entry
		 * @return old value corresponding to the entry
		 */
		public Integer setValue( Integer value ) {
			Integer oldValue = this.value;
			this.value = value;
			return oldValue;
		};

		/**
		 * Constructor: Initializes a new instance of Entry with a key and value.
		 * @param key - key to be stored in this entry
		 * @param value - value to be stored in this entry
		 */
		public Entry( String key, Integer value ) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * Returns the value to which the specified key is mapped,
	 * or null if this map contains no mapping for the key.
	 * @param key - the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped,
	 *         or null if this map contains no mapping for the key
	 */
	public Integer get( String key );

	/**
	 * Associates the specified value with the specified key in this map.
	 *
	 * If the map previously contained a mapping for the key,
	 * the old value is replaced by the specified value.
	 * @param key - key with which the specified value is to be associated
	 * @param value - value to be associated with the specified key
	 * @return the previous value associated with key,
	 *         or null if there was no mapping for key.
	 */
	public Integer put( String key, Integer value );

	/**
	 * Removes the mapping for a key from this map if it is present.
	 *
	 * Returns the value to which this map previously associated the key,
	 * or null if the map contained no mapping for the key.
	 *
	 * The map will not contain a mapping for the specified key once the call returns.
	 * @param key - key whose mapping is to be removed from the map
	 * @return the previous value associated with key,
	 *         or null if there was no mapping for key.
	 */
	public Integer remove( String key );

	/**
	 * Returns true if this map contains no entries mappings.
	 * @return true if this map contains no entries mappings
	 */
	public boolean isEmpty( );

	/**
	 * Returns the number of entries in this map.
	 *
	 * If the map contains more than Integer.MAX_VALUE elements,
	 * returns Integer.MAX_VALUE.
	 * @return the number of entries in this map
	 */
	public int size( );

	/**
	 * Returns the list of the mappings maintained by this map.
	 * @return a list of the mappings contained in this map
	 */
	public List<Entry> entryList( );
}
