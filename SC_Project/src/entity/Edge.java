package entity;

public class Edge
{
    private final int mID;
    private double mCapacity;
    private Node mSource;
    private Node mTarget;
    
    public Edge(int x)
    {
        mID = x;
    }

    public int getID()
    {
        return mID;
    }

	public double getCapacity() {
		return mCapacity;
	}

	public void setCapacity(double capacity) {
		this.mCapacity = capacity;
	}

	public Node getSource() {
		return mSource;
	}

	public void setSource(Node source) {
		this.mSource = source;
	}

	public Node getTarget() {
		return mTarget;
	}

	public void setTarget(Node target) {
		this.mTarget = target;
	}
	
	
    
}
