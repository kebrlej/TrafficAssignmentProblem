package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.DestinationFlowPair;
import cz.zcu.kiv.kebrlej.ODFlows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class TripsFileParserTest {


    @Test
    public void getOriginIdTest() {
        String line = "Origin 43";
        TripsFileParser tripsParser = new TripsFileParser();
        Assertions.assertEquals(43, tripsParser.parseOriginId(line));
    }

    @Test
    void parseDestinationFlows() {
        String line = "    2 :    1365.90; " + "  3 :     407.40;    4 :     861.40;    5 :     354.40;    6 :     545.10;\n" +
                "    7 :     431.50;    8 :       1.00;    9 :      56.80;   10 :      75.30;   11 :       1.00;\n" +
                "   12 :      37.90;   13 :      48.50;   14 :       1.00;   15 :     185.90;   16 :      12.10;\n" +
                "   17 :      81.10;   18 :     126.60;   19 :      67.20;   20 :     382.40;   21 :     101.60;\n" +
                "   22 :      71.70;   23 :      25.80;   24 :      21.10;   25 :     665.10;   26 :      29.00;\n" +
                "   27 :      18.90;   28 :      75.90;   29 :     139.10;   30 :     119.10;   31 :     295.60;\n" +
                "   32 :      85.90;   33 :      74.30;   34 :      96.40;   35 :      53.60;   36 :      37.90;\n" +
                "   37 :      13.70;   38 :     107.70;";

        TripsFileParser tripsFileParser = new TripsFileParser();
        List<DestinationFlowPair> destinationFlowList = new ArrayList<>();
        tripsFileParser.parseDestinationFlows(line, destinationFlowList);

        Assertions.assertEquals(37, destinationFlowList.size());
    }

    @Test
    public void ReadFileTest() throws IOException, TntpParsingException {
        String mapName = "Anaheim";

        TripsFileParser tripsFileParser = new TripsFileParser();

        Path tripsPath = Paths.get(FileParser.getTestResourcesAbsolutePath(), mapName + tripsFileParser.getTntpFileExtension());
        BufferedReader bfr = tripsFileParser.prepareBufferedReader(tripsPath.toString());

        MetadataParser.parseAllMetadata(bfr);
        List<ODFlows> ODFlowsList = tripsFileParser.parseTripsData(bfr);

        Assertions.assertEquals(1, ODFlowsList.get(0).getOriginId());
        Assertions.assertEquals(1365.90, ODFlowsList.get(0).getDestFlows().get(0).getFlow());
    }

}