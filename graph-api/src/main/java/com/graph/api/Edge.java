package com.graph.api;

/**
 * Edge class represents Edge entity
 * 
 * @author khadidja
 *
 *
 * @param <T> Generic type
 */
public class Edge<T> {

	/**
	 * sourceVertex Generic Type : source vertex
	 */
	private T sourceVertex;

	/**
	 * destinationVertex Generic Type : destination vertex
	 */
	private T destinationVertex;

	public Edge(T sourceVertex, T destinationVertex) {
		super();
		this.sourceVertex = sourceVertex;
		this.destinationVertex = destinationVertex;
	}

	public T getSourceVertex() {
		return sourceVertex;
	}

	public T getDestinationVertex() {
		return destinationVertex;
	}

	@Override
	public String toString() {
		return "\nEdge [vertex=" + sourceVertex + " --> vertex=" + destinationVertex + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinationVertex == null) ? 0 : destinationVertex.hashCode());
		result = prime * result + ((sourceVertex == null) ? 0 : sourceVertex.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<T> other = (Edge<T>) obj;
		if (destinationVertex == null) {
			if (other.destinationVertex != null)
				return false;
		} else if (!destinationVertex.equals(other.destinationVertex))
			return false;
		if (sourceVertex == null) {
			if (other.sourceVertex != null)
				return false;
		} else if (!sourceVertex.equals(other.sourceVertex))
			return false;
		return true;
	}

}
