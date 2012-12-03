package main;

import java.util.Comparator;

import entity.Node;

public class Comparator4Hub implements Comparator {
	public int compare(Object arg0, Object arg1) {
		Node node0 = (Node) arg0;
		Node node1 = (Node) arg1;
		int flag = Double.compare(node1.getHub(), node0.getHub());
		if (flag == 0) {
			return node0.getUserID().compareTo(node1.getUserID());
		} else {
			return flag;
		}
	}
}
