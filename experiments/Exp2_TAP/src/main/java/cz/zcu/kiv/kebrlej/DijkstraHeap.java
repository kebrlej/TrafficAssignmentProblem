package cz.zcu.kiv.kebrlej;

import java.util.*;

/*
    Naive implementation of dijstra
    - early stopping?

 */
public class DijkstraHeap {

    public PriorityQueue<NodeDistance> heap;

    Map<Integer, List<Link>> adjacencyMap;

    NodeDistance[] nodeObjects;
    boolean[] processed;
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

        for (int i = pathNodes.size()-1; i > 0; i--) {
            Integer fromNode = pathNodes.get(i);
            Integer destNode = pathNodes.get(i-1);

            Link connectingLink = adjacencyMap.get(fromNode).stream()
                    .filter(link -> link.getTermNode() == destNode)
                    .findFirst().get();
            shortestPath.add(connectingLink);
        }

        return shortestPath;
    }

    public void findPaths(Integer startNode, Integer targetNode) {
        heap.remove(nodeObjects[startNode]);
        nodeObjects[startNode].distance = 0.0;
        predecessors[startNode] = -1;
        heap.add(nodeObjects[startNode]);

        while (heap.isEmpty() == false) {
            NodeDistance currentNode = heap.poll();
            processed[currentNode.nodeId] = true;
            List<Link> adjacentNodes = adjacencyMap.get(currentNode.nodeId);

            for (Link link : adjacentNodes) {
                Integer destId = link.getTermNode();

                if (processed[destId] == false) {
                    double destDistance = nodeObjects[destId].distance;
                    double newPossibleDistance = currentNode.distance + link.getLength();
                    if (destDistance > currentNode.distance + link.getLength()) {
                        heap.remove(nodeObjects[destId]);
                        nodeObjects[destId].distance = newPossibleDistance;
                        heap.add(nodeObjects[destId]);
                        predecessors[destId] = currentNode.nodeId;
                    }
                }
                int x = 0;
            }
            int xx = 0;


        }
        int pom = 0;
    }

    public DijkstraHeap(List<Link> links) {
        adjacencyMap = new HashMap<>();

        links.forEach(link -> {
            if (adjacencyMap.containsKey(link.getInitNode())) {
                adjacencyMap.get(link.getInitNode()).add(link);
            } else {
                List<Link> nodeLinks = new ArrayList<>();
                nodeLinks.add(link);
                adjacencyMap.put(link.getInitNode(), nodeLinks);
            }
        });

        heap = new PriorityQueue<>(adjacencyMap.keySet().size());
        processed = new boolean[adjacencyMap.size()];
        predecessors = new Integer[adjacencyMap.size()];
        nodeObjects = new NodeDistance[adjacencyMap.size()];

        adjacencyMap.keySet().forEach(nodeId -> {
            NodeDistance nodeDistance = new NodeDistance(nodeId, Double.POSITIVE_INFINITY);
            heap.add(nodeDistance);
            nodeObjects[nodeId] = nodeDistance;
        });

        int pom = 0;

        //for each key in map, create queue
    }

}
