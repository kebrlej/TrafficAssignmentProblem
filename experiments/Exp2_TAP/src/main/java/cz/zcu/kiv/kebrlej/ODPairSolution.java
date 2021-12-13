package cz.zcu.kiv.kebrlej;

public class ODPairSolution extends ODPair{

    public double expectedCost;

    public ODPairSolution(Integer origin, Integer destination, double expectedFlow, double expectedCost) {
        super(origin, destination, expectedFlow);
        this.expectedCost = expectedCost;
    }
}
