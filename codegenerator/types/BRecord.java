package de.bmoth.codegenerator.types;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class BRecord implements BObject, Map<java.lang.String, BObject> {

	private final Map<java.lang.String, BObject> map;

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public BObject get(Object key) {
		return map.get(key);
	}

	public BObject getAt(Object key) {
		return map.get(key);
	}

	public BObject put(java.lang.String key, BObject value) {
		throw new UnsupportedOperationException();
	}

	public BObject putAt(java.lang.String key, Object value) {
		throw new UnsupportedOperationException();
	}

	public BObject remove(Object key) {
		throw new UnsupportedOperationException();
	}

	public void putAll(Map<? extends java.lang.String, ? extends BObject> m) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public java.util.Set<java.lang.String> keySet() {
		return map.keySet();
	}

	public Collection<BObject> values() {
		return map.values();
	}

	public java.util.Set<Entry<java.lang.String, BObject>> entrySet() {
		return map.entrySet();
	}

	public boolean equals(Object o) {
		return map.equals(o);
	}

	public int hashCode() {
		return map.hashCode();
	}

	public BRecord(Map<java.lang.String, BObject> map) {
		this.map = map;
	}

	public java.lang.String toString() {
		Iterator<java.util.Map.Entry<java.lang.String, BObject>> it = this
				.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("rec(");
		while (it.hasNext()) {
			Map.Entry<java.lang.String, BObject> entry = (java.util.Map.Entry<java.lang.String, BObject>) it
					.next();
			sb.append(entry.getKey());
			sb.append(": ");
			sb.append(entry.getValue());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	public static Map<java.lang.String, BObject> newStorage() {
		return new LinkedHashMap<java.lang.String, BObject>();
	}
}
