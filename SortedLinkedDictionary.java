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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedDictionary<K, V> implements DictionaryInterface<K, V> {
    private Node firstNode; // Reference to the first node in the sorted linked dictionary
    private int numberOfEntries; // Number of entries in the sorted linked dictionary

    // Inner class Node
    private class Node {
        private K key;
        private V value;
        private Node next;

        // Node constructor
        public Node(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
            next = null;
        }

        // Getter for the key of the node
        public K getKey() {
            return key;
        }

        // Getter for the value of the node
        public V getValue() {
            return value;
        }

        // Setter for the value of the node
        public void setValue(V newVal) {
            value = newVal;
        }

        // Getter for the next node in the linked list
        public Node getNextNode() {
            return next;
        }

        // Setter for the next node in the linked list
        public void setNextNode(Node nextNode) {
            next = nextNode;
        }
    }

    // Constructor for the SortedLinkedDictionary
    public SortedLinkedDictionary() {
        initializeDataFields();
    }

    // Helper method to initialize data fields
    private void initializeDataFields() {
        firstNode = null;
        numberOfEntries = 0;
    }

    // CompareTo method for comparing two SortedLinkedDictionary objects
    public <T> int compareTo(T other) {
        int result;

        if (this.firstNode == null && ((SortedLinkedDictionary<?, ?>) other).firstNode == null) {
            result = 0;
        } else if (this.firstNode == null && ((SortedLinkedDictionary<?, ?>) other).firstNode != null) {
            result = -1;
        } else if (this.firstNode != null && ((SortedLinkedDictionary<?, ?>) other).firstNode == null) {
            result = 1;
        } else {
            result = ((String) this.firstNode.getKey()).compareTo((String) ((SortedLinkedDictionary<?, ?>) other).firstNode.getKey());
        }

        return result;
    }

    // Add method to insert a key-value pair into the sorted linked dictionary
    @Override
    public V add(K key, V value) {
        if (key == null || value == null)
            throw new IllegalArgumentException("Key or value cannot be null.");

        Node newNode = new Node(key, value);

        if (isEmpty() || ((SortedLinkedDictionary<K, V>) key).compareTo((SortedLinkedDictionary<K, V>) firstNode.getKey()) <= 0) {
            // Add at the beginning
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            Node previousNode = null;

            while (currentNode != null && ((SortedLinkedDictionary<K, V>) key).compareTo(currentNode.getKey()) > 0) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }

            newNode.setNextNode(currentNode);
            previousNode.setNextNode(newNode);
        }
        numberOfEntries++;
        return null;
    }

    // Remove method to delete a key-value pair with a specified key from the sorted linked dictionary
    @Override
    public V remove(K key) {
        if (isEmpty() || key == null)
            return null;

        V removedValue = null;

        if (key.equals(firstNode.getKey())) {
            // Remove at the beginning
            removedValue = firstNode.getValue();
            firstNode = firstNode.getNextNode();
        } else {
            Node currentNode = firstNode;
            Node previousNode = null;

            while (currentNode != null && !key.equals(currentNode.getKey())) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }

            if (currentNode != null) {
                removedValue = currentNode.getValue();
                previousNode.setNextNode(currentNode.getNextNode());
            }
        }

        if (removedValue != null) {
            numberOfEntries--;
        }

        return removedValue;
    }

    // GetValue method to retrieve the value associated with a specified key
    @Override
    public V getValue(K key) {
        if (isEmpty() || key == null) {
            return null;
        }

        Node currentNode = firstNode;
        while (currentNode != null) {
            if (key.equals(currentNode.getKey())) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNextNode();
        }

        return null;
    }

    // Contains method to check if the sorted linked dictionary contains a specified key
    @Override
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    // GetKeyIterator method to retrieve an iterator for the keys in the sorted linked dictionary
    @Override
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    // Inner class KeyIterator for iterating over the keys in the sorted linked dictionary
    private class KeyIterator implements Iterator<K> {
        private Node current;

        // Constructor for the KeyIterator
        public KeyIterator() {
            current = firstNode;
        }

        // Check if there is another key in the iterator
        @Override
        public boolean hasNext() {
            return current != null;
        }

        // Retrieve the next key in the iterator
        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator.");
            }

            K key = current.getKey();
            current = current.getNextNode();
            return key;
        }
    }

    // Check if the sorted linked dictionary is empty
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    // Get the size (number of entries) in the sorted linked dictionary
    @Override
    public int getSize() {
        return numberOfEntries;
    }

    // Clear method to remove all entries from the sorted linked dictionary
    @Override
    public void clear() {
        initializeDataFields();
    }

    // GetValueIterator method (unsupported operation)
    @Override
    public Iterator<V> getValueIterator() {
        throw new UnsupportedOperationException("Not supported");
    }
}
