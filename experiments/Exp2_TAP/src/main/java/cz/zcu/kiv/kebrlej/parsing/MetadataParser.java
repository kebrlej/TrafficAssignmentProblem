package cz.zcu.kiv.kebrlej.parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetadataParser {

    private static final String END_OF_METADATA_TAG = "<END OF METADATA>";

    public static Map<String, String> parseAllMetadata(BufferedReader br) throws IOException, TntpParsingException {

        Map<String, String> metadata = new HashMap<>();
        String line;
        while ((line = br.readLine()) != null
                && !isEndOfMetadata(line)) {
            Map.Entry<String, String> tagValuePair = parseMetadataLine(line);
            metadata.put(tagValuePair.getKey(), tagValuePair.getValue());
        }
        return metadata;
    }

    public static boolean isEndOfMetadata(String line) {
        return line.contains(END_OF_METADATA_TAG);
    }


    public static Map.Entry<String, String> parseMetadataLine(String line) throws TntpParsingException {
        Pattern metadataTagPattern = Pattern.compile("\\<.*\\>");
        Matcher matcher = metadataTagPattern.matcher(line);

        if (matcher.find()) {
            String match = matcher.group();
            String metadataValue = line.replace(match, "").strip();
            return Map.entry(match, metadataValue);
        } else {
            throw new TntpParsingException("Unable to parse metadata line: " + line);
        }
    }

}
