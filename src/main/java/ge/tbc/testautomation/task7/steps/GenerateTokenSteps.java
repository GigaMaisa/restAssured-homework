package ge.tbc.testautomation.task7.steps;

import ge.tbc.testautomation.task7.data.StaticData;
import ge.tbc.testautomation.task7.models.request.GenerateTokenRequest;
import ge.tbc.testautomation.task7.models.response.GenerateTokenResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GenerateTokenSteps {
    @Step
    public GenerateTokenResponse generateToken(){
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        GenerateTokenRequest tokenRequest = new GenerateTokenRequest(StaticData.USER_NAME, StaticData.PASSWORD);
        RequestSpecification req = given().contentType(ContentType.JSON).body(tokenRequest.toString());
        Response response = given().spec(req).post("/Account/v1/GenerateToken");
        return response.as(GenerateTokenResponse.class);
    }

    @Step
    public GenerateTokenSteps validateResponseStatus(GenerateTokenResponse response){
        assertThat(response.status(), equalTo(StaticData.EXPECTED_TOKEN_STATUS));
        return this;
    }

    @Step
    public GenerateTokenSteps validateResponseResult(GenerateTokenResponse response){
        assertThat(response.result(), equalTo(StaticData.EXPECTED_TOKEN_RESULT));
        return this;
    }
}
