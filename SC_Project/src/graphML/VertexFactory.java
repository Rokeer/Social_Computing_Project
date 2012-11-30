package graphML;

import entity.Node;
import org.apache.commons.collections15.Factory;

class VertexFactory implements Factory {
	private int n = 0;

	public Node create() {
		return (new Node(n++));
	}
}
