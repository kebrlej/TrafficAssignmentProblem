package cz.zcu.kiv.kebrlej;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NazdarTest {


    @Test
    public void ReadFileTest() throws IOException {
        Path path = Paths.get("../../networks/Anaheim/Anaheim_net.tntp");
        BufferedReader bfr =
                new BufferedReader(new InputStreamReader(new FileInputStream(path.toString())));

        NetworkParser parser = new NetworkParser(null,null);
        parser.readMetadata(bfr);

        bfr.close();
    }

}
