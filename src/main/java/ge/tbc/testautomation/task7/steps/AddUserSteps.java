package ge.tbc.testautomation.task7.steps;

import ge.tbc.testautomation.task7.data.StaticData;
import ge.tbc.testautomation.task7.models.request.AddUserRequest;
import ge.tbc.testautomation.task7.models.response.AddUserFullResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddUserSteps {
    @Step
    public AddUserFullResponse getAddUserResponse(){
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        AddUserRequest addUserRequest = new AddUserRequest(StaticData.USER_NAME, StaticData.PASSWORD);
        RequestSpecification req = given().contentType(ContentType.JSON).body(addUserRequest.toString());
        Response response = given().spec(req).post("/Account/v1/User");
        return response.as(AddUserFullResponse.class);
    }

    @Step
    public void validateBooksList(AddUserFullResponse user){
        assertThat(user.books().size(), equalTo(0));
    }
}
