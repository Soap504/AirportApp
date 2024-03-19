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

public interface MinHeapInterface<T extends Comparable<? super T>> {
   /** Adds a new entry to this heap.
        @param newEntry  An object to be added. */
        public void add(T newEntry);

        /** Removes and returns the smallest item in this heap.
            @return  Either the smallest object in the heap or,
                     if the heap is empty before the operation, null. */
        public T removeMin();
    
        /** Retrieves the smallest item in this heap.
            @return  Either the smallest object in the heap or,
                     if the heap is empty, null. */
        public T getMin();
    
        /** Detects whether this heap is empty.
            @return  True if the heap is empty, or false otherwise. */
        public boolean isEmpty();
    
        /** Gets the size of this heap.
            @return  The number of entries currently in the heap. */
        public int getSize();
    
        /** Removes all entries from this heap. */
        public void clear();
}
