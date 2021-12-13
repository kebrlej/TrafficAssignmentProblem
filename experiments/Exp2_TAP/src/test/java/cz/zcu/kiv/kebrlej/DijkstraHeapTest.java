package cz.zcu.kiv.kebrlej;


import cz.zcu.kiv.kebrlej.parsing.TntpParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DijkstraHeapTest {


    private List<Link> prepareAnaheimTestData() {
        TntpParser tntpParser = new TntpParser();
        tntpParser.parseMap("Anaheim");

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

        DijkstraHeap dh = new DijkstraHeap(links,9);
        dh.findPaths(0,4);
        List<Link> path = dh.extractShortestPath(4);

        Assertions.assertEquals(4, path.size());
        Assertions.assertEquals(7, path.get(0).getTermNode());
        Assertions.assertEquals(6, path.get(1).getTermNode());
        Assertions.assertEquals(5, path.get(2).getTermNode());
        Assertions.assertEquals(4, path.get(3).getTermNode());

        int pom = 0;
    }


}