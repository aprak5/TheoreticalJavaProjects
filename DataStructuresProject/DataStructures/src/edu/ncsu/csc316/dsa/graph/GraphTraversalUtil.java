package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * GraphTraversalUtil provides a collection of behaviors for traversing graphs,
 * including depth-first search and breadth-first search.
 * 
 * The GraphTraversalUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 */
public class GraphTraversalUtil {
    
    /**
     * Returns a map of discovery edges that represent a depth-first search
     * traversal of the given graph from a given starting vertex.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph a graph to traverse
     * @param start the vertex at which to start the depth-first search traversal
     * @return a map of discovery edges that were used to discover vertices in the
     *         graph
     */
    public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
        Set<Vertex<V>> known = new HashSet<>();
    	Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<>();
    	dfsHelper(graph, start, known, forest); // (re)start the DFS process at u
    	return forest;
    }
    
    /**
     * This helps do the actual traversal depth-first in the given graph from the starting vertex, given a set of vertices and forest of edges.
     * @param <V> The vertex value.
     * @param <E> The edge value.
     * @param graph The graph to traverse through.
     * @param u The vertex at which to start the depth-first traversal.
     * @param known The known vertices' set.
     * @param forest The forest of edges traversed along.
     */
    private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        known.add(u);
    	for(Edge<E> e : graph.outgoingEdges(u)) {
    		Vertex<V> v = graph.opposite(u, e);
    		if(!known.contains(v)) {
    			forest.put(v, e);
    			dfsHelper(graph, v, known, forest);
    		}
    	}
    }

    /**
     * Returns a map of discovery edges that represent a breadth-first search
     * traversal of the given graph from a given starting vertex.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph a graph to traverse
     * @param start the vertex at which to start the breadth-first search traversal
     * @return a map of discovery edges that were used to discover vertices in the
     *         graph
     */    
    public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
        Set<Vertex<V>> known = new HashSet<>();
     	Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<>();
    	Queue<Vertex<V>> reachableVerts = new ArrayBasedQueue<Vertex<V>>();
    	
    	known.add(start);
    	reachableVerts.enqueue(start);
    	while(!reachableVerts.isEmpty()) {
    		Vertex<V> u = reachableVerts.dequeue();
    		for(Edge<E> e : graph.outgoingEdges(u)) {
    			Vertex<V> w = graph.opposite(u, e);
    			if(!known.contains(w)) {
    				known.add(w);
    				forest.put(w, e);
    				reachableVerts.enqueue(w);
    			}
    		}
    	}
    	return forest;
    }
}
