package ge.tbc.testautomation.task6.steps;

import ge.tbc.testautomation.task6.data.StaticData;
import ge.tbc.testautomation.task6.pojos.request.TokenRequest;
import ge.tbc.testautomation.task6.pojos.response.TokenResponse;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class GetTokenResponse {
    @Step
    public String getTokenFromApi(){
        RestAssured.baseURI = StaticData.BASE_URI;
        TokenRequest tokenRequest = new TokenRequest(StaticData.TOKEN_USERNAME, StaticData.TOKEN_PASSWORD);
        TokenResponse tokenResponse = given().filter(new AllureRestAssured()).contentType(ContentType.JSON).
                body(tokenRequest).post("/auth").as(TokenResponse.class);
        return tokenResponse.getToken();
    }
}
