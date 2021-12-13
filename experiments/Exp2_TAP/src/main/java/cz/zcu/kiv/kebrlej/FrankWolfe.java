package cz.zcu.kiv.kebrlej;

import cz.zcu.kiv.kebrlej.parsing.TntpParser;

import java.util.*;
import java.util.stream.Collectors;

public class FrankWolfe {


    private Map<Integer, List<Link>> adjacencyMtx;
    private List<List<Link>> neighbours;
    private List<ODPair> odPairs;
    private TntpParser parser;

    public FrankWolfe(TntpParser parser, List<ODPair> odPairs) {
        adjacencyMtx = new HashMap<>();

        this.parser = parser;
        this.odPairs = odPairs;

        Set<Integer> countMap = new HashSet<>();
        parser.getNetLinks().forEach(link -> {
            countMap.add(link.getInitNode());
            countMap.add(link.getTermNode());
            if (adjacencyMtx.containsKey(link.getInitNode())) {
                adjacencyMtx.get(link.getInitNode()).add(link);
            } else {
                List<Link> nodeLinks = new ArrayList<>();
                nodeLinks.add(link);
                adjacencyMtx.put(link.getInitNode(), nodeLinks);
            }
        });

        this.neighbours = new ArrayList<>(countMap.size());
        for(int i = 0; i < countMap.size(); i++){
            this.neighbours.add(new ArrayList<>());
        }

        parser.getNetLinks().forEach(link -> {
            neighbours.get(link.getInitNode()).add(link);
        });
    }

    private void printPath(List<Link> path) {
        String result = path.stream()
                .map(link -> "(" + (link.getInitNode() + 5) + "->" + (link.getTermNode() + 5) + ", " + link.currentFlow + ")")
                .collect(Collectors.joining(" - ", "{", "}"));

        System.out.println(result);
    }

    public void solve() {

        int iterations =100;

        Map<ODPair, List<Link>> odPaths = new HashMap<>();

        for (int i = 1; i <= iterations; i++) {
            for (ODPair odPair : odPairs) {
                List<Link> newShortestPath = findShortestPath(odPair);

                if (i == 1) {
                    newShortestPath.forEach(pathLink -> {
                        pathLink.currentFlow += odPair.flow;
                    });
                } else {
                    Double auxFlow = odPair.flow / Math.sqrt(i);

                    //update flows
                    odPaths.get(odPair).forEach(link -> link.currentFlow -= auxFlow);
                    newShortestPath.forEach(link -> link.currentFlow += auxFlow);
                }

                odPaths.put(odPair, newShortestPath);
            }

            /*
                Update all costs
            */
            parser.getNetLinks().forEach(link -> link.updateCost());

            System.out.println("Iteration: "+i);

            parser.getNetLinks().forEach(link->{
                System.out.println("Link: "+(link.getInitNode()+5) + " - " + (link.getTermNode()+5)+"\tFlow: "+link.currentFlow);
            });

//
//
//            printDebug(odPaths);
//            System.out.println("\n\n\n");
        }

        int pom = 0;


        /*
            for each OD pair
                - find shortest path
                - all or nothing assignment -> all od pairs
                - recalculate costs


        */


    }

    private void printDebug(Map<ODPair, List<Link>> odPairPathMap) {
        odPairPathMap.entrySet().forEach(odPair -> {
            System.out.println("From: " + odPair.getKey().origin + "  To: " + odPair.getKey().destination);
            printPath(odPair.getValue());
            System.out.println();
        });
    }

    private List<Link> findShortestPath(ODPair odPair) {
        DijkstraHeap dh = new DijkstraHeap(this.parser.getNetLinks(), 13);
        dh.findPaths(odPair.origin, odPair.destination);
        List<Link> shortestPath = dh.extractShortestPath(odPair.destination);
        return shortestPath;
    }


}

