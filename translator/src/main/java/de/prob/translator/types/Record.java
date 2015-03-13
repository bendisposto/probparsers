package de.prob.translator.types;

import java.lang.String;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Record implements BObject, Map<java.lang.String, BObject> {
	private final Map<java.lang.String, BObject> map;

	/**
	 * Returns the number of key-value mappings in this map. If the map contains
	 * more than <tt>Integer.MAX_VALUE</tt> elements, returns
	 * <tt>Integer.MAX_VALUE</tt>.
	 * 
	 * @return the number of key-value mappings in this map
	 */

	public int size() {
		return map.size();
	}

	/**
	 * Returns <tt>true</tt> if this map contains no key-value mappings.
	 * 
	 * @return <tt>true</tt> if this map contains no key-value mappings
	 */

	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * Returns <tt>true</tt> if this map contains a mapping for the specified
	 * key. More formally, returns <tt>true</tt> if and only if this map
	 * contains a mapping for a key <tt>k</tt> such that
	 * <tt>(key==null ? k==null : key.equals(k))</tt>. (There can be at most one
	 * such mapping.)
	 * 
	 * @param key
	 *            key whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map contains a mapping for the specified
	 *         key
	 * @throws ClassCastException
	 *             if the key is of an inappropriate type for this map (<a
	 *             href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if the specified key is null and this map does not permit
	 *             null keys (<a
	 *             href="Collection.html#optional-restrictions">optional</a>)
	 */
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	/**
	 * Returns <tt>true</tt> if this map maps one or more keys to the specified
	 * value. More formally, returns <tt>true</tt> if and only if this map
	 * contains at least one mapping to a value <tt>v</tt> such that
	 * <tt>(value==null ? v==null : value.equals(v))</tt>. This operation will
	 * probably require time linear in the map size for most implementations of
	 * the <tt>Map</tt> interface.
	 * 
	 * @param value
	 *            value whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map maps one or more keys to the specified
	 *         value
	 * @throws ClassCastException
	 *             if the value is of an inappropriate type for this map (<a
	 *             href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if the specified value is null and this map does not permit
	 *             null values (<a
	 *             href="Collection.html#optional-restrictions">optional</a>)
	 */

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	/**
	 * Returns the value to which the specified key is mapped, or {@code null}
	 * if this map contains no mapping for the key.
	 * <p/>
	 * <p>
	 * More formally, if this map contains a mapping from a key {@code k} to a
	 * value {@code v} such that {@code (key==null ? k==null :
	 * key.equals(k))}, then this method returns {@code v}; otherwise it returns
	 * {@code null}. (There can be at most one such mapping.)
	 * <p/>
	 * <p>
	 * If this map permits null values, then a return value of {@code null} does
	 * not <i>necessarily</i> indicate that the map contains no mapping for the
	 * key; it's also possible that the map explicitly maps the key to
	 * {@code null}. The {@link #containsKey containsKey} operation may be used
	 * to distinguish these two cases.
	 * 
	 * @param key
	 *            the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or {@code null}
	 *         if this map contains no mapping for the key
	 * @throws ClassCastException
	 *             if the key is of an inappropriate type for this map (<a
	 *             href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if the specified key is null and this map does not permit
	 *             null keys (<a
	 *             href="Collection.html#optional-restrictions">optional</a>)
	 */

	public BObject get(Object key) {
		return map.get(key);
	}

	public BObject getAt(Object key) {
		return map.get(key);
	}

	/**
	 * Associates the specified value with the specified key in this map
	 * (optional operation). If the map previously contained a mapping for the
	 * key, the old value is replaced by the specified value. (A map <tt>m</tt>
	 * is said to contain a mapping for a key <tt>k</tt> if and only if
	 * {@link #containsKey(Object) m.containsKey(k)} would return <tt>true</tt>
	 * .)
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt>
	 *         if there was no mapping for <tt>key</tt>. (A <tt>null</tt> return
	 *         can also indicate that the map previously associated
	 *         <tt>null</tt> with <tt>key</tt>, if the implementation supports
	 *         <tt>null</tt> values.)
	 * @throws UnsupportedOperationException
	 *             if the <tt>put</tt> operation is not supported by this map
	 * @throws ClassCastException
	 *             if the class of the specified key or value prevents it from
	 *             being stored in this map
	 * @throws NullPointerException
	 *             if the specified key or value is null and this map does not
	 *             permit null keys or values
	 * @throws IllegalArgumentException
	 *             if some property of the specified key or value prevents it
	 *             from being stored in this map
	 */
	public BObject put(java.lang.String key, BObject value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes the mapping for a key from this map if it is present (optional
	 * operation). More formally, if this map contains a mapping from key
	 * <tt>k</tt> to value <tt>v</tt> such that
	 * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping is
	 * removed. (The map can contain at most one such mapping.)
	 * <p/>
	 * <p>
	 * Returns the value to which this map previously associated the key, or
	 * <tt>null</tt> if the map contained no mapping for the key.
	 * <p/>
	 * <p>
	 * If this map permits null values, then a return value of <tt>null</tt>
	 * does not <i>necessarily</i> indicate that the map contained no mapping
	 * for the key; it's also possible that the map explicitly mapped the key to
	 * <tt>null</tt>.
	 * <p/>
	 * <p>
	 * The map will not contain a mapping for the specified key once the call
	 * returns.
	 * 
	 * @param key
	 *            key whose mapping is to be removed from the map
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt>
	 *         if there was no mapping for <tt>key</tt>.
	 * @throws UnsupportedOperationException
	 *             if the <tt>remove</tt> operation is not supported by this map
	 * @throws ClassCastException
	 *             if the key is of an inappropriate type for this map (<a
	 *             href="Collection.html#optional-restrictions">optional</a>)
	 * @throws NullPointerException
	 *             if the specified key is null and this map does not permit
	 *             null keys (<a
	 *             href="Collection.html#optional-restrictions">optional</a>)
	 */
	public BObject remove(Object key) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Copies all of the mappings from the specified map to this map (optional
	 * operation). The effect of this call is equivalent to that of calling
	 * {@link #put(Object, Object) put(k, v)} on this map once for each mapping
	 * from key <tt>k</tt> to value <tt>v</tt> in the specified map. The
	 * behavior of this operation is undefined if the specified map is modified
	 * while the operation is in progress.
	 * 
	 * @param m
	 *            mappings to be stored in this map
	 * @throws UnsupportedOperationException
	 *             if the <tt>putAll</tt> operation is not supported by this map
	 * @throws ClassCastException
	 *             if the class of a key or value in the specified map prevents
	 *             it from being stored in this map
	 * @throws NullPointerException
	 *             if the specified map is null, or if this map does not permit
	 *             null keys or values, and the specified map contains null keys
	 *             or values
	 * @throws IllegalArgumentException
	 *             if some property of a key or value in the specified map
	 *             prevents it from being stored in this map
	 */
	public void putAll(Map<? extends java.lang.String, ? extends BObject> m) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes all of the mappings from this map (optional operation). The map
	 * will be empty after this call returns.
	 * 
	 * @throws UnsupportedOperationException
	 *             if the <tt>clear</tt> operation is not supported by this map
	 */
	public void clear() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a {@link Set} view of the keys contained in this map. The set is
	 * backed by the map, so changes to the map are reflected in the set, and
	 * vice-versa. If the map is modified while an iteration over the set is in
	 * progress (except through the iterator's own <tt>remove</tt> operation),
	 * the results of the iteration are undefined. The set supports element
	 * removal, which removes the corresponding mapping from the map, via the
	 * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
	 * <tt>retainAll</tt>, and <tt>clear</tt> operations. It does not support
	 * the <tt>add</tt> or <tt>addAll</tt> operations.
	 * 
	 * @return a set view of the keys contained in this map
	 */
	public java.util.Set<java.lang.String> keySet() {
		return map.keySet();
	}

	/**
	 * Returns a {@link Collection} view of the values contained in this map.
	 * The collection is backed by the map, so changes to the map are reflected
	 * in the collection, and vice-versa. If the map is modified while an
	 * iteration over the collection is in progress (except through the
	 * iterator's own <tt>remove</tt> operation), the results of the iteration
	 * are undefined. The collection supports element removal, which removes the
	 * corresponding mapping from the map, via the <tt>Iterator.remove</tt>,
	 * <tt>Collection.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
	 * <tt>clear</tt> operations. It does not support the <tt>add</tt> or
	 * <tt>addAll</tt> operations.
	 * 
	 * @return a collection view of the values contained in this map
	 */
	public Collection<BObject> values() {
		return map.values();
	}

	/**
	 * Returns a {@link Set} view of the mappings contained in this map. The set
	 * is backed by the map, so changes to the map are reflected in the set, and
	 * vice-versa. If the map is modified while an iteration over the set is in
	 * progress (except through the iterator's own <tt>remove</tt> operation, or
	 * through the <tt>setValue</tt> operation on a map entry returned by the
	 * iterator) the results of the iteration are undefined. The set supports
	 * element removal, which removes the corresponding mapping from the map,
	 * via the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>
	 * , <tt>retainAll</tt> and <tt>clear</tt> operations. It does not support
	 * the <tt>add</tt> or <tt>addAll</tt> operations.
	 * 
	 * @return a set view of the mappings contained in this map
	 */
	public java.util.Set<Entry<java.lang.String, BObject>> entrySet() {
		return map.entrySet();
	}

	/**
	 * Compares the specified object with this map for equality. Returns
	 * <tt>true</tt> if the given object is also a map and the two maps
	 * represent the same mappings. More formally, two maps <tt>m1</tt> and
	 * <tt>m2</tt> represent the same mappings if
	 * <tt>m1.entrySet().equals(m2.entrySet())</tt>. This ensures that the
	 * <tt>equals</tt> method works properly across different implementations of
	 * the <tt>Map</tt> interface.
	 * 
	 * @param o
	 *            object to be compared for equality with this map
	 * @return <tt>true</tt> if the specified object is equal to this map
	 */
	public boolean equals(Object o) {
		return map.equals(o);
	}

	/**
	 * Returns the hash code value for this map. The hash code of a map is
	 * defined to be the sum of the hash codes of each entry in the map's
	 * <tt>entrySet()</tt> view. This ensures that <tt>m1.equals(m2)</tt>
	 * implies that <tt>m1.hashCode()==m2.hashCode()</tt> for any two maps
	 * <tt>m1</tt> and <tt>m2</tt>, as required by the general contract of
	 * {@link Object#hashCode}.
	 * 
	 * @return the hash code value for this map
	 * @see java.util.Map.Entry#hashCode()
	 * @see Object#equals(Object)
	 * @see #equals(Object)
	 */
	public int hashCode() {
		return map.hashCode();
	}

	public Record(Map<java.lang.String, BObject> map) {
		this.map = map;
	}

	public String toString() {
		return "rec(" + map + ')';
	public static Map<java.lang.String, BObject> newStorage() {
		return new LinkedHashMap<java.lang.String, BObject>();
	}
}
