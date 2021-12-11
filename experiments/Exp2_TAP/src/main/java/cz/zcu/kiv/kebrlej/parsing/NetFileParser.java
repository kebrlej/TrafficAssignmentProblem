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
                    links.add(parseNetFileRow(line));
                } catch (TntpParsingException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public static Link parseNetFileRow(String line) throws TntpParsingException {

        String[] rowElements = line.split("\t");

        if (rowElements.length < 7) {
            throw new TntpParsingException("Insufficient count of link parameters: " + line);
        }

        //is first element empty? -> skip if it is
        int columnIndex = rowElements[0].strip().length() == 0 ? 1 : 0;

        Link link = createLink(rowElements, columnIndex);
        return link;

    }

    private static Link createLink(String[] rowElements, int columnIndex) throws TntpParsingException {
        Link link = new Link();

        try {
            link.setInitNode(Integer.parseInt(rowElements[columnIndex++]));
            link.setTermNode(Integer.parseInt(rowElements[columnIndex++]));

            link.setCapacity(Double.parseDouble(rowElements[columnIndex++]));
            link.setLength(Double.parseDouble(rowElements[columnIndex++]));
            link.setFreeFlowTime(Double.parseDouble(rowElements[columnIndex++]));
            link.setAlfa(Double.parseDouble(rowElements[columnIndex++]));
            link.setExponent(Double.parseDouble(rowElements[columnIndex++]));
            link.setSpeed(Double.parseDouble(rowElements[columnIndex++]));

        } catch (NumberFormatException ex) {
            throw new TntpParsingException(ex.getMessage());
        }

        return link;
    }

    public List<Link> getLinks() {
        return links;
    }
}
