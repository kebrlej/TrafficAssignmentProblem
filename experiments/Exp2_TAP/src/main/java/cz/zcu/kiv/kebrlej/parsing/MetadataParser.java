package cz.zcu.kiv.kebrlej.parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetadataParser {

    private static final String END_OF_METADATA_TAG = "<END OF METADATA>";

    private Map<String, String> parsedMetadata = new HashMap<>();

    public Map<String, String> getParsedMetadata() {
        return parsedMetadata;
    }

    public void parseAllMetadata(BufferedReader br) throws IOException {
        String line;

        while ((line = br.readLine()) != null
                && !this.isEndOfMetadata(line)) {

            parseMetadataLine(line);
        }
    }

    public boolean isEndOfMetadata(String line) {
        return line.contains(END_OF_METADATA_TAG);
    }


    public void parseMetadataLine(String line) {
        Pattern metadataTagPattern = Pattern.compile("\\<.*\\>");
        Matcher matcher = metadataTagPattern.matcher(line);

        if (matcher.find()) {
            String match = matcher.group();
            String metadataValue = line.replace(match, "").strip();
            parsedMetadata.put(match, metadataValue);
        }
    }

}
