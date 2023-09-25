package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Test class for AdjacencyMapGraph
 * Checks the expected outputs of the Graph abstract data type behaviors when using
 * an adjacency map graph data structure
 *
 * @author Dr. King and Amit Prakash
 *
 */
public class AdjacencyMapGraphTest {
	/**Test Undirected Graph*/
    private Graph<String, Integer> undirectedGraph;
    /**Test Directed Graph*/
    private Graph<String, Integer> directedGraph;
    
    /**
     * Create a new instance of an adjacency map graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyMapGraph<String, Integer>();
        directedGraph = new AdjacencyMapGraph<String, Integer>(true);
    }
    
    /**
     * Builds the undirected graph.
     */
    private void buildUndirectedSample() {
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
    }
    
    /**
     * Builds the directed graph.
     */
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }

    /**
     * Test the output of the numVertices() behavior
     */     
    @Test
    public void testNumVertices() {
        buildUndirectedSample();
        assertEquals(undirectedGraph.numVertices(), 5);
        
        buildDirectedSample();       
        assertEquals(directedGraph.numVertices(), 6);
    }

    /**
     * Test the output of the vertices() behavior
     */ 
    @Test
    public void testVertices() {
        // We cannot call buildUndirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing
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
        
        Iterator<Vertex<String>> vertIt = undirectedGraph.vertices().iterator();
        
        assertEquals(vertIt.next().getElement(), "Raleigh");
        assertEquals(vertIt.next().getElement(), "Asheville");
        assertEquals(vertIt.next().getElement(), "Wilmington");
        assertEquals(vertIt.next().getElement(), "Durham");
        assertEquals(vertIt.next().getElement(), "Greenville");
        
        
        
        // DIRECTED
        // We cannot call buildDirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing     
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Vertex<String>> vertIt2 = directedGraph.vertices().iterator();
        
        assertEquals(vertIt2.next().getElement(), "Raleigh");
        assertEquals(vertIt2.next().getElement(), "Asheville");
        assertEquals(vertIt2.next().getElement(), "Wilmington");
        assertEquals(vertIt2.next().getElement(), "Durham");
        assertEquals(vertIt2.next().getElement(), "Greenville");
        assertEquals(vertIt2.next().getElement(), "Boone");
    }

    /**
     * Test the output of the numEdges() behavior
     */ 
    @Test
    public void testNumEdges() {
    	buildUndirectedSample();
    	buildDirectedSample();
        assertEquals(undirectedGraph.numEdges(), 10);
        assertEquals(directedGraph.numEdges(), 11);
    }

    /**
     * Test the output of the edges() behavior
     */ 
    @Test
    public void testEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Edge<Integer>> edgeIt = undirectedGraph.edges().iterator();
        
        assertEquals(edgeIt.next(), e1);
        assertEquals(edgeIt.next(), e2);
        assertEquals(edgeIt.next(), e3);
        assertEquals(edgeIt.next(), e4);
        assertEquals(edgeIt.next(), e5);
        assertEquals(edgeIt.next(), e6);
        assertEquals(edgeIt.next(), e7);
        assertEquals(edgeIt.next(), e8);
        assertEquals(edgeIt.next(), e9);
        assertEquals(edgeIt.next(), e10);
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Edge<Integer>> edgeIt2 = directedGraph.edges().iterator();
        
        assertEquals(edgeIt2.next(), e1);
        assertEquals(edgeIt2.next(), e2);
        assertEquals(edgeIt2.next(), e3);
        assertEquals(edgeIt2.next(), e4);
        assertEquals(edgeIt2.next(), e5);
        assertEquals(edgeIt2.next(), e6);
        assertEquals(edgeIt2.next(), e7);
        assertEquals(edgeIt2.next(), e8);
        assertEquals(edgeIt2.next(), e9);
        assertEquals(edgeIt2.next(), e10);
        assertEquals(edgeIt2.next(), e11);
    }

    /**
     * Test the output of the getEdge(v1,v2) behavior
     */ 
    @Test
    public void testGetEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(undirectedGraph.getEdge(v1, v2), e1);
        assertEquals(undirectedGraph.getEdge(v1, v3), e2);
        assertEquals(undirectedGraph.getEdge(v1, v4), e3);
        assertEquals(undirectedGraph.getEdge(v1, v5), e4);
        assertEquals(undirectedGraph.getEdge(v2, v3), e5);
        assertEquals(undirectedGraph.getEdge(v2, v4), e6);
        assertEquals(undirectedGraph.getEdge(v2, v5), e7);
        assertEquals(undirectedGraph.getEdge(v3, v4), e8);
        assertEquals(undirectedGraph.getEdge(v3, v5), e9);
        assertEquals(undirectedGraph.getEdge(v4, v5), e10);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(directedGraph.getEdge(v1, v2), e1);
        assertEquals(directedGraph.getEdge(v1, v3), e2);
        assertEquals(directedGraph.getEdge(v1, v4), e3);
        assertEquals(directedGraph.getEdge(v1, v5), e4);
        assertEquals(directedGraph.getEdge(v2, v3), e5);
        assertEquals(directedGraph.getEdge(v2, v4), e6);
        assertEquals(directedGraph.getEdge(v2, v5), e7);
        assertEquals(directedGraph.getEdge(v3, v4), e8);
        assertEquals(directedGraph.getEdge(v3, v5), e9);
        assertEquals(directedGraph.getEdge(v4, v5), e10);
        assertEquals(directedGraph.getEdge(v5, v6), e11);
    }

    /**
     * Test the output of the endVertices(e) behavior
     */ 
    @Test
    public void testEndVertices() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(undirectedGraph.endVertices(e1)[0], v1);
        assertEquals(undirectedGraph.endVertices(e1)[1], v2);
        assertEquals(undirectedGraph.endVertices(e2)[0], v1);
        assertEquals(undirectedGraph.endVertices(e2)[1], v3);
        assertEquals(undirectedGraph.endVertices(e3)[0], v1);
        assertEquals(undirectedGraph.endVertices(e3)[1], v4);
        assertEquals(undirectedGraph.endVertices(e4)[0], v1);
        assertEquals(undirectedGraph.endVertices(e4)[1], v5);
        assertEquals(undirectedGraph.endVertices(e5)[0], v2);
        assertEquals(undirectedGraph.endVertices(e5)[1], v3);
        assertEquals(undirectedGraph.endVertices(e6)[0], v2);
        assertEquals(undirectedGraph.endVertices(e6)[1], v4);
        assertEquals(undirectedGraph.endVertices(e7)[0], v2);
        assertEquals(undirectedGraph.endVertices(e7)[1], v5);
        assertEquals(undirectedGraph.endVertices(e8)[0], v3);
        assertEquals(undirectedGraph.endVertices(e8)[1], v4);
        assertEquals(undirectedGraph.endVertices(e9)[0], v3);
        assertEquals(undirectedGraph.endVertices(e9)[1], v5);
        assertEquals(undirectedGraph.endVertices(e10)[0], v4);
        assertEquals(undirectedGraph.endVertices(e10)[1], v5);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(directedGraph.endVertices(e1)[0], v1);
        assertEquals(directedGraph.endVertices(e1)[1], v2);
        assertEquals(directedGraph.endVertices(e2)[0], v1);
        assertEquals(directedGraph.endVertices(e2)[1], v3);
        assertEquals(directedGraph.endVertices(e3)[0], v1);
        assertEquals(directedGraph.endVertices(e3)[1], v4);
        assertEquals(directedGraph.endVertices(e4)[0], v1);
        assertEquals(directedGraph.endVertices(e4)[1], v5);
        assertEquals(directedGraph.endVertices(e5)[0], v2);
        assertEquals(directedGraph.endVertices(e5)[1], v3);
        assertEquals(directedGraph.endVertices(e6)[0], v2);
        assertEquals(directedGraph.endVertices(e6)[1], v4);
        assertEquals(directedGraph.endVertices(e7)[0], v2);
        assertEquals(directedGraph.endVertices(e7)[1], v5);
        assertEquals(directedGraph.endVertices(e8)[0], v3);
        assertEquals(directedGraph.endVertices(e8)[1], v4);
        assertEquals(directedGraph.endVertices(e9)[0], v3);
        assertEquals(directedGraph.endVertices(e9)[1], v5);
        assertEquals(directedGraph.endVertices(e10)[0], v4);
        assertEquals(directedGraph.endVertices(e10)[1], v5);
        assertEquals(directedGraph.endVertices(e11)[0], v5);
        assertEquals(directedGraph.endVertices(e11)[1], v6);
    }

    /**
     * Test the output of the opposite(v, e) behavior
     */ 
    @Test
    public void testOpposite() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(undirectedGraph.opposite(v1, e1), v2);
        assertEquals(undirectedGraph.opposite(v1, e2), v3);
        assertEquals(undirectedGraph.opposite(v1, e3), v4);
        assertEquals(undirectedGraph.opposite(v1, e4), v5);
        assertEquals(undirectedGraph.opposite(v2, e5), v3);
        assertEquals(undirectedGraph.opposite(v2, e6), v4);
        assertEquals(undirectedGraph.opposite(v2, e7), v5);
        assertEquals(undirectedGraph.opposite(v3, e8), v4);
        assertEquals(undirectedGraph.opposite(v3, e9), v5);
        assertEquals(undirectedGraph.opposite(v4, e10), v5);
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(directedGraph.opposite(v1, e1), v2);
        assertEquals(directedGraph.opposite(v1, e2), v3);
        assertEquals(directedGraph.opposite(v1, e3), v4);
        assertEquals(directedGraph.opposite(v1, e4), v5);
        assertEquals(directedGraph.opposite(v2, e5), v3);
        assertEquals(directedGraph.opposite(v2, e6), v4);
        assertEquals(directedGraph.opposite(v2, e7), v5);
        assertEquals(directedGraph.opposite(v3, e8), v4);
        assertEquals(directedGraph.opposite(v3, e9), v5);
        assertEquals(directedGraph.opposite(v4, e10), v5);
        assertEquals(directedGraph.opposite(v5, e11), v6);
    }

    /**
     * Test the output of the outDegree(v) behavior
     */ 
    @SuppressWarnings("unused")
	@Test
    public void testOutDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.outDegree(v1));
        assertEquals(4, undirectedGraph.outDegree(v2));
        assertEquals(4, undirectedGraph.outDegree(v3));
        assertEquals(4, undirectedGraph.outDegree(v4));
        assertEquals(4, undirectedGraph.outDegree(v5));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(4, directedGraph.outDegree(v1));
        assertEquals(3, directedGraph.outDegree(v2));
        assertEquals(2, directedGraph.outDegree(v3));
        assertEquals(1, directedGraph.outDegree(v4));
        assertEquals(1, directedGraph.outDegree(v5));
        assertEquals(0, directedGraph.outDegree(v6));
    }

    /**
     * Test the output of the inDegree(v) behavior
     */ 
    @SuppressWarnings("unused")
	@Test
    public void testInDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.inDegree(v1));
        assertEquals(4, undirectedGraph.inDegree(v2));
        assertEquals(4, undirectedGraph.inDegree(v3));
        assertEquals(4, undirectedGraph.inDegree(v4));
        assertEquals(4, undirectedGraph.inDegree(v5));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(0, directedGraph.inDegree(v1));
        assertEquals(1, directedGraph.inDegree(v2));
        assertEquals(2, directedGraph.inDegree(v3));
        assertEquals(3, directedGraph.inDegree(v4));
        assertEquals(4, directedGraph.inDegree(v5));
        assertEquals(1, directedGraph.inDegree(v6));
    }

    /**
     * Test the output of the outgoingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testOutgoingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        Edge<Integer>[] temp2 = (Edge<Integer>[])(new Edge[4]);
        int count2 = 0;
        Iterator<Edge<Integer>> it2 = undirectedGraph.outgoingEdges(v2).iterator();
        assertTrue(it2.hasNext());
        temp2[count2] = it2.next();
        count2++;
        temp2[count2] = it2.next();
        count2++;
        temp2[count2] = it2.next();
        count2++;
        temp2[count2] = it2.next();
        count2++;
        assertFalse(it2.hasNext());
        assertTrue(arrayContains(temp2, e1));
        assertTrue(arrayContains(temp2, e5));
        assertTrue(arrayContains(temp2, e6));
        assertTrue(arrayContains(temp2, e7));
        
        Edge<Integer>[] temp3 = (Edge<Integer>[])(new Edge[4]);
        int count3 = 0;
        Iterator<Edge<Integer>> it3 = undirectedGraph.outgoingEdges(v3).iterator();
        assertTrue(it3.hasNext());
        temp3[count3] = it3.next();
        count3++;
        temp3[count3] = it3.next();
        count3++;
        temp3[count3] = it3.next();
        count3++;
        temp3[count3] = it3.next();
        count3++;
        assertFalse(it3.hasNext());
        assertTrue(arrayContains(temp3, e2));
        assertTrue(arrayContains(temp3, e5));
        assertTrue(arrayContains(temp3, e8));
        assertTrue(arrayContains(temp3, e9));
        
        Edge<Integer>[] temp4 = (Edge<Integer>[])(new Edge[4]);
        int count4 = 0;
        Iterator<Edge<Integer>> it4 = undirectedGraph.outgoingEdges(v4).iterator();
        assertTrue(it4.hasNext());
        temp4[count4] = it4.next();
        count4++;
        temp4[count4] = it4.next();
        count4++;
        temp4[count4] = it4.next();
        count4++;
        temp4[count4] = it4.next();
        count4++;
        assertFalse(it4.hasNext());
        assertTrue(arrayContains(temp4, e3));
        assertTrue(arrayContains(temp4, e6));
        assertTrue(arrayContains(temp4, e8));
        assertTrue(arrayContains(temp4, e10));
        
        Edge<Integer>[] temp5 = (Edge<Integer>[])(new Edge[4]);
        int count5 = 0;
        Iterator<Edge<Integer>> it5 = undirectedGraph.outgoingEdges(v5).iterator();
        assertTrue(it5.hasNext());
        temp5[count5] = it5.next();
        count5++;
        temp5[count5] = it5.next();
        count5++;
        temp5[count5] = it5.next();
        count5++;
        temp5[count5] = it5.next();
        count5++;
        assertFalse(it5.hasNext());
        assertTrue(arrayContains(temp5, e4));
        assertTrue(arrayContains(temp5, e7));
        assertTrue(arrayContains(temp5, e9));
        assertTrue(arrayContains(temp5, e10));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        
        Edge<Integer>[] temp6 = (Edge<Integer>[])(new Edge[4]);
        int count6 = 0;
        Iterator<Edge<Integer>> it6 = directedGraph.outgoingEdges(v1).iterator();
        assertTrue(it6.hasNext());
        temp6[count6] = it6.next();
        count6++;
        temp6[count6] = it6.next();
        count6++;
        temp6[count6] = it6.next();
        count6++;
        temp6[count6] = it6.next();
        count6++;
        assertFalse(it6.hasNext());
        assertTrue(arrayContains(temp6, e1));
        assertTrue(arrayContains(temp6, e2));
        assertTrue(arrayContains(temp6, e3));
        assertTrue(arrayContains(temp6, e4));
        
        Edge<Integer>[] temp7 = (Edge<Integer>[])(new Edge[3]);
        int count7 = 0;
        Iterator<Edge<Integer>> it7 = directedGraph.outgoingEdges(v2).iterator();
        assertTrue(it7.hasNext());
        temp7[count7] = it7.next();
        count7++;
        temp7[count7] = it7.next();
        count7++;
        temp7[count7] = it7.next();
        count7++;
        assertFalse(it7.hasNext());
        assertTrue(arrayContains(temp7, e5));
        assertTrue(arrayContains(temp7, e6));
        assertTrue(arrayContains(temp7, e7));
        
        Edge<Integer>[] temp8 = (Edge<Integer>[])(new Edge[2]);
        int count8 = 0;
        Iterator<Edge<Integer>> it8 = directedGraph.outgoingEdges(v3).iterator();
        assertTrue(it8.hasNext());
        temp8[count8] = it8.next();
        count8++;
        temp8[count8] = it8.next();
        count8++;
        assertFalse(it8.hasNext());
        assertTrue(arrayContains(temp8, e8));
        assertTrue(arrayContains(temp8, e9));
        
        Edge<Integer>[] temp9 = (Edge<Integer>[])(new Edge[1]);
        int count9 = 0;
        Iterator<Edge<Integer>> it9 = directedGraph.outgoingEdges(v4).iterator();
        assertTrue(it9.hasNext());
        temp9[count9] = it9.next();
        count9++;
        assertFalse(it9.hasNext());
        assertTrue(arrayContains(temp9, e10));
        
        Edge<Integer>[] temp10 = (Edge<Integer>[])(new Edge[1]);
        int count10 = 0;
        Iterator<Edge<Integer>> it10 = directedGraph.outgoingEdges(v5).iterator();
        assertTrue(it10.hasNext());
        temp10[count10] = it10.next();
        count10++;
        assertFalse(it10.hasNext());
        assertTrue(arrayContains(temp10, e11));
        
        Iterator<Edge<Integer>> it11 = directedGraph.outgoingEdges(v6).iterator();
        assertFalse(it11.hasNext());     
    }
    
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
    private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
        for(Edge<Integer> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test the output of the incomingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testIncomingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
     // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.incomingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        Edge<Integer>[] temp2 = (Edge<Integer>[])(new Edge[4]);
        int count2 = 0;
        Iterator<Edge<Integer>> it2 = undirectedGraph.incomingEdges(v2).iterator();
        assertTrue(it2.hasNext());
        temp2[count2] = it2.next();
        count2++;
        temp2[count2] = it2.next();
        count2++;
        temp2[count2] = it2.next();
        count2++;
        temp2[count2] = it2.next();
        count2++;
        assertFalse(it2.hasNext());
        assertTrue(arrayContains(temp2, e1));
        assertTrue(arrayContains(temp2, e5));
        assertTrue(arrayContains(temp2, e6));
        assertTrue(arrayContains(temp2, e7));
        
        Edge<Integer>[] temp3 = (Edge<Integer>[])(new Edge[4]);
        int count3 = 0;
        Iterator<Edge<Integer>> it3 = undirectedGraph.incomingEdges(v3).iterator();
        assertTrue(it3.hasNext());
        temp3[count3] = it3.next();
        count3++;
        temp3[count3] = it3.next();
        count3++;
        temp3[count3] = it3.next();
        count3++;
        temp3[count3] = it3.next();
        count3++;
        assertFalse(it3.hasNext());
        assertTrue(arrayContains(temp3, e2));
        assertTrue(arrayContains(temp3, e5));
        assertTrue(arrayContains(temp3, e8));
        assertTrue(arrayContains(temp3, e9));
        
        Edge<Integer>[] temp4 = (Edge<Integer>[])(new Edge[4]);
        int count4 = 0;
        Iterator<Edge<Integer>> it4 = undirectedGraph.incomingEdges(v4).iterator();
        assertTrue(it4.hasNext());
        temp4[count4] = it4.next();
        count4++;
        temp4[count4] = it4.next();
        count4++;
        temp4[count4] = it4.next();
        count4++;
        temp4[count4] = it4.next();
        count4++;
        assertFalse(it4.hasNext());
        assertTrue(arrayContains(temp4, e3));
        assertTrue(arrayContains(temp4, e6));
        assertTrue(arrayContains(temp4, e8));
        assertTrue(arrayContains(temp4, e10));
        
        Edge<Integer>[] temp5 = (Edge<Integer>[])(new Edge[4]);
        int count5 = 0;
        Iterator<Edge<Integer>> it5 = undirectedGraph.incomingEdges(v5).iterator();
        assertTrue(it5.hasNext());
        temp5[count5] = it5.next();
        count5++;
        temp5[count5] = it5.next();
        count5++;
        temp5[count5] = it5.next();
        count5++;
        temp5[count5] = it5.next();
        count5++;
        assertFalse(it5.hasNext());
        assertTrue(arrayContains(temp5, e4));
        assertTrue(arrayContains(temp5, e7));
        assertTrue(arrayContains(temp5, e9));
        assertTrue(arrayContains(temp5, e10));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Edge<Integer>> it6 = directedGraph.incomingEdges(v1).iterator();
        assertFalse(it6.hasNext());
        
        Edge<Integer>[] temp7 = (Edge<Integer>[])(new Edge[1]);
        int count7 = 0;
        Iterator<Edge<Integer>> it7 = directedGraph.incomingEdges(v2).iterator();
        assertTrue(it7.hasNext());
        temp7[count7] = it7.next();
        count7++;
        assertFalse(it7.hasNext());
        assertTrue(arrayContains(temp7, e1));
        
        Edge<Integer>[] temp8 = (Edge<Integer>[])(new Edge[2]);
        int count8 = 0;
        Iterator<Edge<Integer>> it8 = directedGraph.incomingEdges(v3).iterator();
        assertTrue(it8.hasNext());
        temp8[count8] = it8.next();
        count8++;
        temp8[count8] = it8.next();
        count8++;
        assertFalse(it8.hasNext());
        assertTrue(arrayContains(temp8, e2));
        assertTrue(arrayContains(temp8, e5));
        
        Edge<Integer>[] temp9 = (Edge<Integer>[])(new Edge[3]);
        int count9 = 0;
        Iterator<Edge<Integer>> it9 = directedGraph.incomingEdges(v4).iterator();
        assertTrue(it9.hasNext());
        temp9[count9] = it9.next();
        count9++;
        temp9[count9] = it9.next();
        count9++;
        temp9[count9] = it9.next();
        count9++;
        assertFalse(it9.hasNext());
        assertTrue(arrayContains(temp9, e3));
        assertTrue(arrayContains(temp9, e6));
        assertTrue(arrayContains(temp9, e8));
        
        Edge<Integer>[] temp10 = (Edge<Integer>[])(new Edge[4]);
        int count10 = 0;
        Iterator<Edge<Integer>> it10 = directedGraph.incomingEdges(v5).iterator();
        assertTrue(it10.hasNext());
        temp10[count10] = it10.next();
        count10++;
        temp10[count10] = it10.next();
        count10++;
        temp10[count10] = it10.next();
        count10++;
        temp10[count10] = it10.next();
        count10++;
        assertFalse(it10.hasNext());
        assertTrue(arrayContains(temp10, e4));
        assertTrue(arrayContains(temp10, e7));
        assertTrue(arrayContains(temp10, e9));
        assertTrue(arrayContains(temp10, e10));
        
        Edge<Integer>[] temp11 = (Edge<Integer>[])(new Edge[1]);
        int count11 = 0;
        Iterator<Edge<Integer>> it11 = directedGraph.incomingEdges(v6).iterator();
        assertTrue(it11.hasNext());
        temp11[count11] = it11.next();
        count11++;
        assertFalse(it11.hasNext());
        assertTrue(arrayContains(temp11, e11));   
    }

    /**
     * Test the output of the insertVertex(x) behavior
     */ 
    @Test
    public void testInsertVertex() {
        assertEquals(0, undirectedGraph.numVertices());
        Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
        assertEquals(1, undirectedGraph.numVertices());
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());      
    }

    /**
     * Test the output of the insertEdge(v1, v2, x) behavior
     */ 
    @Test
    public void testInsertEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        
        assertEquals(0, undirectedGraph.numEdges());
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
        assertEquals(1, undirectedGraph.numEdges());
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the removeVertex(v) behavior
     */ 
    @SuppressWarnings("unused")
	@Test
    public void testRemoveVertex() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(5, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v5);
        assertEquals(4, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v4);
        assertEquals(3, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v3);
        assertEquals(2, undirectedGraph.numVertices());
        assertEquals(1, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v2);
        assertEquals(1, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v1);
        assertEquals(0, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        directedGraph.removeVertex(v6);
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeVertex(v5);
        assertEquals(4, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        directedGraph.removeVertex(v4);
        assertEquals(3, directedGraph.numVertices());
        assertEquals(3, directedGraph.numEdges());
        directedGraph.removeVertex(v3);
        assertEquals(2, directedGraph.numVertices());
        assertEquals(1, directedGraph.numEdges());
        directedGraph.removeVertex(v2);
        assertEquals(1, directedGraph.numVertices());
        assertEquals(0, directedGraph.numEdges());
        directedGraph.removeVertex(v1);
        assertEquals(0, directedGraph.numVertices());
        assertEquals(0, directedGraph.numEdges());
    }

    /**
     * Test the output of the removeEdge(e) behavior
     */ 
    @Test
    public void testRemoveEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e1);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(9, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e2);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(8, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e3);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(7, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e4);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e5);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(5, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e6);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(4, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e7);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(3, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e8);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(2, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e9);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(1, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e10);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        directedGraph.removeEdge(e1);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeEdge(e2);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(9, directedGraph.numEdges());
        directedGraph.removeEdge(e3);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(8, directedGraph.numEdges());
        directedGraph.removeEdge(e4);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(7, directedGraph.numEdges());
        directedGraph.removeEdge(e5);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        directedGraph.removeEdge(e6);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(5, directedGraph.numEdges());
        directedGraph.removeEdge(e7);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(4, directedGraph.numEdges());
        directedGraph.removeEdge(e8);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(3, directedGraph.numEdges());
        directedGraph.removeEdge(e9);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(2, directedGraph.numEdges());
        directedGraph.removeEdge(e10);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(1, directedGraph.numEdges());
        directedGraph.removeEdge(e11);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(0, directedGraph.numEdges());
    }
}