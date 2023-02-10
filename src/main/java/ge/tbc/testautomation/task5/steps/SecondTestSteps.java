package ge.tbc.testautomation.task5.steps;

import ge.tbc.testautomation.task5.data.ExpectedValues;
import ge.tbc.testautomation.task5.data.ResourcesClass;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SecondTestSteps {
    @Step
    public Response getResponseFromApi(File file){
        return given().contentType(ContentType.XML).given().
                baseUri(ResourcesClass.API_TEST2_BASEURI).basePath(ResourcesClass.API_TEST2_BASEPATH).
                header("SOAPAction",ResourcesClass.API_TEST2_SOAP_ACTION).
                header("Content-Type", ResourcesClass.API_TEST2_CONTENT_TYPE).body(file).post().then().extract().response();
    }

    @Step
    public SecondTestSteps validateHomeStreet(XmlPath xmlPath){
        String street = xmlPath.getString("Envelope.Body.FindPersonResponse.FindPersonResult.Home.Street");
        assertThat(street, equalTo(ExpectedValues.EXPECTED_HOME_STREET));
        return this;
    }

    @Step
    public SecondTestSteps validateOfficeZip(XmlPath xmlPath){
        int zip = xmlPath.getInt("Envelope.Body.FindPersonResponse.FindPersonResult.Office.Zip");
        assertThat(zip, equalTo(ExpectedValues.EXPECTED_OFFICE_ZIP));
        return this;
    }
}
