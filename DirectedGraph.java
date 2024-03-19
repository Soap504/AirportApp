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

public class DirectedGraph<T> implements GraphInterface<T>
{
    private DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;

    /**
     * Creates an empty DirectedGraph.
     */
    public DirectedGraph()
    {
        vertices = new HashedDictionary<>();
        edgeCount = 0;
    } // end default constructor

    
    /** Adds a given vertex to this graph.
        @param vertexLabel  An object that labels the new vertex and is
                            distinct from the labels of current vertices.
        @return  True if the vertex is added, or false if not. 
    */
    public boolean addVertex(T vertexLabel)
    {
        VertexInterface<T> addOutcome = vertices.add(vertexLabel, new Vertex(vertexLabel));
        return  addOutcome == null;
    } // end addVertex

    /** Adds a weighted edge between two given distinct vertices that 
        are currently in this graph. The desired edge must not already 
        be in the graph. In a directed graph, the edge points toward
        the second vertex given.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object, distinct from begin, that labels the end
                      vertex of the edge.
        @param edgeWeight  The real value of the edge's weight.
        @return  True if the edge is added, or false if not.
    */

    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        boolean result = false;
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        if ((originVertex != null) && (endVertex != null))
            result = originVertex.connect(endVertex, edgeWeight);        
        if (result)
            edgeCount++;
        
        return result;
    } // end addEdge with weight

    /** Adds an unweighted edge between two given distinct vertices 
        that are currently in this graph. The desired edge must not
        already be in the graph. In a directed graph, the edge points 
        toward the second vertex given.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object, distinct from begin, that labels the end
                      vertex of the edge.
        @return  True if the edge is added, or false if not. 
    */
    public boolean addEdge(T begin, T end)
    {
        return addEdge(begin, end, 0);
    } // end addEdge without weight

    /** Sees whether an edge exists between two given vertices.
        @param begin  An object that labels the origin vertex of the edge.
        @param end    An object that labels the end vertex of the edge.
        @return  True if an edge exists. */
    public boolean hasEdge(T begin, T end)
    {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        
        if ((beginVertex != null) && (endVertex != null))
        {
            Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborIterator();             
            while (!found && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (nextNeighbor.equals(endVertex))
                    found = true;
            } // end while
        } // end if
        return found;
    } // end hasEdge


    /** Sees whether this graph is empty.
        @return  True if the graph is empty. */
    public boolean isEmpty()
    {
        return vertices.isEmpty();
    } // end empty


    /** Gets the number of vertices in this graph.
        @return  The number of vertices in the graph. */
    public int getNumberOfVertices()
    {
        return vertices.getSize();
    } // end getNumberOfVertices


    /** Gets the number of edges in this graph.
       @return  The number of edges in the graph. */
    public int getNumberOfEdges()
    {
        return edgeCount;
    } // end getNumberOfEdges


    /** Removes all vertices and edges from this graph resulting in an empty graph. */
    public void clear()
    {
        vertices.clear();
        edgeCount = 0;
    } // end clear


    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBreadthFirstTraversal'");
    }


    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDepthFirstTraversal'");
    }


    @Override
    public StackInterface<T> getTopologicalOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTopologicalOrder'");
    }


    @Override
    public int getShortestPath(T begin, T end, StackInterface<T> path) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShortestPath'");
    }

    /** Finds the least-cost path between two given vertices in this graph.
        @param begin  An object that labels the path's origin vertex.
        @param end    An object that labels the path's destination vertex.
        @param path   A stack of labels that is empty initially;
                      at the completion of the method, this stack contains
                      the labels of the vertices along the cheapest path;
                      the label of the origin vertex is a the top, and
                      the label of the destination vertex is at the bottom       
        @return  The cost of the cheapest path. */
    public double getCheapestPath( T begin, T end, StackInterface<T> path)
    {
        resetVertices();
        boolean done = false;
        PriorityQueueInterface<EntryPQ> priorityQueue = new MinHeap<>();
                
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);
        priorityQueue.add(new EntryPQ(originVertex, 0, null));
        
        while(!done && !priorityQueue.isEmpty())
        {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();
            if (!frontVertex.isVisited())
            {
                frontVertex.visit();
                
                frontVertex.setCost(frontEntry.getWeight());
                frontVertex.setPredecessor(frontEntry.getPrevious());
                if (frontVertex.equals(endVertex))
                    done = true;
                else
                {
                    Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
                    Iterator<Double> line = frontVertex.getWeightIterator();
                    while (!done && neighbors.hasNext())
                    {
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        double weightOfEdgeToNeighbor = line.next();
                        if (!nextNeighbor.isVisited())
                        {
                            double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
                            priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
                        } // end if
                    } // end while
                } // end if
            } // end if           
        } // end while
        // Traversal ends; construct shortest path
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while
        return pathCost;
    } // end getCheapestPath
    
    /**
     * Resets the visited status, cost, and predecessor of all vertices in the graph.
     */
    protected void resetVertices()
    {
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);            
        } // end while
    } // end resetVertices

    /**
     * Represents an entry in the priority queue used in Dijkstra's algorithm.
     */
    private class EntryPQ implements Comparable<EntryPQ>
    {
        VertexInterface<T> currentVertex;
        double costToHere; // cost of the path to this vertex from origin vertex
        VertexInterface<T> previousVertex;  // previous vertex on that path
        
        /**
         * Constructs an entry with the given vertex, cost, and previous vertex.
         *
         * @param newVertex  The current vertex.
         * @param totalCost  The total cost of the path to the current vertex.
         * @param vertexFrom The previous vertex on the path.
        */
        public EntryPQ(VertexInterface<T> newVertex, double totalCost, VertexInterface<T> vertexFrom)
        {
            currentVertex = newVertex;
            costToHere = totalCost;
            previousVertex = vertexFrom;    
        } // end  constructor
        
        /**
         * Gets the weight (cost) of the path to the current vertex.
         *
         * @return The weight of the path.
        */
        public double getWeight()
        {
            return costToHere;
        } // end getCost
        
        /**
         * Gets the current vertex.
         *
         * @return The current vertex.
        */
        public VertexInterface<T> getVertex()
        {
            return currentVertex;
        } // end getVertex
        
        /**
         * Gets the previous vertex on the path.
         *
         * @return The previous vertex.
        */
        public VertexInterface<T> getPrevious()
        {
            return previousVertex;
        } // end getPrevious
        
        /**
         * Compares this entry with another entry based on their weights.
         *
         * @param other The other entry to compare with.
         * @return Negative if this entry has a lower weight, positive if higher, and 0 if equal.
        */
        public int compareTo(EntryPQ other)
        {
            int result = (int) (costToHere - other.getWeight());
            return result;
        } // end compareTo
    } // end EntryPQ

} // end DirectedGraph