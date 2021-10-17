package cz.zcu.kiv.kebrlej.parsing;

import java.io.*;

public abstract class FileParser {

    protected abstract String getTntpFileExtension();

    public BufferedReader prepareBufferedReader(String fileAbsPath) throws FileNotFoundException {
        return new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileAbsPath)
                )
        );
    }

    public static String getTestResourcesAbsolutePath() {
        File file = new File("src/test/resources");
        return file.getAbsolutePath();
    }


}
