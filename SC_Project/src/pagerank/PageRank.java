package pagerank;

import java.util.Collection;
import java.util.Iterator;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import entity.Edge;
import entity.Node;

public class PageRank {

	public PageRank() {

	}

	public void calculateUPR(UndirectedGraph<Node, Edge> graph, double d,
			int times) {
		while (times > 0) {
			times = times - 1;

			for (Node n : graph.getVertices()) {
				double result = 0;
				Collection<Node> neighbors = graph.getNeighbors(n);
				Iterator<Node> itr = neighbors.iterator();
				while (itr.hasNext()) {
					Node neighbor = itr.next();
					Edge edge = graph.findEdge(n, neighbor);

					//System.out.println("neighbor :"+allCapacity);
					//System.out.println("node: " + n.getUserID() + ", neighbor: " + neighbor.getUserID() + ", edge cap: " + edge.getCapacity() + ", all cap: "+ (allCapacity/2));
					result = result + edge.getCapacity() / (neighbor.getCapSum())
							* neighbor.getPageRank();
					
					//result = result + neighbor.getPageRank() / (graph.degree(neighbor) / 2);
				}
//				result = (1 - d) + d * result;
				result = ((1 - d) / graph.getVertexCount()) + d * result;
				n.setPageRank(result);
			}
		}
	}
	
	public void calculateDPR(DirectedGraph<Node, Edge> graph, double d,
			int times) {
		while (times > 0) {
			times = times - 1;

			for (Node n : graph.getVertices()) {
				double result = 0;
				Collection<Node> neighbors = graph.getPredecessors(n);
				Iterator<Node> itr = neighbors.iterator();
				while (itr.hasNext()) {
					Node neighbor = itr.next();
					Edge edge = graph.findEdge(neighbor, n);

					//System.out.println("neighbor :"+allCapacity);
					//System.out.println("node: " + n.getUserID() + ", neighbor: " + neighbor.getUserID() + ", edge cap: " + edge.getCapacity() + ", all cap: "+ (allCapacity/2));
					//System.out.println("Node: "+n.getUserID()+", neighbor: "+neighbor.getUserID()+", cap: "+edge.getCapacity() + ", cap sum: "+neighbor.getCapSum());
					
					result = result + edge.getCapacity() / (neighbor.getCapSum()) * neighbor.getPageRank();
					
					//result = result + neighbor.getPageRank() / (graph.degree(neighbor) / 2);
				}
//				result = (1 - d) + d * result;
				result = ((1 - d) / graph.getVertexCount()) + d * result;
				n.setPageRank(result);
			}
		}
	}

}
