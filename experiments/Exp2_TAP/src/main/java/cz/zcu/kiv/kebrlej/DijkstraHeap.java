package cz.zcu.kiv.kebrlej;

import java.util.*;

/*
* Ideas:
* - early stopping
* - memory locality for increased cache performance.
* */

public class DijkstraHeap {

    public PriorityQueue<NodeDistance> distanceHeap;

    List<List<Link>> neighbours;

    NodeDistance[] nodes;
    boolean[] visited;
    Integer[] predecessors;

    public List<Link> extractShortestPath(Integer destination) {
        List<Integer> pathNodes = new ArrayList<>();
        pathNodes.add(destination);
        Integer predecessor = predecessors[destination];
        while (predecessor != -1) {
            pathNodes.add(predecessor);
            predecessor = predecessors[predecessor];
        }
        int pom = 0;

        List<Link> shortestPath = new ArrayList<>();

        for (int i = pathNodes.size() - 1; i > 0; i--) {
            Integer fromNode = pathNodes.get(i);
            Integer destNode = pathNodes.get(i - 1);

            Link connectingLink = neighbours.get(fromNode).stream()
                    .filter(link -> link.getTermNode() == destNode)
                    .findFirst().get();
            shortestPath.add(connectingLink);
        }

        return shortestPath;
    }

    public void findPaths(Integer startNode, Integer targetNode) {
        predecessors[startNode] = -1;

        distanceHeap.remove(nodes[startNode]);
        nodes[startNode].distance = 0.0;
        distanceHeap.add(nodes[startNode]);

        while (distanceHeap.isEmpty() == false) {
            //retrieve and remove
            NodeDistance currentNode = distanceHeap.poll();
            visited[currentNode.nodeId] = true;

            if(this.neighbours.get(currentNode.nodeId).isEmpty()){
                int pom = 0;
            }

            //update all neighbour distances if better path found
            for (Link linkToNeighbour : this.neighbours.get(currentNode.nodeId)) {
                Integer neighbourId = linkToNeighbour.getTermNode();

                if (visited[neighbourId] == false) {
                    double currentBestDistance = nodes[neighbourId].distance;
                    double newDistance = currentNode.distance + linkToNeighbour.getCost();
                    if (currentBestDistance > newDistance) {
                        updateNodeHeap(currentNode, neighbourId, newDistance);
                    }
                }
                int x = 0;
            }
            int xx = 0;
        }
        int pom = 0;
    }

    private void updateNodeHeap(NodeDistance currentNode, Integer nodeId, double newDistance) {
        distanceHeap.remove(nodes[nodeId]);
        nodes[nodeId].distance = newDistance;
        distanceHeap.add(nodes[nodeId]);
        predecessors[nodeId] = currentNode.nodeId;
    }

    public void reset(){
        for (int i = 0; i < neighbours.size(); i++) {
            visited[i] = false;
            predecessors[i] = Integer.MIN_VALUE;
        }

        distanceHeap.clear();
        for (int i = 0; i < neighbours.size(); i++) {
            NodeDistance nodeDistance = new NodeDistance(i, Double.POSITIVE_INFINITY);
            distanceHeap.add(nodeDistance);
            nodes[i] = nodeDistance;
        }
    }

    public DijkstraHeap(List<Link> links, Integer nodeCount) {
        distanceHeap = new PriorityQueue<>(nodeCount);
        visited = new boolean[nodeCount];
        predecessors = new Integer[nodeCount];
        nodes = new NodeDistance[nodeCount];
        neighbours = new ArrayList<>(nodeCount);

        for (int i = 0; i < nodeCount; i++) {
            neighbours.add(new ArrayList<Link>());
            visited[i] = false;
            predecessors[i] = Integer.MIN_VALUE;
        }

        //initialize neighbours
        links.forEach(link -> {
            neighbours.get(link.getInitNode()).add(link);
        });

        for (int i = 0; i < nodeCount; i++) {
            NodeDistance nodeDistance = new NodeDistance(i, Double.POSITIVE_INFINITY);
            distanceHeap.add(nodeDistance);
            nodes[i] = nodeDistance;
        }
    }

}
