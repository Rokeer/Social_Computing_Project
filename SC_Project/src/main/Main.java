package main;

import org.xml.sax.SAXException;

import pagerank.PageRank;
import sum.Sum;

import edu.uci.ics.jung.graph.UndirectedGraph;
import entity.Edge;
import entity.Node;

import graphML.GraphML;
import hits.HITS;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		// 文件名 如果graphml是双向图，pr算法的all cap需要除以2，如果是无向图，则不需要除
		String filename = "network.graphml";
		// PR计算中的d值
		double d = 0.85;
		// 计算Sum时，PR所占权重
		double weight = 0.5;
		// 计算PR和HITS时迭代次数
		int times = 100;
		// 打印排名数
		int top = 10;

		GraphML graphML = new GraphML();
		//PageRankNewAlg pr = new PageRankNewAlg();
		PageRank pr = new PageRank();
		HITS hits = new HITS();
		Util u = new Util();
		Sum sum = new Sum();

		UndirectedGraph<Node, Edge> graph = graphML.getGraph(filename); // 获取graph
		pr.calculatePR(graph, d, times); // 计算pr
		hits.calculateHITS(graph, times); // 计算hits
		u.normalization(graph); // 归一化
		sum.calculateSum(graph, weight); // 计算总和

		double tmp4PR = 0;
		double tmp4Auth = 0;
		double tmp4Hub = 0;
		double tmp4Sum = 0;
		for (Node n : graph.getVertices()) {
			tmp4PR = tmp4PR + n.getPageRank();
			tmp4Auth = tmp4Auth + n.getAuth();
			tmp4Hub = tmp4Hub + n.getHub();
			tmp4Sum = tmp4Sum + n.getSum();
		}

		System.out.println(tmp4PR);
		System.out.println(tmp4Auth);
		System.out.println(tmp4Hub);
		System.out.println(tmp4Sum);

		ArrayList<Node> ranking = new ArrayList<Node>();
		ranking = u.ranking(graph, 0);
		System.out.println("Ranking using PR");
		for (int i = 0; i < top; i++) {
			System.out.println("UserID: " + ranking.get(i).getUserID()
					+ ", PageRank: " + ranking.get(i).getPageRank());
		}
		System.out.println("******************************************");

		ranking = u.ranking(graph, 1);
		System.out.println("Ranking using HITS");
		for (int i = 0; i < top; i++) {
			System.out.println("UserID: " + ranking.get(i).getUserID()
					+ ", HITS: " + ranking.get(i).getAuth());
		}
		System.out.println("******************************************");

		ranking = u.ranking(graph, 2);
		System.out.println("Ranking using Sum");
		for (int i = 0; i < top; i++) {
			System.out.println("UserID: " + ranking.get(i).getUserID()
					+ ", Sum: " + ranking.get(i).getSum());
		}
		System.out.println("******************************************");

		/**
		 * for (Node n : graph.getVertices()) { System.out.println("UserID: " +
		 * n.getUserID() + ", PR: " + n.getPageRank() + ", Auth&Hub: " +
		 * n.getAuth() + "&" + n.getHub() + ", Sum: " + n.getSum()); }
		 **/

	}
}