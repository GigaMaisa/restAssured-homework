package ge.tbc.testautomation.task5.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ReadFileFromResources {
    public static File readFile(String fileName) throws URISyntaxException {
        URL resourceFile = ReadFileFromResources.class.getClassLoader().getResource(fileName);
        assert resourceFile != null;
        return new File(resourceFile.toURI());
    }
}
