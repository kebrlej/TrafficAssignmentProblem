package cz.zcu.kiv.kebrlej;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkParser {


    /*
    _trips - OD matrix
    _net - network description
    _flow - correct equilibrium flows

    TNTP
    - TAB delimiter
    - line ends with SEMI-COLON
    - <METADATA>_space_NUMBER
    - <FIRST THRU NODE> pruchozi nody 1-n, n az dal jsou pruchozi
    - <END OF METADATA>
    - ~COMMENT LINE
     */
    private static final String END_OF_METADATA_TAG = "<END OF METADATA>";
    private static final String NUMBER_OF_ZONES_TAG = "<NUMBER_OF_ZONES>";
    private static final String NUMBER_OF_NODES_TAG = "<NUMBER_OF_NODES>";
    private static final String FIRST_THRU_NODE_TAG = "<FIRST_THRU_NODE>";
    private static final String NUMBER_OF_LINKS_TAG = "<NUMBER_OF_LINKS>";

    public Map<String, String> metadata = new HashMap<>();
    private List<Link> links = new ArrayList<>();

    public void parseMetadataLine(String line) {
        Pattern p = Pattern.compile("\\<.*\\>");
        Matcher m = p.matcher(line);

        if (m.find()) {
            String match = m.group();
            String data = line.replace(match, "").strip();
            metadata.put(match, data);
        }
    }

    public boolean isEndOfMetadata(String line) {
        return line.contains(NetworkParser.END_OF_METADATA_TAG);
    }

    public void readMetadata(BufferedReader br) throws IOException {
        String line;

        while ((line = br.readLine()) != null
                && !this.isEndOfMetadata(line)) {
            parseMetadataLine(line);
        }

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



    public NetworkParser(String dir, String networkName) {

//        if (!file.exists()) {
//            throw new RuntimeException("Network file: " + filePath + "  not found!!!");
//        }


    }

}
