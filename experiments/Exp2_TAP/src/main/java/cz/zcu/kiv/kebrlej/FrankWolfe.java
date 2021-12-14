package cz.zcu.kiv.kebrlej;

import cz.zcu.kiv.kebrlej.parsing.TntpParser;

import java.util.*;
import java.util.stream.Collectors;

public class FrankWolfe {

    private List<ODPair> odPairs;
    private List<Link> links;

    private List<Link> linkSolutions;
    DijkstraHeap dijkstra;


    public FrankWolfe(TntpParser parser, List<ODPair> odPairs) {
        this.links = parser.getNetLinks();
        links.forEach(link -> link.updateCost());
        this.linkSolutions = parser.getOdSolutions();
        this.odPairs = odPairs;

        this.dijkstra = new DijkstraHeap(links, Integer.valueOf(parser.getNetMetadata().get("<NUMBER OF NODES>")));
    }

    public void solve(int iterations) {
        Map<ODPair, List<Link>> odPaths = new HashMap<>();

        for (int i = 1; i <= iterations; i++) {
            System.out.println("Iteration: " + i);
            performIteration(odPaths, i);
            printLinksCumulativeError();
            System.out.println();
        }
        int pom = 0;
        printLinkDiffs();
    }

    /*
    for each OD pair
        - find shortest path
        - all or nothing assignment -> all od pairs
        - recalculate costs
    */
    private void performIteration(Map<ODPair, List<Link>> odPaths, int iterationNumber) {

        for (ODPair odPair : odPairs) {
            List<Link> newShortestPath = findShortestPath(odPair);

            if (iterationNumber == 1) {
                newShortestPath.forEach(pathLink -> {
                    pathLink.currentFlow += odPair.flow;
                });
                odPaths.put(odPair, newShortestPath);
            } else {
                Double auxFlow = odPair.flow / Math.sqrt(iterationNumber);

                //update flows
                List<Link> previousShortestPath = odPaths.get(odPair);
                previousShortestPath.forEach(link -> link.currentFlow -= auxFlow);
                newShortestPath.forEach(link -> link.currentFlow += auxFlow);
            }

            odPaths.put(odPair, newShortestPath);
        }

        links.forEach(link -> link.updateCost());
    }




    private List<Link> findShortestPath(ODPair odPair) {
        dijkstra.reset();
        dijkstra.findPaths(odPair.origin, odPair.destination);
        List<Link> shortestPath = dijkstra.extractShortestPath(odPair.destination);
        return shortestPath;
    }


    private void printLinksCumulativeError() {
        double diffSum = 0;
        double checkSum = 0;
        double checkSum2 = 0;
        for (int i = 0; i < links.size(); i++) {
            diffSum += Math.abs(links.get(i).currentFlow - linkSolutions.get(i).currentFlow);
            checkSum += links.get(i).currentFlow;
            checkSum2 += linkSolutions.get(i).currentFlow;
        }
        System.out.println("FLow diff sum: " + diffSum);
        System.out.println("Flow check sum: " + checkSum);
        System.out.println("Solution flow check sum: "+ checkSum2);
    }

    private void printLinkDiffs() {
        for (int i = 0; i < links.size(); i++) {
            System.out.println("Current flow: " + links.get(i).currentFlow + "  expected: " + linkSolutions.get(i).currentFlow);

            if (links.get(i).getTermNode() != linkSolutions.get(i).getTermNode()  || links.get(i).getInitNode() != linkSolutions.get(i).getInitNode()) {
                throw new RuntimeException();
            }

        }
    }


    private void printPath(List<Link> path) {
        String result = path.stream()
                .map(link -> "(" + (link.getInitNode() + 5) + "->" + (link.getTermNode() + 5) + ", " + link.currentFlow + ")")
                .collect(Collectors.joining(" - ", "{", "}"));

        System.out.println(result);
    }

    private void printDebug(Map<ODPair, List<Link>> odPairPathMap) {
        odPairPathMap.entrySet().forEach(odPair -> {
            System.out.println("From: " + odPair.getKey().origin + "  To: " + odPair.getKey().destination);
            printPath(odPair.getValue());
            System.out.println();
        });
    }




}

