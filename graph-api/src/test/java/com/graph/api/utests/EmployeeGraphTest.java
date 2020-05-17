package com.graph.api.utests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.graph.api.Edge;

/**
 * Employee Graph test class
 * 
 * @author khadidja
 *
 */
public class EmployeeGraphTest {

	private GraphWrapper<Employee> graph;

	private static List<Edge<Employee>> PATH_PETERTASK_MICHELDUPONT = new LinkedList<Edge<Employee>>();
	private static List<Edge<Employee>> PATH_JOHNDOE_MICHELDUPONT = new LinkedList<Edge<Employee>>();
	private static List<Edge<Employee>> PATH_JOHNDOE_MARYDAVIS = new LinkedList<Edge<Employee>>();

	private static Employee JOHNDOE = new Employee(1, "John", "Doe", LocalDate.of(1990, 12, 5), 200.0);;
	private static Employee PETERTASK = new Employee(2, "Peter", "Task", LocalDate.of(1978, 10, 15), 200.0);;
	private static Employee MARYDAVIS = new Employee(3, "Mary", "Davis", LocalDate.of(1997, 1, 20), 200.0);;
	private static Employee THOMASDAVIS = new Employee(4, "Thomas", "Davis", LocalDate.of(2000, 5, 27), 200.0);;
	private static Employee MICHELDUPONT = new Employee(5, "Michel", "Dupont", LocalDate.of(1977, 5, 19), 200.0);;

	static {

		// Path PETER TASK-> MICHEL DUPONT
		PATH_PETERTASK_MICHELDUPONT.add(new Edge<Employee>(PETERTASK, MICHELDUPONT));

		// Path JOHN DOE-> MICHEL DUPONT
		PATH_JOHNDOE_MICHELDUPONT.add(new Edge<Employee>(JOHNDOE, MICHELDUPONT));

		// Path JOHN DOE-> MARY DAVIS
		PATH_JOHNDOE_MARYDAVIS.add(new Edge<Employee>(JOHNDOE, MICHELDUPONT));
		PATH_JOHNDOE_MARYDAVIS.add(new Edge<Employee>(MICHELDUPONT, THOMASDAVIS));
		PATH_JOHNDOE_MARYDAVIS.add(new Edge<Employee>(THOMASDAVIS, MARYDAVIS));
	}

	@Before
	public void setUp() {
		// bidirectional graph
		graph = new GraphWrapper<Employee>(true);
	}

	@Test
	public void testAddEdge() {
		populateEdges();
		assertEquals(7, graph.getEdgesCount(true));
	}

	@Test
	public void testAddVertices() {
		Employee johnDoe = new Employee(1, "John", "Doe", LocalDate.of(1990, 12, 5), 200.0);
		graph.addVertex(johnDoe);
		Employee peterTask = new Employee(2, "Peter", "Task", LocalDate.of(1978, 10, 15), 200.0);
		graph.addVertex(peterTask);

		assertTrue(graph.hasVertex(johnDoe));
		assertTrue(graph.hasVertex(peterTask));
		assertEquals(2, graph.getVertexCount());
	}

	@Test
	public void testGetPathOK1() throws Exception {
		populateEdges();
		assertEquals(PATH_PETERTASK_MICHELDUPONT, graph.getPath(PETERTASK, MICHELDUPONT));
	}

	@Test
	public void testGetPathOK2() throws Exception {
		populateEdges();
		assertEquals(PATH_PETERTASK_MICHELDUPONT, graph.getPath(PETERTASK, MICHELDUPONT));
		assertEquals(PATH_JOHNDOE_MICHELDUPONT, graph.getPath(JOHNDOE, MICHELDUPONT));
		assertEquals(PATH_JOHNDOE_MARYDAVIS, graph.getPath(JOHNDOE, MARYDAVIS));
	}

	@Test
	public void testGetPathOK3() throws Exception {
		populateEdges();
		assertEquals(PATH_PETERTASK_MICHELDUPONT, graph.getPath(PETERTASK, MICHELDUPONT));
		assertEquals(PATH_JOHNDOE_MICHELDUPONT, graph.getPath(JOHNDOE, MICHELDUPONT));
		assertEquals(PATH_JOHNDOE_MARYDAVIS, graph.getPath(JOHNDOE, MARYDAVIS));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPathSourceIsNull() throws Exception {
		graph.getPath(null, MICHELDUPONT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPathSourceDoesNotExist() throws Exception {
		graph.getPath(new Employee(100, "Toto", "Titi", LocalDate.of(1970, 12, 12), 1000.00), MICHELDUPONT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPathDestinationIsNull() throws Exception {
		graph.getPath(MICHELDUPONT, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPathDestinationDoesNotExist() throws Exception {
		graph.getPath(MICHELDUPONT, new Employee(100, "Toto", "Titi", LocalDate.of(1970, 12, 12), 1000.00));
	}

	@Test
	public void testGetPathSameSourceDestinationVertices() throws Exception {
		graph.addEdge(JOHNDOE, JOHNDOE);
		assertEquals((new Edge<Employee>(JOHNDOE, JOHNDOE)), graph.getPath(JOHNDOE, JOHNDOE).get(0));

	}

	private void populateEdges() {
		// bidirectional graph
		graph.addEdge(JOHNDOE, PETERTASK);
		graph.addEdge(JOHNDOE, MICHELDUPONT);
		graph.addEdge(PETERTASK, MARYDAVIS);
		graph.addEdge(PETERTASK, THOMASDAVIS);
		graph.addEdge(PETERTASK, MICHELDUPONT);
		graph.addEdge(MARYDAVIS, THOMASDAVIS);
		graph.addEdge(THOMASDAVIS, MICHELDUPONT);
	}

}
