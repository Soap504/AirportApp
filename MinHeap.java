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

import java.util.Arrays;

public final class MinHeap<T extends Comparable<? super T>>
             implements PriorityQueueInterface<T>
{
    private T[] heap;      // Array of heap entries; ignore heap[0]
    private int lastIndex; // Index of last entry and number of entries
    
    private static final int DEFAULT_CAPACITY = 25;

    public MinHeap()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    // Constructor initializes the heap with a given initial capacity.
    public MinHeap(int initialCapacity)
    {
        // Is initialCapacity too small?
        
           initialCapacity = DEFAULT_CAPACITY;

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        
    } // end constructor
    
    // Constructor creates a heap from an array of entries.
    public MinHeap(T[] entries)
    {
        this(entries.length);   // Call other constructor
        lastIndex = entries.length;
        
        // Copy given array to data field
        for (int index = 0; index < lastIndex; index++)
            heap[index + 1] = entries[index];
        // Create heap
        for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
            reheap(rootIndex);        
    } // end constructor

    // Adds a new entry to the heap while maintaining the heap property.
    public void add(T newEntry)
    {
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) < 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } // end while
        heap[newIndex] = newEntry;
        lastIndex++;
    } // end add

    // Removes and returns the minimum value (root) from the heap.
    public T remove()
    {
         // Ensure initialization of data fields        
        T root = null;
        if (!isEmpty())
        {
            root = heap[1];             // Return a value
            heap[1] = heap[lastIndex];  // Form a semiheap
            lastIndex--;                // Decrease size
            reheap(1);                  // Transform to a heap
        } /// end if
        return root;    
    } // end remove
    
    // Private helper method to transform a semiheap into a heap.
    private void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while (!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) && 
                    heap[rightChildIndex].compareTo(heap[largerChildIndex]) < 0)
            {
                largerChildIndex = rightChildIndex;
            } // end if
            
            if (orphan.compareTo(heap[largerChildIndex]) > 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        } // end while
        heap[rootIndex] = orphan;
    } // end reheap

    /** Revised reheap method.
        @param <T> Data type of the entry
        @param heap The array containing the heap entries
        @param rootIndex The index of the root of semiheap
        @param lastIndex The last index that the array heap ranges. */
    private static <T extends Comparable<? super T>> 
            void reheap(T[] heap, int rootIndex, int lastIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex + 1;
        while (!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) && 
                    heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            } // end if
            
            if (orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex + 1;
            }
            else
                done = true;
        } // end while
        heap[rootIndex] = orphan;
    } // end reheap
    
    // Static method to perform heap sort on an array.
    public static <T extends Comparable<? super T>> void heapSort(T[] array, int n)
    {
        // Create heap
        for (int rootIndex = n / 2 - 1; rootIndex >= 0; rootIndex--)
            reheap(array, rootIndex, n - 1);    
        swap(array, 0, n - 1);
        
        for (int lastIndex = n - 2; lastIndex > 0; lastIndex--)
        {
            reheap(array, 0, lastIndex);
            swap(array, 0, lastIndex);
        } // end for
    } // end heapSort
            
    // Static method to swap elements in an array
    private static <T extends Comparable<? super T>> 
            void swap(T[] array, int firstIndex, int secondIndex)
    {
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    } // end swap
            
    // Returns the minimum value (root) from the heap without removing it.
    public T peek()
    {
        
        T root = null;
        if (!isEmpty())
           root = heap[1];
        return root;
    } // end getMin

    // Checks if the heap is empty.
    public boolean isEmpty()
    {
        return lastIndex < 1;
    } // end isEmpty

    // Returns the number of entries in the heap.
    public int getSize()
    {
        return lastIndex;
    } // end getSize

    // Clears all entries from the heap.
    public void clear()
    {
      
        while (lastIndex > -1)
        {
           heap[lastIndex] = null;
           lastIndex--;
        } // end while
        lastIndex = 0;
    } // end clear
     
} // end MinHeap