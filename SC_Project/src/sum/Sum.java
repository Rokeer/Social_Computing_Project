package sum;

import edu.uci.ics.jung.graph.UndirectedGraph;
import entity.Edge;
import entity.Node;

public class Sum {
	public Sum() {

	}

	public void calculateSum(UndirectedGraph<Node, Edge> graph, double weight) {
		for (Node n : graph.getVertices()) {
			n.setSum((weight * n.getPageRank()) + ((1 - weight) * n.getAuth()));
		}
	}
}
