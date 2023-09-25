package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * An AdjacencyMapGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyMapGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyMapGraph vertices each maintain
 * maps of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyMapGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {
	/**The list of vertices.*/
    private PositionalList<Vertex<V>> vertexList;
    /**The list of edges.*/
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * Creates a new undirected adjacency map graph
     */
    public AdjacencyMapGraph() {
        this(false);
    }

    /**
     * Creates a new adjacency map graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */    
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    /**
     * The number of vertices in the graph.
     * @return The size of the vertexList.
     */
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    /**
     * The iterable object for the vertices in the graph.
     * @return The vertexList for the graph.
     */
    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    /**
     * The number of edges in the graph.
     * @return The size of the edgeList.
     */
    @Override
    public int numEdges() {
        return edgeList.size();
    }

    /**
     * The iterable object for the edges in the graph.
     * @return The edgeList for the graph.
     */
    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    /**
     * The edge between the given vertices.
     * @param vertex1 The first vertex for the edge.
     * @param vertex2 The second vertex for the edge.
     * @return The edge for the two vertices.
     */
    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }

    /**
     * The out degree for the given vertex.
     * @param vertex The vertex to get the out degree for.
     * @return The out degree for the vertex.
     */
    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    /**
     * The in degree for the given vertex.
     * @param vertex The vertex to get the in degree for.
     * @return The in degree for the vertex.
     */
    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    /**
     * The outgoing edges for a given vertex.
     * @param vertex The vertex for the outgoing edges.
     * @return The list of outgoing edges for the vertex.
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    /**
     * The incoming edges for a given vertex.
     * @param vertex The vertex for the incoming edges.
     * @return The list of incoming edges for the vertex.
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }
    
    /**
     * This inserts a vertex with vertexData in the graph.
     * @param vertexData The element to insert in the graph.
     * @return The vertex with the vertexData.
     */
    @Override
    public Vertex<V> insertVertex(V vertexData) {
        AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        return vertex;
    }

    /**
     * This inserts a edge with edgeData in the graph with two endpoints: vertex1 and vertex2.
     * @param v1 The first vertex with the edge, one of the endpoints.
     * @param v2 The second vertex with the edge, one of the endpoints.
     * @param edgeData The element to insert in the graph.
     * @return The edge with the edgeData.
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        AMVertex origin = validate(v1);
        AMVertex destination = validate(v2);
        GraphEdge edge = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        // Remember to add the edge to the maps for each endpoint vertex
        origin.getOutgoing().put(v2, edge);
        destination.getIncoming().put(v1, edge);
        return edge;
    }

    /**
     * This removes the vertex from the graph.
     * @param vertex This is the vertex to remove from the graph.
     */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);
        for(Edge<E> e : v.getOutgoing().values())
        	removeEdge(e);
        for(Edge<E> e : v.getIncoming().values())
        	removeEdge(e);
        // remove this vertex from the list of vertices
        vertexList.remove(v.getPosition());
    }

    /**
     * This removes the edge from the graph.
     * @param edge This is the edge to remove from the graph.
     */
    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        AMVertex origin = validate(ends[0]);
        AMVertex dest = validate(ends[1]);
        try {
        edgeList.remove(e.getPosition());
        } catch(NullPointerException npe) {
        	//nothing
        }
        if(origin.getOutgoing().get(origin) != null)
        	origin.getOutgoing().remove(origin);
        if(dest.getIncoming().get(dest) != null)
        	dest.getIncoming().remove(dest);
    }
    
    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    private class AMVertex extends GraphVertex {

        /**
         * A map of outgoing edges -- <opposite vertex, edge to reach opposite vertex>
         */
        private Map<Vertex<V>, Edge<E>> outgoing;

        /**
         * A map of incoming edges -- <opposite vertex, edge to reach opposite vertex>
         */
        private Map<Vertex<V>, Edge<E>> incoming;

        /**
         * Creates a new adjacency map vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if (isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a map of outgoingEdges from the vertex. For an undirected graph,
         * returns the same as getIncoming()
         * 
         * @return a map of outgoing edges from the vertex
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        /**
         * Returns a map of incomingEdges to the vertex. For an undirected graph,
         * returns the same as getOutgoing()
         * 
         * @return a map of incoming edges to the vertex
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }

    /**
     * Safely casts a Vertex to an adjacency map vertex
     * 
     * @return an adjacency map vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency map
     *                                  vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex) v;
    }
}