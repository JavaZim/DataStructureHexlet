package com.java.datastructure.Hexlet.module.m03e02;

import java.util.*;

public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair> values = new ArrayList<Pair>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return values.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)

        return size() == 0;
        // END
    }

    @Override
    public boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        for (Pair item : values) {
            if (item.key == key) {
                return true;
            }
        }
        return false;

        // END
    }

    @Override
    public boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        if(value == null) {
            throw new NullPointerException();
        }

        for (Pair item : values) {
            if (item.value == value) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)


        for (Pair item : values) {
            if (item.key == key) {
                return item.value;
            }
        }
        return null;
        // END
    }

    @Override
    public V put(K key, V value) {
        // BEGIN (write your solution here)
        for (Pair item : values) {
            if (item.key == key) {
                item.value = value;
                return item.value;
            }
        }

        Pair newItem = new Pair(key, value);
        values.add(newItem);
        return null;
        // END
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)


        for (int i = 0; i < this.size(); i++)
            if (values.get(i).key.equals(key)) {
                V returnV = values.get(i).value;
                values.remove(i);
                return returnV;
            }
        return null;

        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>) (Set) m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        values.clear();
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : values) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        Collection<V> newCollection = new LinkedList<V>();
        for (Pair item : values) {
            newCollection.add(item.value);
        }
        return newCollection;
        // END
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>) (Set) new HashSet<>(values);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) return false;
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }
    }
}

