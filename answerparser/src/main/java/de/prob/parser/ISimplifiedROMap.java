package de.prob.parser;

/**
 * This interface describes a completely stripped-down version of a read-only
 * map. Its purpose is to allow it to implement easily a rewriting-strategy for
 * keys without working out all those details in all the {@link java.util.Map}.
 * 
 * The class {@link ISimplifiedROMap} provides a simple wrapper class to use a
 * {@link java.util.Map} as a {@link ISimplifiedROMap}.
 * 
 * @author plagge
 */
public interface ISimplifiedROMap<K, V> {
	V get(K key);
}
