package cz.zcu.kiv.kebrlej.parsing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MetadataParserTest {

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
