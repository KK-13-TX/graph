package com.graph.api.utests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graph.api.Edge;

/**
 * Simple Integer Graph test class
 * 
 * @author khadidja
 *
 */
public class SimpleGraphTest {

	private GraphWrapper<Integer> graph;

	private static List<Edge<Integer>> PATH_1_4 = new LinkedList<Edge<Integer>>();
	private static List<Edge<Integer>> PATH_0_4 = new LinkedList<Edge<Integer>>();
	private static List<Edge<Integer>> PATH_0_2 = new LinkedList<Edge<Integer>>();
	static {
		// Path 1->4
		PATH_1_4.add(new Edge<Integer>(1, 4));

		// Path 0->4
		PATH_0_4.add(new Edge<Integer>(0, 4));

		// Path 0->2
		PATH_0_2.add(new Edge<Integer>(0, 4));
		PATH_0_2.add(new Edge<Integer>(4, 1));
		PATH_0_2.add(new Edge<Integer>(1, 2));
	}

	@Before
	public void setUp() {
		// unidirectional graph
		graph = new GraphWrapper<Integer>(false);
	}

	@Test
	public void testAddEdges() {
		populateEdges();
		assertEquals(7, graph.getEdgesCount(false));
	}

	@Test
	public void testAddVertices() {
		graph.addVertex(9);
		graph.addVertex(10);
		assertTrue(graph.hasVertex(9));
		assertTrue(graph.hasVertex(10));
		assertEquals(2, graph.getVertexCount());
	}

	@Test
	public void testGetPathOK1() throws Exception {
		populateEdges();
		assertEquals(PATH_1_4, graph.getPath(1, 4));
	}

	@Test
	public void testGetPathOK2() throws Exception {
		populateEdges();
		assertEquals(PATH_0_4, graph.getPath(0, 4));
	}

	@Test
	public void testGetPathOK3() throws Exception {
		populateEdges();
		assertEquals(PATH_0_2, graph.getPath(0, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getPathVertexDoesNotExist() throws Exception {
		graph.getPath(8, 4);
	}

	private void populateEdges() {
		// unidirectional graph
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
	}

}
