package com.graph.api;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Graph class represents Graph entity that handles vertices and edges. Provides
 * actions on Graph - addVertex(T) - addEdge(T) - getPath(T,T)
 * 
 * @author khadidja
 *
 * @param <T> Generic type
 */
public class Graph<T> {

	protected Map<T, LinkedList<Edge<T>>> vertices = new HashMap<>();

	/**
	 * define if the traverse of the graph is bidirectional or not
	 */
	private boolean isBidirectional;

	public Graph(boolean isBidirectional) {
		this.isBidirectional = isBidirectional;
	}

	/**
	 * Add a vertex into graphMap
	 * 
	 * @param vertex Generic type T
	 */
	public void addVertex(T vertex) {
		vertices.put(vertex, new LinkedList<Edge<T>>());
	}

	/**
	 * Add a edge between two vertices
	 * 
	 * @param sourceVertex      Generic type T sVertex source vertex
	 * @param destinationVertex Generic type T dVertex destination vertex
	 */
	public void addEdge(T sourceVertex, T destinationVertex) {

		if (!vertices.containsKey(sourceVertex))
			addVertex(sourceVertex);

		if (!vertices.containsKey(destinationVertex))
			addVertex(destinationVertex);

		vertices.get(sourceVertex).add(new Edge<T>(sourceVertex, destinationVertex));

		if (isBidirectional) {
			vertices.get(destinationVertex).add(new Edge<T>(destinationVertex, sourceVertex));
		}
	}

	/**
	 * Get path between two vertices (Returns a list of edges that form a path
	 * between two vertices) throws Exception if vertices do not exist.
	 * 
	 * @param sourceVertex      Generic type T sVertex source vertex
	 * @param destinationVertex Generic type T dVertex destination vertex
	 * @return the list of edges that represent the path or null if no path found
	 * @throws Exception Throws an exception if source or destination vertices do
	 *                   not exist
	 */

	public List<Edge<T>> getPath(T sourceVertex, T destinationVertex) throws Exception {
		if (sourceVertex == null || destinationVertex == null)
			throw new IllegalArgumentException("Source and destination vertices must not be null: ");
		if (!vertices.containsKey(sourceVertex))
			throw new IllegalArgumentException("Source vertex does not exist : " + sourceVertex);
		if (!vertices.containsKey(destinationVertex))
			throw new IllegalArgumentException("Destination vertex does not exist : " + destinationVertex);

		if (sourceVertex.equals(destinationVertex)
				&& !vertices.get(sourceVertex).stream().anyMatch(i -> i.getDestinationVertex() == sourceVertex))
			throw new IllegalArgumentException("No edge exists between " + sourceVertex + " and " + destinationVertex);

		Set<T> visited = new LinkedHashSet<>();
		Stack<T> stack = new Stack<>();
		stack.push(sourceVertex);

		while (!stack.isEmpty()) {
			T vertex = stack.pop();

			if (!visited.contains(vertex)) {
				visited.add(vertex);

				for (Edge<T> edge : this.vertices.get(vertex)) {

					stack.push(edge.getDestinationVertex());

					if (edge.getDestinationVertex().equals(destinationVertex)) {
						visited.add(destinationVertex);
						return toEdgesList(visited);
					}
				}
			}
		}

		return null; // return null if not path found between two vertices. Client call must handle
						// the case.
	}

	@SuppressWarnings("unchecked")
	private List<Edge<T>> toEdgesList(Set<T> set) throws Exception {
		if (set.size() == 0)
			throw new Exception("The set must contain at least 1 element.");

		List<Edge<T>> list = new LinkedList<>();
		Object[] arr = set.toArray();

		if (arr.length == 1)
			list.add(new Edge<T>((T) arr[0], (T) arr[0]));
		else
			for (int i = 0; i < arr.length - 1; i++) {
				list.add(new Edge<T>((T) arr[i], (T) arr[i + 1]));
			}

		return list;
	}

	/**
	 * Display graph vertices and their edges
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();

		for (T v : vertices.keySet()) {
			builder.append(v.toString() + ": ");
			for (Edge<T> w : vertices.get(v)) {
				builder.append(w.toString() + " ");
			}
			builder.append("\n");
		}

		return (builder.toString());
	}
}
