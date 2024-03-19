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

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
    private Node topNode; // References the first node in the chain

    // Default constructor
    public LinkedStack() {
        topNode = null;
    }

    // Pushes a new entry onto the stack
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }// end push

    // Retrieves the top entry of the stack without removing it
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return topNode.getData();
        }
    }// end peek

    // Retrieves and removes the top entry of the stack
    public T pop() {
        T top = peek(); // Might throw EmptyStackException
        topNode = topNode.getNextNode();
        return top;
    }// end pop

    // Checks if the stack is empty
    public boolean isEmpty() {
        return topNode == null;
    }// end isEmpty

    // Removes all entries from the stack
    public void clear() {
        topNode = null;
    }// end clear

    // Inner class representing a node in the stack
    private class Node {
        private T data; // Data stored in the node
        private Node next; // Reference to the next node in the chain

        // Constructor with data only
        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        // Constructor with data and reference to the next node
        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }

        // Getter for data
        private T getData() {
            return data;
        }// end getData

        // Setter for data
        private void setData(T newData) {
            data = newData;
        }// end setData

        // Getter for the next node
        private Node getNextNode() {
            return next;
        }// end getNextNode

        // Setter for the next node
        private void setNextNode(Node nextNode) {
            next = nextNode;
        }// end setNextNode
    }// end class: Node
}
