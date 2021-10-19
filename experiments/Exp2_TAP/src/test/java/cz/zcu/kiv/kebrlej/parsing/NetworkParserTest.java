package cz.zcu.kiv.kebrlej.parsing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NetworkParserTest {

    private static NetFileParser netFileParser;

    @BeforeAll
    static void beforeAll(){
        netFileParser = new NetFileParser();
    }

    @Test
    public void myNetFileParseTest(){
        String mapName = "testnet1";

        NetworkParser np = new NetworkParser();

        np.parseNetFile(mapName);

        int pom = 0;
    }


    @Test
    public void parseMetadataLine() throws TntpParsingException {
        String testLine = "<NUMBER OF ZONES> 38";

        assertEquals("<NUMBER OF ZONES>",MetadataParser.parseMetadataLine(testLine).getKey());
        assertEquals("38", MetadataParser.parseMetadataLine(testLine).getValue());
    }

    @Test
    void isEndOfMetadata() {
        String line = "<END OF METADATA> hello world!";
        assertTrue(MetadataParser.isEndOfMetadata(line));
    }
}