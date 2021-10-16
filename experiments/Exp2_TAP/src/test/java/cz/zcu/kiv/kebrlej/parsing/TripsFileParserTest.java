package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.DestinationFlow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        List<DestinationFlow> destinationFlowList = tripsFileParser.parseDestinationFlows(line);

        Assertions.assertEquals(37, destinationFlowList.size());
    }

}