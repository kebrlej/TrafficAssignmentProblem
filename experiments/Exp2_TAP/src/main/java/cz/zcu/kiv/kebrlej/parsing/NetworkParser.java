package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.Link;
import cz.zcu.kiv.kebrlej.ODFlows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class NetworkParser {

    Map<String, String> netMetadata;
    List<Link> netLinks;

    Map<String,String> flowsMetadata;
    List<ODFlows> ODFlowsList;


    public void parseMap(String mapName) {
        parseTripsFile(mapName);
        parseNetFile(mapName);
    }

    private void parseNetFile(String mapName) {
        NetFileParser netFileParser = new NetFileParser();
        Path netFilePath = Paths.get(FileParser.getTestResourcesAbsolutePath(), mapName + netFileParser.getTntpFileExtension());

        try {
            BufferedReader bfr = netFileParser.prepareBufferedReader(netFilePath.toString());

            this.netMetadata = MetadataParser.parseAllMetadata(bfr);

            netFileParser.parseNetworkData(bfr);
            this.netLinks = netFileParser.getLinks();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TntpParsingException e) {
            e.printStackTrace();
        }

    }

    private void parseTripsFile(String mapName) {
        TripsFileParser tripsFileParser = new TripsFileParser();

        Path tripsPath = Paths.get(FileParser.getTestResourcesAbsolutePath(), mapName + tripsFileParser.getTntpFileExtension());
        try {
            BufferedReader bfr = tripsFileParser.prepareBufferedReader(tripsPath.toString());

            this.flowsMetadata = MetadataParser.parseAllMetadata(bfr);
            this.ODFlowsList = tripsFileParser.parseTripsData(bfr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TntpParsingException e) {
            e.printStackTrace();
        }
    }


}
