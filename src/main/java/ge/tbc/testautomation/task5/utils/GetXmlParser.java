package ge.tbc.testautomation.task5.utils;

import ge.tbc.testautomation.task5.data.ResourcesClass;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import io.restassured.response.Response;

public class GetXmlParser {
    public static XmlPath getXmlParserFromResponse(Response response){
        return response.xmlPath();
    }
}
