package pagerank;

import java.util.Collection;
import java.util.Iterator;

import edu.uci.ics.jung.graph.UndirectedGraph;
import entity.Edge;
import entity.Node;

public class PageRank {

	public PageRank() {

	}

	public void calculatePR(UndirectedGraph<Node, Edge> graph, double d,
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
					/**
					 * double allCapacity = 0; Iterator<Edge> edgeItr =
					 * graph.getIncidentEdges(neighbor).iterator();
					 * while(edgeItr.hasNext()) { Edge tmpEdge = edgeItr.next();
					 * allCapacity = allCapacity + tmpEdge.getCapacity(); }
					 **/
					result = result + edge.getCapacity()
							* (neighbor.getPageRank() / graph.degree(neighbor));
				}
				result = (1 - d) + d * result;
				// result = ((1 - d) / graph.getVertexCount()) + d * result;
				n.setPageRank(result);
			}
		}
	}

}
