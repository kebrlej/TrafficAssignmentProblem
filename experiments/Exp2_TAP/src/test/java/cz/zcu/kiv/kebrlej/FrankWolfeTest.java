package cz.zcu.kiv.kebrlej;


import cz.zcu.kiv.kebrlej.parsing.TntpParser;
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
        prepareTestData();

        int pom = 0;
        String mapName = "testnet1";

        TntpParser np = new TntpParser();

        np.parseNetFile(mapName);
        np.normalizeNodeIds(5);


        List<ODPair> odFlows = new ArrayList<>();
        odFlows.add(new ODPair(0,10,6000));
        odFlows.add(new ODPair(0,12,6700));
        odFlows.add(new ODPair(1,10,7500));
        odFlows.add(new ODPair(1,12,5250));

        FrankWolfe fw = new FrankWolfe(np,odFlows);
        fw.solve();
    }

    private void prepareTestData() {
        String mapName = "testnet1";

        TntpParser np = new TntpParser();

        np.parseNetFile(mapName);
        np.normalizeNodeIds(5);


        List<ODPair> odFlows = new ArrayList<>();
        odFlows.add(new ODPair(0,10,6000));
        odFlows.add(new ODPair(0,12,6700));
        odFlows.add(new ODPair(1,10,7500));
        odFlows.add(new ODPair(1,12,5250));
    }


}