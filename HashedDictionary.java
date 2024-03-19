//
// Name: Arce, Sophia
// Project: 5
// Due: 12/8/2023
// Course: cs-2400-02-f23
//
// Description:
// A brief description of the project.
// The project involves implementing a Java program for managing airport information and connections using a graph ADT. 
// Two input files, namely `airports.csv` and `distances.csv`, contain IATA 3-letter airport codes with 
// associated information and distance data between airports, respectively. 
// The program provides a menu system with options to read and store data from the input files, 
// display airport information based on user input, find the shortest distance between two airports using cheapest path algorithm, and exit the program. 
// The implementation includes a DirectedGraph class to store airport and distance information.
// The required input/output interactions include questions pertaining to the airport information, finding minimum distances, displaying help information, 
// and handling various user inputs and potential errors such as invalid commands, unknown airport codes, and unconnected airports.
// The main goal is to create an efficient program that effectively manages and presents airport data based on user response.
//

import java.util.Iterator;

public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {

    private static final int DEFAULT_CAPACITY = 101;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private Entry<K, V>[] table;
    private int numberOfEntries;
    //private int numberOfCollisions;

    // Default constructor
    public HashedDictionary() {
        this(DEFAULT_CAPACITY);
    }

    
    /**
     * Constructor with specified capacity for HashedDictionary.
     *
     * @param cap The initial capacity of the hash table.
    */
    public HashedDictionary(int cap) {
        @SuppressWarnings("unchecked")
        Entry<K, V>[] temp = (Entry<K, V>[]) new Entry[cap];
        table = temp;
        numberOfEntries = 0;
       // numberOfCollisions = 0;
    }

    // Implementation of the add method
    /**
     * Adds a new entry to this dictionary. If the given key already exists in
     * the dictionary, replaces the corresponding value with the new value.
     *
     * @param key   The key of the new entry.
     * @param value The value of the new entry.
     * @return The old value associated with the key, or null if the key did
     *         not exist in the dictionary.
     * @throws IllegalArgumentException if the key or value is null.
     */
    @Override
    public V add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }

        int index = getHashIndex(key);

        // If the slot is empty, add the new entry
        if(table[index] == null){
            table[index] =new Entry<>(key, value);
            numberOfEntries++;

            // Check if resizing is needed
            if ((double) numberOfEntries / table.length > LOAD_FACTOR_THRESHOLD) {
                enlargeHashTable();
            }

            return null;
        }
        else{
            // If the slot is occupied, update the existing entry
            V oldValue = table[index].getValue();
            table[index].setValue(value);
            return oldValue;
        }
    }
    // Resize the hash table when it becomes too full
    private void enlargeHashTable() {
        int newCapacity = table.length * 2 + 1;
        Entry<K, V>[] newHashTable = new Entry[newCapacity];
        numberOfEntries = 0;

        // Rehash existing entries into the new, larger hash table
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                int newIndex = getHashIndex(entry.getKey());
                newHashTable[newIndex] = entry;
                numberOfEntries++;
            }
        }
        table = newHashTable;
    }

    // Implementation of the getValue method
    /**
     * Retrieves the value associated with the given key in this dictionary.
     *
     * @param key The key of the entry to locate.
     * @return The value associated with the key, or null if the key is not
     *         found.
     */
    @Override
    public V getValue(K key) {
        int index = getHashIndex(key);

        while (table[index] != null) {
            if (key.equals(table[index].key)) {
                return table[index].value;
            }
            index = (index + 1) % table.length;
        }

        return null;
    }

    // Implementation of the getKeyIterator method
    /**
     * Returns an iterator that traverses all keys in this dictionary.
     *
     * @return An iterator over the keys in this dictionary.
    */
    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    // Method to calculate the hash index for a given key
    private int getHashIndex(K key) {
        int hash = key.hashCode() % table.length;
        return (hash < 0) ? (hash + table.length) % table.length : hash;
    }

    /*
     * Entry class representing key-value pairs.
     *
     * @param <K> The type of keys.
     * @param <V> The type of values.
    */
    public static class Entry<K, V> {
        private K key;
        private V value;

        /**
         * Entry constructor.
         *
         * @param searchKey The key of the entry.
         * @param dataValue The value associated with the key.
        */
        private Entry(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
        }

        /**
         * Gets the key of the entry.
         *
         * @return The key.
        */
        public K getKey(){
            return key;
        }

        /**
         * Gets the value of the entry.
         * @return The value.
        */
        public V getValue(){
            return value;
        }

        /**
         * Sets the value of the entry.
         * @param value The new value.
        */
        public void setValue(V value){
            this.value = value;
        }

    }

    // KeyIterator class implementing the Iterator interface
    private class KeyIterator implements Iterator<K> {
        private int currentIndex;

        // Constructor for KeyIterator
        private KeyIterator() {
            currentIndex = 0;
        }

        /**
         * Check if there is a next key in the hash table.
         *
         * @return True if there is a next key, false otherwise.
        */
        @Override
        public boolean hasNext() {
            while (currentIndex < table.length && table[currentIndex] == null) {
                currentIndex++;
            }
            return currentIndex < table.length;
        }

        /**
         * Get the next key in the hash table.
         *
         * @return The next key if available, otherwise null.
        */
        @Override
        public K next() {
            if (hasNext()) {
                K key = table[currentIndex].getKey();
                currentIndex++;
                return key;
            } else {
                return null;
            }
        }
    }

    // inner class
    // KeyIterator class implementing the Iterator interface
    private class ValueIterator implements Iterator<V> {
        private int currentIndex;

        /**
         * Constructor for ValueIterator.
         *
         * @param newDictionary The dictionary to iterate over.
        */
        private ValueIterator(HashedDictionary<K, V> newDictionary) {
            currentIndex = 0;
        }

        /**
         * Check if there is a next value in the hash table.
         *
         * @return True if there is a next value, false otherwise.
        */
        @Override
        public boolean hasNext() {
            while (currentIndex < table.length && table[currentIndex] == null) {
                currentIndex++;
            }
            return currentIndex < table.length;
        }

        /**
         * Get the next value in the hash table.
         *
         * @return The next value if available, otherwise null.
        */
        @Override
        public V next() {
            if (hasNext()) {
                V value = table[currentIndex].getValue();
                currentIndex++;
                return value;
            } else {
                return null;
            }
        }
    }

    // Unsupported methods
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported");
    }

    // Get an iterator for values in the hash table.
    public Iterator<V> getValueIterator()
    {
        Iterator<V> myIterator = new ValueIterator(this);
        return myIterator;  
    }

    // Get an iterator for values in the hash table.
    public int getSize() {
        throw new UnsupportedOperationException("Not supported");
    }
    //clears hash table
    public void clear() {
        throw new UnsupportedOperationException("Not supported");
    }
     

    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported");
    }

    public boolean contains(K key) {
        throw new UnsupportedOperationException("Not supported");
    }
}
