package cz.zcu.kiv.kebrlej;

import cz.zcu.kiv.kebrlej.parsing.MetadataParser;
import cz.zcu.kiv.kebrlej.parsing.NetFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;


class NetworkParserTest {

    private static NetFileParser netFileParser;
    private static MetadataParser metadataParser;

    @BeforeAll
    public static void beforeAll(){
        netFileParser = new NetFileParser();
        metadataParser = new MetadataParser();
    }


    @Test
    public void ReadFileTest() throws IOException {
        Path path = Paths.get("../../networks/Anaheim/Anaheim_net.tntp");
        BufferedReader bfr =
                new BufferedReader(new InputStreamReader(new FileInputStream(path.toString())));
        metadataParser.parseAllMetadata(bfr);
        netFileParser.parseNetworkData(bfr);
        bfr.close();
    }

    @Test
    public void parseMetadataLine() {

        MetadataParser metadataParser = new MetadataParser();
        String line = "<NUMBER OF ZONES> 38";


        metadataParser.parseMetadataLine(line);
        Assertions.assertTrue(metadataParser.getParsedMetadata().containsKey("<NUMBER OF ZONES>"));
        Assertions.assertEquals("38", metadataParser.getParsedMetadata().get("<NUMBER OF ZONES>"));
    }


    @Test
    void isEndOfMetadata() {
        String line = "<END OF METADATA> bla bla pico";
        Assertions.assertTrue(metadataParser.isEndOfMetadata(line));
    }
}