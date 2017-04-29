package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import structures.Graph;

//Tests for graph
public class GraphTests {

	@Test
	public void test() {
		Graph graph = new Graph(10);
		graph.addEdge(1, 5);
		
		assertEquals(graph.vertices[1].adjVerts.size(), 1);
		
		graph.addEdge(1, 6);
		graph.addEdge(1, 2);
		
		assertEquals(graph.vertices[1].adjVerts.size(), 3);
		assertEquals(graph.vertices[5].adjVerts.size(), 1);
		assertEquals(graph.vertices[6].adjVerts.size(), 1);
		
		
		graph.addEdge(2, 5);
		graph.addEdge(2, 6);
		graph.addEdge(2, 3);
		
		assertEquals(graph.vertices[2].adjVerts.size(), 4);
		assertEquals(graph.vertices[5].adjVerts.size(), 2);
		assertEquals(graph.vertices[6].adjVerts.size(), 2);
		
	}

}
