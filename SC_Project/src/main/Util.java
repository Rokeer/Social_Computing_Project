package main;

import java.util.ArrayList;
import java.util.Collections;

import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import entity.Edge;
import entity.Node;

public class Util {
	public Util() {

	}

	public void uNormalization(UndirectedGraph<Node, Edge> graph) {
		double sum4Auth = 0;
		double sum4Hub = 0;
		for (Node n : graph.getVertices()) {
			sum4Auth = sum4Auth + n.getAuth();
			sum4Hub = sum4Hub + n.getHub();
		}
		for (Node n : graph.getVertices()) {
			n.setAuth(n.getAuth() / sum4Auth);
			n.setHub(n.getHub() / sum4Hub);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Node> uRanking(UndirectedGraph<Node, Edge> graph, int type) {
		ArrayList<Node> nodesList = new ArrayList<Node>();
		nodesList.addAll(graph.getVertices());
		if (type == 0) {
			Comparator4PR comparator4PR = new Comparator4PR();
			Collections.sort(nodesList, comparator4PR);
		} else if (type == 1) {
			Comparator4HITS comparator4HITS = new Comparator4HITS();
			Collections.sort(nodesList, comparator4HITS);
		} else {
			Comparator4Sum comparator4Sum = new Comparator4Sum();
			Collections.sort(nodesList, comparator4Sum);
		}

		return nodesList;
	}
	
	
	public void dNormalization(DirectedGraph<Node, Edge> graph) {
		double sum4Auth = 0;
		double sum4Hub = 0;
		for (Node n : graph.getVertices()) {
			sum4Auth = sum4Auth + n.getAuth();
			sum4Hub = sum4Hub + n.getHub();
		}
		for (Node n : graph.getVertices()) {
			n.setAuth(n.getAuth() / sum4Auth);
			n.setHub(n.getHub() / sum4Hub);
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Node> dRanking(DirectedGraph<Node, Edge> graph, int type) {
		ArrayList<Node> nodesList = new ArrayList<Node>();
		nodesList.addAll(graph.getVertices());
		if (type == 0) {
			Comparator4PR comparator4PR = new Comparator4PR();
			Collections.sort(nodesList, comparator4PR);
		} else if (type == 1) {
			Comparator4HITS comparator4HITS = new Comparator4HITS();
			Collections.sort(nodesList, comparator4HITS);
		} else {
			Comparator4Sum comparator4Sum = new Comparator4Sum();
			Collections.sort(nodesList, comparator4Sum);
		}

		return nodesList;
	}
}
