package graphML;

import entity.Edge;
import entity.Node;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.io.GraphMLMetadata;
import edu.uci.ics.jung.io.GraphMLReader;
import edu.uci.ics.jung.io.GraphMLWriter;
import org.apache.commons.collections15.BidiMap;
import org.apache.commons.collections15.Transformer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

public class GraphML {
	public GraphML() {

	}
	
	public DirectedGraph<Node, Edge> getDGraph (String filename)
			throws ParserConfigurationException, SAXException, IOException {
		GraphMLReader<DirectedGraph<Node, Edge>, Node, Edge> gmlr = new GraphMLReader<DirectedGraph<Node, Edge>, Node, Edge>(
				new VertexFactory(), new EdgeFactory());
		final DirectedGraph<Node, Edge> graph = new DirectedSparseMultigraph<Node, Edge>();

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

		// 计算权值的和
		double allCapacity = 0;
		for (Node n : graph.getVertices()) {
			allCapacity = 0;
			Iterator<Edge> edgeItr = graph.getOutEdges(n).iterator();
			while (edgeItr.hasNext()) {
				Edge tmpEdge = edgeItr.next();
				allCapacity = allCapacity + tmpEdge.getCapacity();
				n.setCapSum(allCapacity);
			}
		}
		return graph;
	}

	public UndirectedGraph<Node, Edge> getUGraph(String filename)
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

		// 计算权值的和
		double allCapacity = 0;
		for (Node n : graph.getVertices()) {
			allCapacity = 0;
			Iterator<Edge> edgeItr = graph.getOutEdges(n).iterator();
			while (edgeItr.hasNext()) {
				Edge tmpEdge = edgeItr.next();
				allCapacity = allCapacity + tmpEdge.getCapacity();
				n.setCapSum(allCapacity);
			}
		}
		return graph;
	}

	public void exportUGraph(UndirectedGraph<Node, Edge> graph, String filename)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (Node n : graph.getVertices()) {
			sb.append("\"" + n.getUserID() + "\":\"" + n.getPageRank() + "\",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		String str = sb.toString();
		System.out.println(sb.toString());

		try {
			File f = new File(filename);
			if (f.exists()) {
				System.out.println("文件存在");
			} else {
				System.out.println("文件不存在，正在创建...");
				if (f.createNewFile()) {
					System.out.println("文件创建成功！");
				} else {
					System.out.println("文件创建失败！");
				}
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(str);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void exportDGraph(DirectedGraph<Node, Edge> graph, String filename)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (Node n : graph.getVertices()) {
			sb.append("\"" + n.getUserID() + "\":\"" + n.getPageRank() + "\",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		String str = sb.toString();
		System.out.println(sb.toString());

		try {
			File f = new File(filename);
			if (f.exists()) {
				System.out.println("文件存在");
			} else {
				System.out.println("文件不存在，正在创建...");
				if (f.createNewFile()) {
					System.out.println("文件创建成功！");
				} else {
					System.out.println("文件创建失败！");
				}
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(str);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}