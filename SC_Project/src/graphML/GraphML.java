package graphML;

import entity.Edge;
import entity.Node;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.io.GraphMLMetadata;
import edu.uci.ics.jung.io.GraphMLReader;
import org.apache.commons.collections15.BidiMap;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class GraphML {
	public GraphML() {

	}

	public UndirectedGraph<Node, Edge> getGraph(String filename)
			throws ParserConfigurationException, SAXException, IOException {
		GraphMLReader<UndirectedGraph<Node, Edge>, Node, Edge> gmlr = new GraphMLReader<UndirectedGraph<Node, Edge>, Node, Edge>(
				new VertexFactory(), new EdgeFactory());
		final UndirectedGraph<Node, Edge> graph = new UndirectedSparseMultigraph<Node, Edge>();

		gmlr.load(filename, graph);

		BidiMap<Node, String> vertex_ids = gmlr.getVertexIDs();
		Map<String, GraphMLMetadata<Edge>> edge_meta = gmlr.getEdgeMetadata();

		for (Node n : graph.getVertices()) {
			n.setUserID(vertex_ids.get(n));
			n.setPageRank(0.2);
			n.setAuth(1);
			n.setHub(1);
		}
		for (Edge e : graph.getEdges()) {
			e.setCapacity(Double.parseDouble(edge_meta.get("d2").transformer
					.transform(e)));
		}
		return graph;
	}
}