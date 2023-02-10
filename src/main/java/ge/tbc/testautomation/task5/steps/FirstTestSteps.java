package ge.tbc.testautomation.task5.steps;

import ge.tbc.testautomation.task5.data.ExpectedValues;
import ge.tbc.testautomation.task5.data.ResourcesClass;
import ge.tbc.testautomation.task5.utils.GetXmlParser;
import io.qameta.allure.Step;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class FirstTestSteps {
    XmlPath xmlPath = GetXmlParser.getXmlParserFromResponse(getResponseFromApi(ResourcesClass.API_TEST1_URL));

    @Step
    public Response getResponseFromApi(String url){
        return given().when().get(url);
    }

    @Step
    public FirstTestSteps validateContinentNameCount(){
        int count = xmlPath.get("ArrayOftContinent.tContinent.sName.size()");
        assertThat(count, equalTo(ExpectedValues.EXPECTED_SNAME_NODE_COUNT));
        return this;
    }

    @Step
    public FirstTestSteps validateAnNodeContinentValue(){
        String antarcticaNode = xmlPath.get("ArrayOftContinent.tContinent.findAll { tContinent -> def sCode = tContinent.sCode; sCode=='AN' }.sName");
        assertThat(antarcticaNode, equalTo(ExpectedValues.EXPECTED_AN_NODE_VALUE));
        return this;
    }

    @Step
    public FirstTestSteps validateLastContinentName(){
        String lastContinent = xmlPath.getString("ArrayOftContinent.tContinent[-1].sName");
        System.out.println(lastContinent);
        assertThat(lastContinent, equalTo(ExpectedValues.EXPECTED_LAST_CONTINENT));
        return this;
    }

    @Step
    public FirstTestSteps validateContinents(){
        List<String> actualContinents = xmlPath.getList("ArrayOftContinent.tContinent.sName");
        List<String> expectedContinents = ExpectedValues.EXPECTED_CONTINENTS;
        for (String continent : actualContinents) {
            assertThat(expectedContinents, hasItem(continent));
        }
        return this;
    }
}
