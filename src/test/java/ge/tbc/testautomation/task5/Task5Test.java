package ge.tbc.testautomation.task5;

import ge.tbc.testautomation.task5.data.ResourcesClass;
import ge.tbc.testautomation.task5.steps.FirstTestSteps;
import ge.tbc.testautomation.task5.steps.SecondTestSteps;
import ge.tbc.testautomation.task5.utils.GetXmlParser;
import ge.tbc.testautomation.task5.utils.ReadFileFromResources;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.net.URISyntaxException;

public class Task5Test {
    FirstTestSteps firstTestSteps;
    SecondTestSteps secondTestSteps;

    @BeforeClass
    public void setUp(){
        firstTestSteps = new FirstTestSteps();
        secondTestSteps = new SecondTestSteps();
    }

    @Test
    public void runFirstTest(){
        firstTestSteps.validateContinentNameCount().validateContinents().validateAnNodeContinentValue().validateLastContinentName();
    }

    @Test
    public void runSecondTest() throws URISyntaxException{
        File file = ReadFileFromResources.readFile(ResourcesClass.RESOURCE_FILE_NAME);
        Response response = secondTestSteps.getResponseFromApi(file);
        XmlPath xmlPath = GetXmlParser.getXmlParserFromResponse(response);

        secondTestSteps.validateHomeStreet(xmlPath).validateOfficeZip(xmlPath);
    }
}
