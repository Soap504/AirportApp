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

public interface GraphAlgorithmsInterface<T>
{
    /** Performs a breadth-first traversal of this graph.
        @param origin  An object that labels the origin vertex of the traversal.
        @return  A queue of labels of the vertices in the traversal, with
                 the label of the origin vertex at the queue's front. */
    public QueueInterface<T> getBreadthFirstTraversal(T origin);

    /** Performs a depth-first traversal of this graph.
        @param origin  An object that labels the origin vertex of the traversal.
        @return  A queue of labels of the vertices in the traversal, with
                 the label of the origin vertex at the queue's front. */
    public QueueInterface<T> getDepthFirstTraversal(T origin);

    /** Performs a topological sort of the vertices in this graph without cycles.
        @return  A stack of vertex labels in topological order, beginning
                 with the stack's top. */
    public StackInterface<T> getTopologicalOrder();

    /** Finds the shortest-length path between two given vertices in this graph.
        @param begin  An object that labels the path's origin vertex.
        @param end    An object that labels the path's destination vertex.
        @param path   A stack of labels that is empty initially;
                      at the completion of the method, this stack contains
                      the labels of the vertices along the shortest path;
                      the label of the origin vertex is at the top, and
                      the label of the destination vertex is at the bottom       
        @return  The length of the shortest path. */
    public int getShortestPath(T begin, T end, StackInterface<T> path);

    /** Finds the least-cost path between two given vertices in this graph.
        @param begin  An object that labels the path's origin vertex.
        @param end    An object that labels the path's destination vertex.
        @param path   A stack of labels that is empty initially;
                      at the completion of the method, this stack contains
                      the labels of the vertices along the cheapest path;
                      the label of the origin vertex is at the top, and
                      the label of the destination vertex is at the bottom       
        @return  The cost of the cheapest path. */
    public double getCheapestPath(T begin, T end, StackInterface<T> path);
} // end GraphAlgorithmsInterface