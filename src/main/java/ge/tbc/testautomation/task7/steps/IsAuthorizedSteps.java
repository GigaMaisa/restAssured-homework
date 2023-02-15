package ge.tbc.testautomation.task7.steps;

import ge.tbc.testautomation.task7.data.StaticData;
import ge.tbc.testautomation.task7.models.request.IsAuthorizedRequest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class IsAuthorizedSteps {
    @Step
    public Boolean isUserAuthorized(){
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        IsAuthorizedRequest request = new IsAuthorizedRequest(StaticData.USER_NAME, StaticData.PASSWORD);
        RequestSpecification req = given().contentType(ContentType.JSON).body(request.toString());
        Response response = given().spec(req).post("/Account/v1/Authorized");
        String smt = response.then().extract().body().asString();
        return Boolean.parseBoolean(smt.strip());
    }

    @Step
    public void validateUserIsAuthorized(Boolean isUserAuthorized){
        assertThat(isUserAuthorized, equalTo(true));
    }
}
