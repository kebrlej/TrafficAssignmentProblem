package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.Link;
import cz.zcu.kiv.kebrlej.ODFlows;
import cz.zcu.kiv.kebrlej.ODPairSolution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Flow;

public class TntpParser {

    private Map<String, String> netMetadata;
    private List<Link> netLinks;

    private Map<String,String> flowsMetadata;
    private List<ODFlows> ODFlowsList;

    private List<ODPairSolution> odSolutions;


    public void parseMap(String mapName) {
        parseTripsFile(mapName);
        parseNetFile(mapName);
        parseFlowsFile(mapName);
    }

    public void normalizeNodeIds(Integer offset){
        netLinks.forEach(link -> {
            link.setInitNode(link.getInitNode()-offset);
            link.setTermNode(link.getTermNode()-offset);
        });
    }

    public void parseNetFile(String mapName) {
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

    private void parseFlowsFile(String mapName){
        FlowFileParser flowFileParser = new FlowFileParser();

        Path tripsPath = Paths.get(FileParser.getTestResourcesAbsolutePath(), mapName + flowFileParser.getTntpFileExtension());
        try {
            BufferedReader bfr = flowFileParser.prepareBufferedReader(tripsPath.toString());

            this.odSolutions = flowFileParser.parseFlowData(bfr);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TntpParsingException e) {
            e.printStackTrace();
        }
    }


    public Map<String, String> getNetMetadata() {
        return netMetadata;
    }

    public List<Link> getNetLinks() {
        return netLinks;
    }

    public Map<String, String> getFlowsMetadata() {
        return flowsMetadata;
    }

    public List<ODFlows> getODFlowsList() {
        return ODFlowsList;
    }
}
