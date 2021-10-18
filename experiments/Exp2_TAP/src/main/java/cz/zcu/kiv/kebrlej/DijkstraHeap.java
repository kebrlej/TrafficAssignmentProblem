package cz.zcu.kiv.kebrlej;

import java.util.*;

public class DijkstraHeap {

    public PriorityQueue<NodeDistance> heap;

    Map<Integer, List<Link>> adjacencyMap;

    NodeDistance[] nodeObjects;
    Boolean[] processed;
    Integer[] predecessors;

    public void findPaths(Integer startNode, Integer targetNode) {
        heap.remove(nodeObjects[startNode]);
        nodeObjects[startNode].distance = 0.0;
        heap.add(nodeObjects[startNode]);

        int pom = 0;
        while (heap.isEmpty() == false) {
            NodeDistance node = heap.poll();
            processed[node.nodeId] = true;
            List<Link> adjacentNodes = adjacencyMap.get(node.nodeId);

            for (Link link : adjacentNodes) {
                Integer destId = link.getTermNode();

                if (processed[destId] == false) {
                    double destDistance = nodeObjects[destId].distance;
                    double newPossibleDistance = node.distance + link.getLength();
                    if (destDistance > node.distance + link.getLength()) {
                        heap.remove(nodeObjects[destId]);
                        nodeObjects[destId].distance = newPossibleDistance;
                        heap.add(nodeObjects[destId]);
                    }
                }
                /*
                    1) get link term node
                    2) check if this path is better than existing
                        - better -> remove from heap, update distance, put in heap
                        -

                 */

            }


        }

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
        processed = new Boolean[adjacencyMap.size()];
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
