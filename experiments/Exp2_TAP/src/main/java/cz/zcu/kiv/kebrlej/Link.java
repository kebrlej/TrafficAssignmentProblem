package cz.zcu.kiv.kebrlej;

public class Link {

    private int initNode;
    private int termNode;

    private double capacity;
    private double length;
    private double freeFlowTime;

    private double alfa; //named "b" in tntp files
    private double exponent;

    private double speed;

    public double BPRFUnction() {
        return 0.0;
    }


    public int getInitNode() {
        return initNode;
    }

    public void setInitNode(int initNode) {
        this.initNode = initNode;
    }

    public int getTermNode() {
        return termNode;
    }

    public void setTermNode(int termNode) {
        this.termNode = termNode;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getFreeFlowTime() {
        return freeFlowTime;
    }

    public void setFreeFlowTime(double freeFlowTime) {
        this.freeFlowTime = freeFlowTime;
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public double getExponent() {
        return exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
