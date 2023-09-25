package edu.ncsu.csc316.dsa.graph;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An EdgeListGraph is an implementation of the {@link Graph} abstract data type.
 * EdgeListGraph maintains a list of vertices in the graph and a list of edges in the
 * graph.
 * 
 * The EdgeListGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class EdgeListGraph<V, E> extends AbstractGraph<V, E> {
	/**The list of vertices.*/
    private PositionalList<Vertex<V>> vertexList;
    /**The list of edges.*/
    private PositionalList<Edge<E>> edgeList;

    /**
     * Creates a new undirected edge list graph
     */
    public EdgeListGraph() {
        this(false);
    }

    /**
     * Creates a new edge list graph
     * @param directed if true, the graph is directed; if false, the graph is undirected
     */
    public EdgeListGraph(boolean directed) {
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
     * The validation for the given Vertex as a vertex for the graph.
     * @param v The vertex for the graph.
     * @return The vertex in the graph.
     * @throws java.lang.IllegalArgumentException If the vertex is not a valid one, IAE is thrown.
     */
    private GraphVertex validate(Vertex<V> v) throws IllegalArgumentException {
        if (!(v instanceof AbstractGraph.GraphVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid edge list vertex.");
        }
        return (GraphVertex) v;
    }

    /**
     * The edge between the given vertices.
     * @param vertex1 The first vertex for the edge.
     * @param vertex2 The second vertex for the edge.
     * @return The edge for the two vertices.
     */
    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        GraphVertex v1 = validate(vertex1);
        GraphVertex v2 = validate(vertex2);
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge current = validate(it.next());
            Vertex<V>[] ends = current.getEndpoints();
            if (!isDirected() && ends[1] == v1 && ends[0] == v2) {
                return current;
            }
            if (ends[0] == vertex1 && ends[1] == v2) {
                return current;
            }
        }
        return null;
    }

    /**
     * The out degree for the given vertex.
     * @param vertex The vertex to get the out degree for.
     * @return The out degree for the vertex.
     */
    @Override
    public int outDegree(Vertex<V> vertex) {
        return outgoingEdgeList(vertex).size();
    }

    /**
     * The outgoing edge list for the given vertex.
     * @param vertex The vertex to look for the outgoing edge list.
     * @return The outgoing edge list for the given vertex.
     */
    private List<Edge<E>> outgoingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if (ends[0] == v) {
                list.addLast(edge);
            } else if (!isDirected() && ends[1] == v) {
                list.addLast(edge);
            }
        }
        return list;
    }

    /**
     * The incoming edge list for the given vertex.
     * @param vertex The vertex to look for the incoming edge list.
     * @return The incoming edge list for the given vertex.
     */
    private List<Edge<E>> incomingEdgeList(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        Iterator<Edge<E>> it = edgeList.iterator();
        while (it.hasNext()) {
            GraphEdge edge = validate(it.next());
            Vertex<V>[] ends = edge.getEndpoints();
            if (ends[1] == v) {
                list.addLast(edge);
            } else if (!isDirected() && ends[0] == v) {
                list.addLast(edge);
            }
        }
        return list;
    }
    
    /**
     * The in degree for the given vertex.
     * @param vertex The vertex to get the in degree for.
     * @return The in degree for the vertex.
     */
    @Override
    public int inDegree(Vertex<V> vertex) {
        return incomingEdgeList(vertex).size();
    }

    /**
     * The outgoing edges for a given vertex.
     * @param vertex The vertex for the outgoing edges.
     * @return The list of outgoing edges for the vertex.
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return outgoingEdgeList(vertex);
    }

    /**
     * The incoming edges for a given vertex.
     * @param vertex The vertex for the incoming edges.
     * @return The list of incoming edges for the vertex.
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return incomingEdgeList(vertex);
    }

    /**
     * This inserts a vertex with vertexData in the graph.
     * @param vertexData The element to insert in the graph.
     * @return The vertex with the vertexData.
     */
    @Override
    public Vertex<V> insertVertex(V vertexData) {
        GraphVertex v = new GraphVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        v.setPosition(pos);
        return v;
    }

    /**
     * This inserts a edge with edgeData in the graph with two endpoints: vertex1 and vertex2.
     * @param vertex1 The first vertex with the edge, one of the endpoints.
     * @param vertex2 The second vertex with the edge, one of the endpoints.
     * @param edgeData The element to insert in the graph.
     * @return The edge with the edgeData.
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> vertex1, Vertex<V> vertex2, E edgeData) {
        GraphVertex origin = validate(vertex1);
        GraphVertex destination = validate(vertex2);
        GraphEdge e = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        return e;
    }

    /**
     * This removes the vertex from the graph.
     * @param vertex This is the vertex to remove from the graph.
     */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        for (Edge<E> e : outgoingEdges(v)) {
            removeEdge(e);
        }
        for (Edge<E> e : incomingEdges(v)) {
            removeEdge(e);
        }
        vertexList.remove(v.getPosition());
    }

    /**
     * This removes the edge from the graph.
     * @param edge This is the edge to remove from the graph.
     */
    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        edgeList.remove(e.getPosition());
    }
}