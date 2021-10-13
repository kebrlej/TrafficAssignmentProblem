package cz.zcu.kiv.kebrlej;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


class NetworkParserTest {

    private static NetworkParser networkParser;

    @BeforeAll
    public static void beforeAll(){
        networkParser = new NetworkParser("zdar","anaheim");
    }

    @Test
    public void parseMetadataLine(){
        String line = "<NUMBER OF ZONES> 38";

        Pattern p = Pattern.compile("\\<.*\\>");
        Matcher m = p.matcher(line);

        if(m.find()){
            String match = m.group();
            String data = line.replace(match, "").strip();
            Assertions.assertEquals(match, "<NUMBER OF ZONES>");
            Assertions.assertEquals(data, "38");
        }else{
            Assertions.fail();
        }



    }


    @Test
    void isEndOfMetadata() {
        String line = "<END OF METADATA> bla bla pico";

        Assertions.assertTrue(networkParser.isEndOfMetadata(line));


    }
}