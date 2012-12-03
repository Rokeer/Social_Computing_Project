package hits;

import java.util.Collection;
import java.util.Iterator;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import entity.Edge;
import entity.Node;

public class HITS {
	public HITS() {

	}

	public void calculateUHITS(UndirectedGraph<Node, Edge> graph, int times) {
		int step = 0;
		double norm = 0;
		for (step = 0; step < times; step++) {
			for (Node n : graph.getVertices()) {
				n.setAuth(0);
				Collection<Node> neighbors = graph.getNeighbors(n);
				Iterator<Node> itr = neighbors.iterator();
				while (itr.hasNext()) {
					Node neighbor = itr.next();
					Edge edge = graph.findEdge(n, neighbor);
					n.setAuth(n.getAuth() + edge.getCapacity() * neighbor.getHub());
					//n.setAuth(n.getAuth() + neighbor.getHub());
				}
				norm = norm + Math.sqrt(n.getAuth());
			}
			norm = Math.sqrt(norm);
			for (Node n : graph.getVertices()) {
				n.setAuth(n.getAuth() / norm);
			}
			norm = 0;
			for (Node n : graph.getVertices()) {
				n.setHub(0);
				Collection<Node> neighbors = graph.getNeighbors(n);
				Iterator<Node> itr = neighbors.iterator();
				while (itr.hasNext()) {
					Node neighbor = itr.next();
					Edge edge = graph.findEdge(n, neighbor);
					n.setHub(n.getHub() + edge.getCapacity() * neighbor.getAuth());
					//n.setHub(n.getHub() + neighbor.getAuth());
				}
				norm = norm + Math.sqrt(n.getHub());
			}
			norm = Math.sqrt(norm);
			for (Node n : graph.getVertices()) {
				n.setHub(n.getHub() / norm);
			}
		}

	}

	
	public void calculateDHITS(DirectedGraph<Node, Edge> graph, int times) {
		int step = 0;
		double norm = 0;
		for (step = 0; step < times; step++) {
			for (Node n : graph.getVertices()) {
				n.setAuth(0);
				Collection<Node> incomingNeighbors = graph.getPredecessors(n);
				Iterator<Node> itr = incomingNeighbors.iterator();
				while (itr.hasNext()) {
					Node neighbor = itr.next();
					Edge edge = graph.findEdge(neighbor, n);
					n.setAuth(n.getAuth() + edge.getCapacity() * neighbor.getHub());
					//n.setAuth(n.getAuth() + neighbor.getHub());
				}
				norm = norm + Math.sqrt(n.getAuth());
			}
			norm = Math.sqrt(norm);
			for (Node n : graph.getVertices()) {
				n.setAuth(n.getAuth() / norm);
			}
			norm = 0;
			for (Node n : graph.getVertices()) {
				n.setHub(0);
				Collection<Node> outgoingNeighbors = graph.getSuccessors(n);
				Iterator<Node> itr = outgoingNeighbors.iterator();
				while (itr.hasNext()) {
					Node neighbor = itr.next();
					Edge edge = graph.findEdge(n, neighbor);
					n.setHub(n.getHub() + edge.getCapacity() * neighbor.getAuth());
					//n.setHub(n.getHub() + neighbor.getAuth());
				}
				norm = norm + Math.sqrt(n.getHub());
			}
			norm = Math.sqrt(norm);
			for (Node n : graph.getVertices()) {
				n.setHub(n.getHub() / norm);
			}
		}
	}
}
