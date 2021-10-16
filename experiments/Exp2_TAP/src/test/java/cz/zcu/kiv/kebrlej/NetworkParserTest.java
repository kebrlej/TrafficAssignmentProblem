package cz.zcu.kiv.kebrlej;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        networkParser.readMetadata(bfr);
        bfr.close();
    }

    @Test
    public void parseMetadataLine() {
        String line = "<NUMBER OF ZONES> 38";

        networkParser.parseMetadataLine(line);
        Assertions.assertTrue(networkParser.metadata.containsKey("<NUMBER OF ZONES>"));
        Assertions.assertEquals("38", networkParser.metadata.get("<NUMBER OF ZONES>"));
    }


    @Test
    void isEndOfMetadata() {
        String line = "<END OF METADATA> bla bla pico";
        Assertions.assertTrue(networkParser.isEndOfMetadata(line));
    }
}