package cz.zcu.kiv.kebrlej;

public class NodeDistance implements Comparable<NodeDistance>{

    Integer nodeId;
    Double distance = Double.POSITIVE_INFINITY;

    public NodeDistance(Integer nodeId, Double distance){
        this.nodeId = nodeId;
        this.distance = distance;
    }

    @Override
    public int compareTo(NodeDistance otherNode) {
        return this.distance.compareTo(otherNode.distance);
    }
}
