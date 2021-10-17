package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.DestinationFlowPair;
import cz.zcu.kiv.kebrlej.ODFlows;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TripsFileParser extends FileParser {

    @Override
    protected String getTntpFileExtension() {
        return "_trips.tntp";
    }

    public Integer parseOriginId(String line) {
        Pattern originPattern = Pattern.compile("Origin (\\d+)");
        Matcher matcher = originPattern.matcher(line);
        boolean result = matcher.find();
        return Integer.parseInt(matcher.group(1));
    }

    public List<DestinationFlowPair> parseDestinationFlows(
            String line, List<DestinationFlowPair> destinationFlows) {

        Pattern originPattern
                = Pattern.compile("\\s*(\\d+)\\s*:\\s*(\\d+[\\.\\d+]*)");
        Matcher matcher = originPattern.matcher(line);

        while (matcher.find()) {
            String dest = matcher.group(1);
            String flow = matcher.group(2);

            destinationFlows.add(
                    new DestinationFlowPair(
                            Integer.parseInt(dest),
                            Double.parseDouble(flow)
                    )
            );
        }

        return destinationFlows;
    }

    public List<ODFlows> parseTripsData(BufferedReader br) throws IOException {
        List<ODFlows> ODFlowsList = new ArrayList<>();
        ODFlows ODFlows;
        String line;
        while ((line = br.readLine()) != null) {
            if (isWhiteSpaceOrCommentLine(line)) {
                //empty or comment line
                continue;
            }
            ODFlows = new ODFlows();
            ODFlows.setOriginId(this.parseOriginId(line));


            List<DestinationFlowPair> destinationFlows = new ArrayList<>();
            while ((line = br.readLine()) != null
                    && false == isWhiteSpaceOrCommentLine(line)) {

                this.parseDestinationFlows(line, destinationFlows);
            }
            ODFlows.setDestFlows(destinationFlows);
            ODFlowsList.add(ODFlows);
        }

        return ODFlowsList;
    }

    private boolean isWhiteSpaceOrCommentLine(String line) {
        return line.trim().length() == 0 || line.contains("~");
    }


}
