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

public interface DictionaryInterface<K, V> {

    /** Adds a new entry to this dictionary. If the given search key already
    exists in the dictionary, replaces the corresponding value.
    @param key An object search key of the new entry.
    @param value An object associated with the search key.
    @return Either null if the new entry was added to the dictionary
    or the value that was associated with key if that value
    was replaced. */
    public V add(K key, V value);

    /** Removes a specific entry from this dictionary.
    @param key An object search key of the entry to be removed.
    @return Either the value that was associated with the search key
    or null if no such object exists. */
    public V remove(K key);

    /** Retrieves from this dictionary the value associated with a given
    search key.
    @param key An object search key of the entry to be retrieved.
    @return Either the value that is associated with the search key
    or null if no such object exists. */
    public V getValue(K key);

    /** Creates an iterator that traverses all search keys in this dictionary.
    @return An iterator that provides sequential access to the search
    keys in the dictionary. */
    public Iterator<K> getKeyIterator();

    /** Creates an iterator that traverses all values in this dictionary.
    @return An iterator that provides sequential access to the values
    in this dictionary. */
    public Iterator<V> getValueIterator();

    /** Sees whether this dictionary is empty.
    @return True if the dictionary is empty. */
    public boolean isEmpty();

    /** Gets the size of this dictionary.
    @return The number of entries (key-value pairs) currently
    in the dictionary. */
    public int getSize();

    /** Removes all entries from this dictionary. */
    public void clear();
    
    // end DictionaryInterfaceCopyright © 2019, 2015, 2012 Pearson Education, Inc. All Rights Reserved
    /** Sees whether a specific entry is in this dictionary.
    @param key An object search key of the desired entry.
    @return True if key is associated with an entry in the dictionary. */
    public boolean contains(K key);
    
}