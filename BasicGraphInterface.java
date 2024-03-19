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
public interface BasicGraphInterface<T>
{
    /** Adds a given vertex to this graph.
        @param vertexLabel  An object that labels the new vertex and is
                            distinct from the labels of current vertices.
        @return  True if the vertex is added, or false if not. */
    public boolean addVertex(T vertexLabel);

    /** Adds a weighted edge between two given distinct vertices that 
        are currently in this graph. The desired edge must not already 
        be in the graph. In a directed graph, the edge points toward
        the second vertex given.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object, distinct from begin, that labels the end
                      vertex of the edge.
        @param edgeWeight  The real value of the edge's weight.
        @return  True if the edge is added, or false if not. */
    public boolean addEdge(T begin, T end, double edgeWeight);

    /** Adds an unweighted edge between two given distinct vertices 
        that are currently in this graph. The desired edge must not
        already be in the graph. In a directed graph, the edge points 
        toward the second vertex given.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object, distinct from begin, that labels the end
                      vertex of the edge.
        @return  True if the edge is added, or false if not. */
    public boolean addEdge(T begin, T end);

    /** Sees whether an edge exists between two given vertices.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object that labels the end vertex of the edge.
        @return  True if an edge exists. */
    public boolean hasEdge(T begin, T end);

    /** Sees whether this graph is empty.
        @return  True if the graph is empty. */
    public boolean isEmpty();

    /** Gets the number of vertices in this graph.
        @return  The number of vertices in the graph. */
    public int getNumberOfVertices();

    /** Gets the number of edges in this graph.
       @return  The number of edges in the graph. */
    public int getNumberOfEdges();

    /** Removes all vertices and edges from this graph resulting in an empty graph. */
    public void clear();
} // end BasicGraphInterface