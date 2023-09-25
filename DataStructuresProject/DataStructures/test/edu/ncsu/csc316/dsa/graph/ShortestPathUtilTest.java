/**
 * 
 */
package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Highway;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the ShortestPathUtil class.
 * @author Amit Prakash
 *
 */
public class ShortestPathUtilTest {

	/**Test Undirected Graph*/
    private Graph<String, Highway> undirectedGraph;
    /**Test Directed Graph*/
    private Graph<String, Highway> directedGraph;
    
    /**
     * Create a new instance of an adjacency list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, Highway>();
        directedGraph = new AdjacencyListGraph<String, Highway>(true);
    }

    /**
     * Tests dijkstra().
     */
	@Test
	public void testDijkstra() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, new Highway("I-5", 5));
        undirectedGraph.insertEdge(v1, v3, new Highway("I-10", 10));
        undirectedGraph.insertEdge(v1, v4, new Highway("I-15", 15));
        undirectedGraph.insertEdge(v1, v5, new Highway("I-20", 20));
        undirectedGraph.insertEdge(v2, v3, new Highway("I-25", 25));
        undirectedGraph.insertEdge(v2, v4, new Highway("I-30", 30));
        undirectedGraph.insertEdge(v2, v5, new Highway("I-35", 35));
        undirectedGraph.insertEdge(v3, v4, new Highway("I-40", 40));
        undirectedGraph.insertEdge(v3, v5, new Highway("I-45", 45));
        undirectedGraph.insertEdge(v4, v5, new Highway("I-50", 50));
        
        Vertex<String> dv1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> dv2 = directedGraph.insertVertex("Asheville");
        Vertex<String> dv3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> dv4 = directedGraph.insertVertex("Durham");
        Vertex<String> dv5 = directedGraph.insertVertex("Greenville");
        Vertex<String> dv6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(dv1, dv2, new Highway("I-5", 5));
        directedGraph.insertEdge(dv1, dv3, new Highway("I-10", 10));
        directedGraph.insertEdge(dv1, dv4, new Highway("I-15", 15));
        directedGraph.insertEdge(dv1, dv5, new Highway("I-20", 20));
        directedGraph.insertEdge(dv2, dv3, new Highway("I-25", 25));
        directedGraph.insertEdge(dv2, dv4, new Highway("I-30", 30));
        directedGraph.insertEdge(dv2, dv5, new Highway("I-35", 35));
        directedGraph.insertEdge(dv3, dv4, new Highway("I-40", 40));
        directedGraph.insertEdge(dv3, dv5, new Highway("I-45", 45));
        directedGraph.insertEdge(dv4, dv5, new Highway("I-50", 50));
        directedGraph.insertEdge(dv5, dv6, new Highway("I-55", 55));
        
		Map<Vertex<String>, Integer> outputUndirected = ShortestPathUtil.dijkstra(undirectedGraph, v1);
		Map<Vertex<String>, Integer> outputDirected = ShortestPathUtil.dijkstra(directedGraph, dv1);
		assertNotNull(outputUndirected);
		assertNotNull(outputDirected);
	}
	
	/**
	 * Tests shortestPathTree().
	 */
	@Test
	public void testShortestPathTree() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, new Highway("I-5", 5));
        undirectedGraph.insertEdge(v1, v3, new Highway("I-10", 10));
        undirectedGraph.insertEdge(v1, v4, new Highway("I-15", 15));
        undirectedGraph.insertEdge(v1, v5, new Highway("I-20", 20));
        undirectedGraph.insertEdge(v2, v3, new Highway("I-25", 25));
        undirectedGraph.insertEdge(v2, v4, new Highway("I-30", 30));
        undirectedGraph.insertEdge(v2, v5, new Highway("I-35", 35));
        undirectedGraph.insertEdge(v3, v4, new Highway("I-40", 40));
        undirectedGraph.insertEdge(v3, v5, new Highway("I-45", 45));
        undirectedGraph.insertEdge(v4, v5, new Highway("I-50", 50));
        
        Vertex<String> dv1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> dv2 = directedGraph.insertVertex("Asheville");
        Vertex<String> dv3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> dv4 = directedGraph.insertVertex("Durham");
        Vertex<String> dv5 = directedGraph.insertVertex("Greenville");
        Vertex<String> dv6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(dv1, dv2, new Highway("I-5", 5));
        directedGraph.insertEdge(dv1, dv3, new Highway("I-10", 10));
        directedGraph.insertEdge(dv1, dv4, new Highway("I-15", 15));
        directedGraph.insertEdge(dv1, dv5, new Highway("I-20", 20));
        directedGraph.insertEdge(dv2, dv3, new Highway("I-25", 25));
        directedGraph.insertEdge(dv2, dv4, new Highway("I-30", 30));
        directedGraph.insertEdge(dv2, dv5, new Highway("I-35", 35));
        directedGraph.insertEdge(dv3, dv4, new Highway("I-40", 40));
        directedGraph.insertEdge(dv3, dv5, new Highway("I-45", 45));
        directedGraph.insertEdge(dv4, dv5, new Highway("I-50", 50));
        directedGraph.insertEdge(dv5, dv6, new Highway("I-55", 55));
        
        Map<Vertex<String>, Integer> outputMapUndirected = ShortestPathUtil.dijkstra(undirectedGraph, v1);
		Map<Vertex<String>, Integer> outputMapDirected = ShortestPathUtil.dijkstra(directedGraph, dv1);
        
		Map<Vertex<String>, Edge<Highway>> outputUndirected = ShortestPathUtil.shortestPathTree(undirectedGraph, v1, outputMapUndirected);
		Map<Vertex<String>, Edge<Highway>> outputDirected = ShortestPathUtil.shortestPathTree(directedGraph, dv1, outputMapDirected);
		assertNotNull(outputUndirected);
		assertNotNull(outputDirected);
	}

}
