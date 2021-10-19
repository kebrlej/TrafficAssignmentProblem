package cz.zcu.kiv.kebrlej;


import cz.zcu.kiv.kebrlej.parsing.NetworkParser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FrankWolfeTest {

    /*
    TODO:
    - move numbering to index from 0
    - check after first iteration
     */

    @Test
    public void fwSimpleTest() {
        String mapName = "testnet1";

        NetworkParser np = new NetworkParser();

        np.parseNetFile(mapName);

        List<ODPair> odFlows = new ArrayList<>();
        odFlows.add(new ODPair(1,11,6000));
        odFlows.add(new ODPair(1,13,6700));
        odFlows.add(new ODPair(2,11,7500));
        odFlows.add(new ODPair(2,13,5250));

        int pom = 0;
    }


}