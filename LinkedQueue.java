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

public final class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode; // References node at front of queue
    private Node lastNode;  // References node at back of queue
    
    public LinkedQueue()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor
    
    /** Adds a new entry to the back of this queue.
       @param newEntry  An object to be added. */
    public void enqueue(T newEntry)
    {
        Node newNode = new Node(newEntry);
        
        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        // end if
        lastNode = newNode;
    } // end enqueue
    
    /** Removes and returns the entry at the front of this queue.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty before the operation. */
    public T dequeue()
    {
        T front = getFront(); // Might throw an EmptyQueueException
        assert firstNode != null;
        firstNode.setData(null);
        firstNode = firstNode.getNextNode(); // Remove first node by setting second node as firstNode.
        if (firstNode == null)  // If the chain had only one node which is just removed.
            lastNode = null;
        return front;
    } // end dequeue
    
    /** Retrieves the entry at the front of this queue.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty. */
    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.getData();
    } // end getFront
    
    /** Detects whether this queue is empty.
       @return  True if the queue is empty, or false otherwise. */
    public boolean isEmpty()
    {
        return (firstNode == null) && (lastNode == null);
    } // end isEmpty
   
    /** Removes all entries from this queue. */
    public void clear()
    {
        firstNode = null;
        lastNode = null;
    } // end clear
    
    private class Node
    {
        private T    data;  // Entry in bag
        private Node next;  // Link to next node

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        } // end constructor

        private T getData()
        {
            return data;
        } // end getData

        private void setData(T newData)
        {
            data = newData;
        } // end setData

        private Node getNextNode()
        {
            return next;
        } // end getNextNode

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        } // end setNextNode
    } // end Node
} // end LinkedQueue