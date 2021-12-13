package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.ODPairSolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlowParserTest {


    @Test
    public void parseFlowRowTest() throws TntpParsingException {

        String line = "4 \t233 \t12173.799999999996 \t1.6380226412299237 ";

        FlowFileParser parser = new FlowFileParser();
        ODPairSolution odPairSolution = parser.parseFlowLine(line);

        Assertions.assertEquals(4, odPairSolution.origin);
        Assertions.assertEquals(233, odPairSolution.destination);
        Assertions.assertEquals(12173.799999999996, odPairSolution.flow, 0.01);
        Assertions.assertEquals(1.6380226412299237, odPairSolution.expectedCost, 0.01);
    }


}
