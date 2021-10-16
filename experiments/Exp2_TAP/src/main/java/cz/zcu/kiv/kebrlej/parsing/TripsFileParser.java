package cz.zcu.kiv.kebrlej.parsing;

import cz.zcu.kiv.kebrlej.DestinationFlow;
import cz.zcu.kiv.kebrlej.OriginTrips;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TripsFileParser {


    /*
    1) metadata
     */

//    public OriginTrips parseOriginTrips(B){
//
//
//    }


    public Integer parseOriginId(String line) {
        Pattern originPattern = Pattern.compile("Origin (\\d+)");
        Matcher matcher = originPattern.matcher(line);
        boolean result = matcher.matches();
        String number = matcher.group(1);
        return Integer.parseInt(matcher.group(1));
    }

    public List<DestinationFlow> parseDestinationFlows(String line) {
        List<DestinationFlow> destinationFlows = new ArrayList<>();

        Pattern originPattern
                = Pattern.compile("\\s*(\\d+)\\s*:\\s*(\\d+[\\.\\d+]*)");
        Matcher matcher = originPattern.matcher(line);

        while (matcher.find()) {
            String dest = matcher.group(1);
            String flow = matcher.group(2);

            destinationFlows.add(
                    new DestinationFlow(
                            Integer.parseInt(dest),
                            Double.parseDouble(flow)
                    )
            );
        }

        return destinationFlows;
    }

    public void parseTripsData(BufferedReader br) throws IOException {
        List<OriginTrips> originTripsList = new ArrayList<>();
        OriginTrips originTrips;
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().length() == 0 || line.contains("~")) {
                //empty or comment line
                continue;
            }
            originTrips = new OriginTrips();
            originTrips.setOriginId(this.parseOriginId(line));

            line = br.readLine();
            originTrips.setDestFlows(this.parseDestinationFlows(line));

        }
    }


}
