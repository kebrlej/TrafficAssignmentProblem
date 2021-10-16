package cz.zcu.kiv.kebrlej;

import cz.zcu.kiv.kebrlej.parsing.NetworkParser;
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

    private static NetworkParser networkParser;

    @BeforeAll
    public static void beforeAll(){
        networkParser = new NetworkParser("zdar","anaheim");
    }


    @Test
    public void ReadFileTest() throws IOException {
        Path path = Paths.get("../../networks/Anaheim/Anaheim_net.tntp");
        BufferedReader bfr =
                new BufferedReader(new InputStreamReader(new FileInputStream(path.toString())));
        networkParser.parseNetworkMetadata(bfr);
        networkParser.parseNetworkData(bfr);
        bfr.close();
    }

    @Test
    public void parseMetadataLine() {
        String line = "<NUMBER OF ZONES> 38";

        networkParser.parseMetadataLine(line);
        Assertions.assertTrue(networkParser.networkMetadata.containsKey("<NUMBER OF ZONES>"));
        Assertions.assertEquals("38", networkParser.networkMetadata.get("<NUMBER OF ZONES>"));
    }


    @Test
    void isEndOfMetadata() {
        String line = "<END OF METADATA> bla bla pico";
        Assertions.assertTrue(networkParser.isEndOfMetadata(line));
    }
}