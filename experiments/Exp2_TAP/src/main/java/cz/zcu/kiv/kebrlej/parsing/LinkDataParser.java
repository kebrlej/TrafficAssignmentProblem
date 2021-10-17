package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.Link;

public class LinkDataParser {


    public static Link parseLinkData(String line) throws TntpParsingException {

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

}
