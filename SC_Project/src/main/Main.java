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
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		// 文件名 如果graphml是双向图，pr算法的CapSum需要除以2，如果是无向图，则不需要除
		String path = "data/";
		String filename = "";
		// PR计算中的d值
		double d = 0.85;
		// 计算Sum时，PR所占权重
		double weight = 0.5;
		// 计算PR和HITS时迭代次数
		int times = 100;
		// 打印排名数
		int top = 1;

		for (int i = 0; i < 16; i++) {
			filename = "cut15_No_"+i+".graphml";
			run(path, filename, d, weight, times, top);
		}
		
		

	}

	public static void run(String path, String filename, double d, double weight, int times, int top)  throws ParserConfigurationException,
	SAXException, IOException {
		GraphML graphML = new GraphML();
		//PageRankNewAlg pr = new PageRankNewAlg();
		PageRank pr = new PageRank();
		HITS hits = new HITS();
		Util u = new Util();
		Sum sum = new Sum();

		UndirectedGraph<Node, Edge> graph = graphML.getGraph(path+filename); // 获取graph
		pr.calculatePR(graph, d, times); // 计算pr
		hits.calculateHITS(graph, times); // 计算hits
		u.normalization(graph); // 归一化
		sum.calculateSum(graph, weight); // 计算总和
		
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
		

		graphML.exportGraph(graph, path + "export/" + filename + ".json");
		//graphML.exportGraph(graph, "export.json");
	}
}