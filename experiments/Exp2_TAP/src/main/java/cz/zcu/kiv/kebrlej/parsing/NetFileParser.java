package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.Link;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetFileParser {

    private List<Link> links = new ArrayList<>();

    public void parseNetworkData(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0 || line.contains("~")) {
                //empty or comment line
                continue;
            }else{
                try {
                    links.add(LinkDataParser.parseLinkData(line));
                } catch (LinkDataParsingException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

}
