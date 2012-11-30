package graphML;

import entity.Edge;
import org.apache.commons.collections15.Factory;

class EdgeFactory implements Factory {
	private int e = 0;

	public Edge create() {
		return (new Edge(e++));
	}
}
