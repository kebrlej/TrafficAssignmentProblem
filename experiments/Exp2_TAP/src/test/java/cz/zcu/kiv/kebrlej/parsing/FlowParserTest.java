package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FlowParserTest {


    @Test
    public void parseFlowRowTest() throws TntpParsingException {

        String line = "4 \t233 \t12173.799999999996 \t1.6380226412299237 ";

        FlowFileParser parser = new FlowFileParser();
        Link odPairSolution = parser.parseFlowLine(line);

        Assertions.assertEquals(4, odPairSolution.getInitNode());
        Assertions.assertEquals(233, odPairSolution.getTermNode());
        Assertions.assertEquals(12173.799999999996, odPairSolution.currentFlow, 0.01);
    }


}
