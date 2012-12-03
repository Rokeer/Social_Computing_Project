package sum;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import entity.Edge;
import entity.Node;

public class Sum {
	public Sum() {

	}

	public void calculateUSum(UndirectedGraph<Node, Edge> graph, double weight) {
		for (Node n : graph.getVertices()) {
			n.setSum((weight * n.getPageRank()) + ((1 - weight) * n.getAuth()));
		}
	}
	
	public void calculateDSum(DirectedGraph<Node, Edge> graph, double weight) {
		for (Node n : graph.getVertices()) {
			n.setSum((weight * n.getPageRank()) + ((1 - weight) * n.getAuth()));
		}
	}
}
