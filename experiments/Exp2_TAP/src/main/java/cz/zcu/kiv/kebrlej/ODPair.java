package cz.zcu.kiv.kebrlej;

public class ODPair {

    public Integer origin;
    public Integer destination;

    public double flow;


    public ODPair(Integer origin, Integer destination, double flow) {
        this.origin = origin;
        this.destination = destination;
        this.flow = flow;
    }
}
