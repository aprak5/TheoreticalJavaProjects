package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An AdjacencyMatrixGraph is an implementation of the {@link Graph} abstract
 * data type. AdjacencyMatrixGraph maintains a list of vertices in the graph and
 * a list of edges in the graph. In addition, AdjacencyMatrixGraph maintains a
 * 2-dimensional array to store edges based on the endpoints of the edges
 * 
 * The AdjacencyMatrixGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMatrixGraph<V, E> extends AbstractGraph<V, E> {
	/**The matrix of the graph edges.*/
    private GraphEdge[][] matrix;
    /**The list of vertices.*/
    private PositionalList<Vertex<V>> vertexList;
    /**The list of edges.*/
    private PositionalList<Edge<E>> edgeList;
    /**The vertex indexer for the graph.*/
    private int vertexIndexer;

    /**
     * Creates a new undirected adjacency matrix graph
     */
    public AdjacencyMatrixGraph() {
        this(false);
        vertexIndexer = 0;
    }

    /**
     * Creates a new adjacency matrix graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(boolean directed) {
        super(directed);
        matrix = (GraphEdge[][]) (new AbstractGraph.GraphEdge[0][0]);
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
        MatrixVertex v1 = validate(vertex1);
        MatrixVertex v2 = validate(vertex2);
        return matrix[v1.getIndex()][v2.getIndex()];
    }

    /**
     * The list of vertices of endpoints.
     * @param edge The edge to get endpoints for.
     * @return The list of vertices of endpoints.
     */
    @Override
    public Vertex<V>[] endVertices(Edge<E> edge) {
        GraphEdge e = validate(edge);
        return e.getEndpoints();
    }

    /**
     * The vertex opposite to a given vertex and edge.
     * @param vertex The vertex opposite for the given vertex. 
     * @param edge The edge opposite for the given vertex.
     * @return The vertex opposite to a given vertex and edge. 
     */
    @Override
    public Vertex<V> opposite(Vertex<V> vertex, Edge<E> edge) {
        GraphEdge temp = validate(edge);
        GraphVertex v = validate(vertex);
        Vertex<V>[] ends = temp.getEndpoints();
        if (ends[0] == v) {
            return ends[1];
        }
        if (ends[1] == v) {
            return ends[0];
        }
        throw new IllegalArgumentException("Vertex is not incident on this edge.");
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
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (GraphEdge e : matrix[v.getIndex()]) {
            if (e != null) {
                list.addLast(e);
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
        MatrixVertex v = validate(vertex);
        List<Edge<E>> list = new SinglyLinkedList<Edge<E>>();
        for (int i = 0; i < matrix.length; i++) {
            GraphEdge e = matrix[i][v.getIndex()];
            if (e != null) {
                list.addLast(e);
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
        MatrixVertex v = new MatrixVertex(vertexData);
        Position<Vertex<V>> pos = vertexList.addLast(v);
        v.setPosition(pos);
        growArray();
        return v;
    }

    /**
     * This grows the array of matrices of indices.
     */
    @SuppressWarnings("unchecked")
    private void growArray() {
        GraphEdge[][] temp = new AbstractGraph.GraphEdge[matrix.length + 1][matrix.length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                temp[i][j] = matrix[i][j];
            }
        }
        matrix = temp;
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
        MatrixVertex origin = validate(vertex1);
        MatrixVertex destination = validate(vertex2);
        GraphEdge e = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(e);
        e.setPosition(pos);
        matrix[origin.getIndex()][destination.getIndex()] = e;
        if (!isDirected()) {
            matrix[destination.getIndex()][origin.getIndex()] = e;
        }
        return e;
    }

    /**
     * This removes the vertex from the graph.
     * @param vertex This is the vertex to remove from the graph.
     */
    @Override
    public void removeVertex(Vertex<V> vertex) {
        GraphVertex v = validate(vertex);
        vertexList.remove(v.getPosition());
        int idx = validate(vertex).getIndex();
        for(int i = 0; i < matrix.length; i++) {
        	matrix[i][idx] = null;
        	matrix[idx][i] = null;
        }
        for(Edge<E> e: edgeList)
        	if(validate(e).getEndpoints()[0].equals(v) || validate(e).getEndpoints()[1].equals(v))
        		edgeList.remove(validate(e).getPosition());
    }

    /**
     * This removes the edge from the graph.
     * @param edge This is the edge to remove from the graph.
     */
    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        edgeList.remove(e.getPosition());
        Vertex<V>[] ends = e.getEndpoints();
        int origin = validate(ends[0]).getIndex();
        int dest = validate(ends[1]).getIndex();
        matrix[origin][dest] = null;
        matrix[dest][origin] = null;
    }

    /**
     * Safely casts a Vertex to a graph vertex
     * 
     * @return a graph vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid graph vertex
     */
    private MatrixVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMatrixGraph.MatrixVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency matrix vertex.");
        }
        return (MatrixVertex) v;
    }
    
    /**
     * Gets the index for the vertex.
     * @return The vertexIndexer for the graph.
     */
    private int getVertexIndex() {
        vertexIndexer++;
        return vertexIndexer - 1;
    }

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King and Amit Prakash
     *
     */
    private class MatrixVertex extends GraphVertex {

        /** The integer index of the vertex **/
        private int index;

        /**
         * Creates a new adjacency matrix vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public MatrixVertex(V data) {
            super(data);
            index = getVertexIndex();
        }

        /**
         * Returns the row/column index of the vertex in the matrix
         * 
         * @return the index of the vertex in the matrix
         */
        public int getIndex() {
            return index;
        }
    }
}