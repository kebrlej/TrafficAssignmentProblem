package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.ODFlows;
import cz.zcu.kiv.kebrlej.ODPair;
import cz.zcu.kiv.kebrlej.ODPairSolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlowFileParser extends FileParser {


    @Override
    protected String getTntpFileExtension() {
         return "_flow.tntp";
    }

    public List<ODPairSolution> parseFlowData(BufferedReader br) throws IOException, TntpParsingException {

        List<ODPairSolution> odPairSolutions = new ArrayList<>();

        br.readLine(); // skip first line
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0 || line.contains("~")) {
                continue; // empty or comment line -> skip
            } else {
                odPairSolutions.add(parseFlowLine(line));
            }
        }

        return odPairSolutions;
    }


    public ODPairSolution parseFlowLine(String line) throws TntpParsingException {
        String[] rowElements = line.split("\t");
        if(rowElements.length < 4){
            throw new TntpParsingException("Row does not contain all columns.");
        }

        ODPairSolution odPairSolution = new ODPairSolution(
                Integer.parseInt(rowElements[0].trim()),
                Integer.parseInt(rowElements[1].trim()),
                Double.parseDouble(rowElements[2].trim()),
                Double.parseDouble(rowElements[3].trim())
        );

        return odPairSolution;
    }
}
