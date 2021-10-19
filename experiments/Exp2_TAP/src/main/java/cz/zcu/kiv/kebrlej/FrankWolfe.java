package cz.zcu.kiv.kebrlej;

import cz.zcu.kiv.kebrlej.parsing.NetworkParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrankWolfe {

    private Map<Integer, List<Link>> adjacencyMtx;
    private List<ODPair> odPairs;

    private NetworkParser parser;

    public FrankWolfe(NetworkParser parser, List<ODPair> odPairs) {
        adjacencyMtx = new HashMap<>();
        this.parser = parser;
        this.odPairs = odPairs;
        parser.getNetLinks().forEach(link -> {
            if (adjacencyMtx.containsKey(link.getInitNode())) {
                adjacencyMtx.get(link.getInitNode()).add(link);
            } else {
                List<Link> nodeLinks = new ArrayList<>();
                nodeLinks.add(link);
                adjacencyMtx.put(link.getInitNode(), nodeLinks);
            }
        });
    }

    private void printPath(List<Link> path) {
        String result = path.stream()
                .map(n -> "(" + (n.getInitNode() + 5) + "->" + (n.getTermNode() + 5) + ")")
                .collect(Collectors.joining(" - ", "{", "}"));

        System.out.println(result);
    }

    public void solve() {

        int iteration = 0;

        for (ODPair odPair : odPairs) {
            DijkstraHeap dh = new DijkstraHeap(this.parser.getNetLinks(), 13);
            dh.findPaths(odPair.origin, odPair.destination);
            List<Link> shortestPath = dh.extractShortestPath(odPair.destination);
            printPath(shortestPath);

            if (iteration == 0) {
                
            } else {

            }

            /*
                - poprve prirad kazdemu linku plnou palbu



            */

        }


        /*
            for each OD pair
                - find shortest path
                - all or nothing assignment -> all od pairs
                - recalculate costs


        */


    }


}

