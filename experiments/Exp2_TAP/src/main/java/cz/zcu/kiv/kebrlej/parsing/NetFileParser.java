package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.Link;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetFileParser extends FileParser {

    private List<Link> links = new ArrayList<>();

    @Override
    protected String getTntpFileExtension() {
        return "_net.tntp";
    }

    public void parseNetworkData(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0 || line.contains("~")) {
                //empty or comment line
                continue;
            } else {
                try {
                    links.add(LinkDataParser.parseLinkData(line));
                } catch (TntpParsingException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public List<Link> getLinks() {
        return links;
    }
}
