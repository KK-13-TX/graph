package com.graph.api.utests;

import java.util.LinkedList;
import java.util.Map;

import com.graph.api.Edge;
import com.graph.api.Graph;

/**
 * GraphWrapper uses Graph class
 * 
 * @author khadidja
 *
 * @param <T> Generic Type T
 */

public class GraphWrapper<T> extends Graph<T> {

	public GraphWrapper(boolean isBidirectional) {
		super(isBidirectional);
	}

	public boolean hasVertex(T vertex) {
		return vertices.containsKey(vertex);
	}

	public int getVertexCount() {
		return vertices.keySet().size();
	}

	public int getEdgesCount(boolean bidirection) {
		int count = 0;
		for (T v : vertices.keySet()) {
			count += vertices.get(v).size();
		}
		if (bidirection == true) {
			count = count / 2;
		}
		return count;
	}

	public Map<T, LinkedList<Edge<T>>> getGraphMap() {
		return vertices;
	}

}
