package cz.zcu.kiv.kebrlej;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    - <END OF METADATA>
    - <FIRST THRU NODE> pruchozi nody 1-n, n az dal jsou pruchozi
    - ~COMMENT LINE
     */
    private static final String END_OF_METADATA_TAG = "<END OF METADATA>";
    private static final String NUMBER_OF_ZONES_TAG= "<NUMBER_OF_ZONES>";
    private static final String NUMBER_OF_NODES_TAG= "<NUMBER_OF_NODES>";
    private static final String FIRST_THRU_NODE_TAG= "<FIRST_THRU_NODE>";
    private static final String NUMBER_OF_LINKS_TAG= "<NUMBER_OF_LINKS>";

    public Map<String, String> params = new HashMap<>();

    public void parseMetadataLine(String line){
        //split comment
        Pattern p = Pattern.compile("<*>");

        //extract tag
        // extract value -> to int
        //
    }

    public boolean isEndOfMetadata(String line){
        return line.contains(NetworkParser.END_OF_METADATA_TAG);
    }

//    public void readMetadata(BufferedReader br) throws IOException {
//        for (String line = br.readLine();
//             line != null && line.startsWith("<") && !line.contains(END_OF_METADATA_TAG) ;
//             line = br.readLine()) {
//
//            parseMetadataLine(line);
//        }
//    }


    public NetworkParser(String dir, String networkName) {

//        if (!file.exists()) {
//            throw new RuntimeException("Network file: " + filePath + "  not found!!!");
//        }


    }

}
