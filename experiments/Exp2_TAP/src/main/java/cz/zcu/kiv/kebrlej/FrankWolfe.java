package cz.zcu.kiv.kebrlej;

import cz.zcu.kiv.kebrlej.parsing.NetworkParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrankWolfe {

    private Map<Integer, List<Link>> adjacencyMtx;

    private NetworkParser parser;

    public FrankWolfe(NetworkParser parser) {
        adjacencyMtx = new HashMap<>();
        this.parser = parser;
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

    public void solve(){

        DijkstraHeap dh = new DijkstraHeap(this.parser.getNetLinks());

        Integer origin = 0;
        Integer destination = 10;

        dh.findPaths(origin,destination);
        dh.extractShortestPath(destination);

        /*
            for each OD pair
                - find shortest path
                - all or nothing assignment -> all od pairs
                - recalculate costs


        */


    }



}

