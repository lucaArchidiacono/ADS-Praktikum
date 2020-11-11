package Praktikum_09_Code;
import java.util.*;

public class MyHashtable<K,V> implements java.util.Map<K,V> {
    private K[] keys;
    private V[] values;
    private int size, max;
    private final K INVALIDKEY = (K) new Object();

    private int calcHash(int h) {
        return h % keys.length;
    }

    private int hash(Object k) {
        int h = Math.abs(k.hashCode());
        return calcHash(h);
    }

    public MyHashtable(int size) {
        // to be done
        this.max = size;
        clear();
    }

    //  Removes all mappings from this map (optional operation).
    public void clear() {
        // to be done
        keys = (K[]) new Object[max];
        values = (V[]) new Object[max];
        size = 0;
    }

    private int find(Object key) {
        //without a counter/indexer there would be a infinite loop inside the while
        int counter = 0;
        int currentPos = hash(key);
        while (keys[currentPos] != null && !keys[currentPos].equals(key) && counter < max) {
            currentPos = calcHash(currentPos+1);
            counter++;
        }

        if (counter == max) return -1;
        return currentPos;
    }

    //  Associates the specified value with the specified key in this map (optional operation).
    public V put(K key, V value) {
        // to be done
        int hash = find(key);
        if (hash < 0 || values[hash] == null) {
            if (size > 0.8* max) {
                K[] oldK = keys;
                V[] oldV = values;
                max *= 2;
                clear();
                for (int i=0; i < oldK.length; i++){
                    if (oldK[i] != null && oldK[i] != INVALIDKEY) {
                        put(oldK[i], oldV[i]);
                    }
                }
            }

            hash = hash(key);

            while (keys[hash] != null && !keys[hash].equals(key) && keys[hash] != INVALIDKEY) {
                hash = calcHash(hash+1);
            }

            keys[hash] = key;
            values[hash] = value;
            size++;
        }
        return value;
    }

    //  Returns the value to which this map maps the specified key.
    public V get(Object key) {
        // to be done
        int hash = find(key);
        return (hash >= 0) ? values[hash] : null;
    }

    //  Returns true if this map contains no key-value mappings.
    public boolean isEmpty() {
        // to be done
        return size <= 0;
    }

    //  Removes the mapping for this key from this map if present (optional operation).
    public V remove(Object key) {
        // to be done (Aufgabe 3)
        int hash = find(key);
        V value = null;
        if (hash >= 0 && keys[hash] != null) {
            value = values[hash];
            size--;
            values[hash] = null;
            //mark this key as "deleted" with an empty key
            keys[hash] = INVALIDKEY;
        }
        return value;
    }

    //  Returns the number of key-value mappings in this map.
    public int size() {
        // to be done
        return size;
    }

    // =======================================================================
    //  Returns a set view of the keys contained in this map.
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    //  Copies all of the mappings from the specified map to this map (optional operation).
    public void putAll(Map t) {
        throw new UnsupportedOperationException();
    }

    //  Returns a collection view of the values contained in this map.
    public Collection values() {
        throw new UnsupportedOperationException();
    }
    //  Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }
    //  Returns true if this map maps one or more keys to the specified value.
    public boolean containsValue(Object value)  {
        throw new UnsupportedOperationException();
    }
    //  Returns a set view of the mappings contained in this map.
    public Set entrySet() {
        throw new UnsupportedOperationException();
    }
    //  Compares the specified object with this map for equality.
    public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }
    //  Returns the hash code value for this map.
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

}