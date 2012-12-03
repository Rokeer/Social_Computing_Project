package main;

import org.xml.sax.SAXException;

import pagerank.PageRank;
import sum.Sum;

import edu.uci.ics.jung.graph.DirectedGraph;
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
		// �ļ��� ���graphml��˫��ͼ��pr�㷨��CapSum��Ҫ����2�����������ͼ������Ҫ��
		String path = "data/";
		String filename = "";
		// PR�����е�dֵ
		double d = 0.85;
		// ����Sumʱ��PR��ռȨ��
		double weight = 0.5;
		// ����PR��HITSʱ��������
		int times = 100;
		// ��ӡ������
		int top = 0;
		// graph���ͣ�0��ʾundirected��1��ʾdirected
		int graphType = 0;

		//run(path, filename, graphType, d, weight, times, top);
		
		
		for (int i = 0; i < 16; i++) {
			filename = "cut15_No_"+i+".graphml";
			run(path, filename, graphType, d, weight, times, top);
		}
		
		
		

	}

	public static void run(String path, String filename, int graphType, double d, double weight, int times, int top)  throws ParserConfigurationException,
	SAXException, IOException {
		GraphML graphML = new GraphML();
		//PageRankNewAlg pr = new PageRankNewAlg();
		PageRank pr = new PageRank();
		HITS hits = new HITS();
		Util u = new Util();
		Sum sum = new Sum();

		
		if (graphType == 0) {
			UndirectedGraph<Node, Edge> graph = graphML.getUGraph(path+filename); // ��ȡgraph
			pr.calculateUPR(graph, d, times); // ����pr
			hits.calculateUHITS(graph, times); // ����hits
			u.uNormalization(graph); // ��һ��
			sum.calculateUSum(graph, weight); // �����ܺ�
			
			ArrayList<Node> ranking = new ArrayList<Node>();
			ranking = u.uRanking(graph, 0);
			System.out.println("Ranking using PR");
			for (int i = 0; i < top; i++) {
				System.out.println("UserID: " + ranking.get(i).getUserID()
						+ ", PageRank: " + ranking.get(i).getPageRank());
			}
			System.out.println("******************************************");

			ranking = u.uRanking(graph, 1);
			System.out.println("Ranking using HITS");
			for (int i = 0; i < top; i++) {
				System.out.println("UserID: " + ranking.get(i).getUserID()
						+ ", HITS: " + ranking.get(i).getAuth());
			}
			System.out.println("******************************************");

			ranking = u.uRanking(graph, 2);
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
			

			graphML.exportUGraph(graph, path + "export/" + filename + ".json");
		}
		else
		{
			DirectedGraph<Node, Edge> graph = graphML.getDGraph(path+filename); // ��ȡgraph
			pr.calculateDPR(graph, d, times); // ����pr
			hits.calculateDHITS(graph, times); // ����hits
			//u.dNormalization(graph); // ��һ��
			sum.calculateDSum(graph, weight); // �����ܺ�
			
			
			/**
			double tmp4PR = 0;
			double tmp4Auth = 0;
			double tmp4Hub = 0;
			double tmp4Sum = 0;
			for (Node n : graph.getVertices()) {
				tmp4PR = tmp4PR +

				n.getPageRank();
				tmp4Auth = tmp4Auth + n.getAuth();
				tmp4Hub = tmp4Hub

				+ n.getHub();
				tmp4Sum = tmp4Sum + n.getSum();
			}
			System.out.println(tmp4PR);
			System.out.println(tmp4Auth);
			System.out.println(tmp4Hub);
			System.out.println(tmp4Sum);
			**/
			
			ArrayList<Node> ranking = new ArrayList<Node>();
			ranking = u.dRanking(graph, 0);
			System.out.println("Ranking using PR");
			for (int i = 0; i < top; i++) {
				System.out.println("UserID: " + ranking.get(i).getUserID()
						+ ", PageRank: " + ranking.get(i).getPageRank());
			}
			System.out.println("******************************************");

			ranking = u.dRanking(graph, 1);
			System.out.println("Ranking using HITS");
			for (int i = 0; i < top; i++) {
				System.out.println("UserID: " + ranking.get(i).getUserID()
						+ ", Auth: " + ranking.get(i).getAuth() + ", Hub: " + ranking.get(i).getHub());
			}
			System.out.println("******************************************");

			ranking = u.dRanking(graph, 2);
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
			

			graphML.exportDGraph(graph, path + "export/" + filename + ".json");
			
		}
		

	}
}