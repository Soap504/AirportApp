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

public final class PriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T>
{
    private final T[] queue; 
    private final T[] heap;
    private int lastIndex;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    
    
    public PriorityQueue()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor
    
    /** Creates an empty queue having a given capacity.
        @param desiredCapacity  The integer capacity desired. */
    public PriorityQueue(int desiredCapacity)
    {
        if (desiredCapacity <= MAX_CAPACITY)
        {
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[])new Comparable<?>[desiredCapacity]; // Unchecked cast
            queue = tempQueue;
            heap = (T[]) new Comparable<?>[desiredCapacity];
            lastIndex = 0;
            numberOfEntries = 0;
            integrityOK = true;
            // Test that contents are nulls - OK
            //      for (int index = 0; index < desiredCapacity; index++) 
            //         System.out.print(queue[index] + " ");
            //      System.out.println();
        }
        else
            throw new IllegalStateException("Attempt to create a queue " +
                                         "whose capacity exceeds " +
                                         "allowed maximum.");
    } // end constructor
    
    
    /** Adds a new entry to the front/back of this priority queue.
        @param newEntry  An object to be added. */
    public void add(T newEntry)
    {
        checkIntegrity();        // Ensure initialization of data fields
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ( (parentIndex < 0) && newEntry.compareTo(heap[parentIndex]) < 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } // end while
        heap[newIndex] = newEntry;
        lastIndex++;
    } // end add
    
    /** Removes and returns the entry having the highest priority.
        @return Either the object having the highest priority or, if the
                priority queue is empty before the operation, null. */
    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    } // end remove
    /** Retrieves the entry having the highest priority.
        @return Either the object having the highest priority or, if the
                priority queue is empty, null. */
    public T peek()
    {
        return queue[0];
    } // end peek
    
    /** Detects whether this priority queue is empty.
       @return  True if the priority queue is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return false;
    } // end isEmpty
    
    /** Gets the size of this priority queue.
        @return  The number of entries currently in the priority queue. */
    public int getSize()
    {
        return numberOfEntries;
    } // end getSize

    /* Removes all entries from this priority queue. */
    public void clear()
    {
        while (!isEmpty())
            remove();
    } // end clear
    
    // Removes and returns the entry at a given index within the array.
    // If no such entry exists, returns null.
    // Precondition: 0 <= givenIndex < numberOfEntries.
    // Precondition: checkInitialization has been called.
    private T removeEntry(int givenIndex)
    {
        T result = null;
      
        if (!isEmpty() && (givenIndex >= 0))
        {
            result = queue[givenIndex];          // Entry to remove
            int lastIndex = numberOfEntries - 1;
            queue[givenIndex] = queue[lastIndex];  // Replace entry to remove with last entry
            queue[lastIndex] = null;             // Remove reference to last entry
            numberOfEntries--;
        } // end if
      
        return result;
    } // end removeEntry
   
    // Throws an exception if this object is not initialized.
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("ArrayQueue object is corrupt.");
    } // end checkIntegrity
    
// Returns true if the array queue is full, or false if not.
    private boolean isArrayFull()
    {
        return numberOfEntries >= queue.length;
    } // end isArrayFull
} // end LinkedQueue