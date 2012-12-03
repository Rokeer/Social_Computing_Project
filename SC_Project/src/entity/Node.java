package entity;

public class Node {
	private final int mID;
	private String mUserID;
	private double mPageRank;
	private double mAuth;
	private double mHub;
	private double mCapSum;

	public Node(int x) {
		mID = x;
	}

	public int getID() {
		return mID;
	}

	public String getUserID() {
		return mUserID;
	}

	public void setUserID(String userID) {
		this.mUserID = userID;
	}

	public double getPageRank() {
		return mPageRank;
	}

	public void setPageRank(double pagerank) {
		this.mPageRank = pagerank;
	}

	public double getAuth() {
		return mAuth;
	}

	public void setAuth(double auth) {
		this.mAuth = auth;
	}

	public double getHub() {
		return mHub;
	}

	public void setHub(double hub) {
		this.mHub = hub;
	}
	
	public double getCapSum() {
		return mCapSum;
	}

	public void setCapSum(double capsum) {
		this.mCapSum = capsum;
	}
}
