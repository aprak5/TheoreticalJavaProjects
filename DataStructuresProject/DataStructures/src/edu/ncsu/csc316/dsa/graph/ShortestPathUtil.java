package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King and Amit Prakash
 *
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
        // d.get(v) is upper bound on distance from src to v
    	Map<Vertex<V>, Integer> d = new LinearProbingHashMap<>();
    	// map reachable v to its d value
    	Map<Vertex<V>, Integer> cloud = new LinearProbingHashMap<>();
    	// pq will have vertices as elements, with d.get(v) as key
    	AdaptablePriorityQueue<Integer, Vertex<V>> pq;
    	pq = new HeapAdaptablePriorityQueue<>();
    	// maps from vertex to its pq locator
    	Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqTokens;
    	pqTokens = new LinearProbingHashMap<>();
    	
    	// for each vertex v of the graph, add an entry to the priority queue, with
    	// the source having distance 0 and all others having infinite distance
    	for (Vertex<V> v : graph.vertices()) {
    		if (v == start)
    			d.put(v, 0);
    		else
    			d.put(v, Integer.MAX_VALUE);
    	    pqTokens.put(v, pq.insert(d.get(v), v)); // save entry for future updates
        }
    	// now begin adding reachable vertices to the cloud
    	while (!pq.isEmpty()) {
    		Entry<Integer, Vertex<V>> entry = pq.deleteMin();
    		int key = entry.getKey( );
    		Vertex<V> u = entry.getValue( );
    		cloud.put(u, key); // this is actual distance to u
    		pqTokens.remove(u); // u is no longer in pq
    		for (Edge<E> e : graph.outgoingEdges(u)) {
    			Vertex<V> v = graph.opposite(u, e);
    			if (cloud.get(v) == null) {
    				// perform relaxation step on edge (u,v)
    				int wgt = e.getElement().getWeight();
    				if (d.get(u) + wgt < d.get(v)) { // better path to v?
    					d.put(v, d.get(u) + wgt); // update the distance
    					pq.replaceKey(pqTokens.get(v), d.get(v)); // update the pq entry
    				}
    			}
    		}
    	}
        return cloud;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
        Map<Vertex<V>, Edge<E>> tree = new LinearProbingHashMap<>();
    	for (Vertex<V> v : costs) {
    		if (v != start)
    			for (Edge<E> e : graph.incomingEdges(v)) { // consider INCOMING edges
    				Vertex<V> u = graph.opposite(v, e);
    				if (costs.get(v) == costs.get(u) + e.getElement().getWeight())
    					tree.put(v, e); // edge is is used to reach v
    			}
    	}
    	return tree;
    }   
}    