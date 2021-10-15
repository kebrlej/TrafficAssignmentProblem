package cz.zcu.kiv.kebrlej;

public class DestinationFlow {

    private int destination;
    private double flow;

    public DestinationFlow(int destination, double flow) {
        this.destination = destination;
        this.flow = flow;
    }

    public int getDestination() {
        return destination;
    }

    public double getFlow() {
        return flow;
    }
}
