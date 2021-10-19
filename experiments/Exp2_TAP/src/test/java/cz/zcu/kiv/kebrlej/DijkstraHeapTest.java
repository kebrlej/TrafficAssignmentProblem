package cz.zcu.kiv.kebrlej;


import cz.zcu.kiv.kebrlej.parsing.NetworkParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DijkstraHeapTest {


    private List<Link> prepareAnaheimTestData() {
        NetworkParser networkParser = new NetworkParser();
        networkParser.parseMap("Anaheim");

        return null;
    }


    private static void addEdge(List<Link> link, int initNode, int termNode, int length) {
        link.add(new Link(initNode, termNode, length));
        link.add(new Link(termNode, initNode, length));
    }

    @Test
    public void constructHeapTest() {
        List<Link> links = new ArrayList<>();

        addEdge(links, 0, 1, 4);
        addEdge(links, 0, 7, 8);
        addEdge(links, 1, 2, 8);
        addEdge(links, 1, 7, 11);
        addEdge(links, 2, 3, 7);
        addEdge(links, 2, 8, 2);
        addEdge(links, 2, 5, 4);
        addEdge(links, 3, 4, 9);
        addEdge(links, 3, 5, 14);
        addEdge(links, 4, 5, 10);
        addEdge(links, 5, 6, 2);
        addEdge(links, 6, 7, 1);
        addEdge(links, 6, 8, 6);
        addEdge(links, 7, 8, 7);

        DijkstraHeap dh = new DijkstraHeap(links);
        dh.findPaths(0,4);
        dh.extractShortestPath(4);
        int pom = 0;
    }


}