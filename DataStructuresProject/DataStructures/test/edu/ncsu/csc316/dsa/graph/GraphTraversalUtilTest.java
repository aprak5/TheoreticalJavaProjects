/**
 * 
 */
package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Tests the GraphTraversalUtil class methods.
 * @author Amit Prakash
 *
 */
public class GraphTraversalUtilTest {

	/**Test Undirected Graph*/
    private Graph<String, Integer> undirectedGraph;
    /**Test Directed Graph*/
    private Graph<String, Integer> directedGraph;
    
    /**
     * Create a new instance of an adjacency list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, Integer>();
        directedGraph = new AdjacencyListGraph<String, Integer>(true);
    }
    
    /**
     * Tests breadthFirstSearch().
     */
	@Test
	public void testBFS() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        Vertex<String> dv1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> dv2 = directedGraph.insertVertex("Asheville");
        Vertex<String> dv3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> dv4 = directedGraph.insertVertex("Durham");
        Vertex<String> dv5 = directedGraph.insertVertex("Greenville");
        Vertex<String> dv6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(dv1, dv2, 5);
        directedGraph.insertEdge(dv1, dv3, 10);
        directedGraph.insertEdge(dv1, dv4, 15);
        directedGraph.insertEdge(dv1, dv5, 20);
        directedGraph.insertEdge(dv2, dv3, 25);
        directedGraph.insertEdge(dv2, dv4, 30);
        directedGraph.insertEdge(dv2, dv5, 35);
        directedGraph.insertEdge(dv3, dv4, 40);
        directedGraph.insertEdge(dv3, dv5, 45);
        directedGraph.insertEdge(dv4, dv5, 50);
        directedGraph.insertEdge(dv5, dv6, 55);
        
		Map<Vertex<String>, Edge<Integer>> outputUndirected = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v1);
		Map<Vertex<String>, Edge<Integer>> outputDirected = GraphTraversalUtil.breadthFirstSearch(directedGraph, dv1);
		assertNotNull(outputUndirected);
		assertNotNull(outputDirected);
	}

	/**
	 * Tests depthFirstSearch().
	 */
	@Test
	public void testDFS() {
		Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        Vertex<String> dv1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> dv2 = directedGraph.insertVertex("Asheville");
        Vertex<String> dv3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> dv4 = directedGraph.insertVertex("Durham");
        Vertex<String> dv5 = directedGraph.insertVertex("Greenville");
        Vertex<String> dv6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(dv1, dv2, 5);
        directedGraph.insertEdge(dv1, dv3, 10);
        directedGraph.insertEdge(dv1, dv4, 15);
        directedGraph.insertEdge(dv1, dv5, 20);
        directedGraph.insertEdge(dv2, dv3, 25);
        directedGraph.insertEdge(dv2, dv4, 30);
        directedGraph.insertEdge(dv2, dv5, 35);
        directedGraph.insertEdge(dv3, dv4, 40);
        directedGraph.insertEdge(dv3, dv5, 45);
        directedGraph.insertEdge(dv4, dv5, 50);
        directedGraph.insertEdge(dv5, dv6, 55);
		Map<Vertex<String>, Edge<Integer>> outputUndirected = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v1);
		Map<Vertex<String>, Edge<Integer>> outputDirected = GraphTraversalUtil.depthFirstSearch(directedGraph, dv1);
		assertNotNull(outputUndirected);
		assertNotNull(outputDirected);
	}
}
