package cz.zcu.kiv.kebrlej;

public class DestinationFlowPair {

    private int destination;
    private double flow;

    public DestinationFlowPair(int destination, double flow) {
        this.destination = destination;
        this.flow = flow;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getDestination() {
        return destination;
    }

    public double getFlow() {
        return flow;
    }
}
