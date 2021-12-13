package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinkDataParserTest {

    @Test
    public void parseLinkDataTest() throws TntpParsingException {
        String line = "1\t117\t9000\t5280\t1.090458488\t0.15\t4\t4842\t0\t1\t;\n";
        Assertions.assertDoesNotThrow( () -> {
            NetFileParser.parseNetFileRow(line);
        });

        Link link = NetFileParser.parseNetFileRow(line);



    }


}