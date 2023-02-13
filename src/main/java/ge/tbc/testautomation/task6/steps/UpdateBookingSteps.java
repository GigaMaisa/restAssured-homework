package ge.tbc.testautomation.task6.steps;

import ge.tbc.testautomation.task6.data.StaticData;
import ge.tbc.testautomation.task6.pojos.request.UpdateBookingRequest;
import ge.tbc.testautomation.task6.pojos.response.UpdateBookingResponse;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Objects;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateBookingSteps {
    @Step
    private int getRandomId(){
        Random random = new Random();
        return random.nextInt(1, 100);
    }

    @Step
    public UpdateBookingResponse updateBookingApiCall(String token, UpdateBookingRequest bookingRequest){
        RestAssured.baseURI = StaticData.BASE_URI;
        int id = getRandomId();

        RequestSpecification req = given().contentType(ContentType.JSON).body(bookingRequest.toString());
        Response response = given().spec(req).cookie("token", token).
                filter(new AllureRestAssured()).put("booking/"+ id);

        response.then().statusCode(200);
        return response.as(UpdateBookingResponse.class);
    }

    @Step
    public UpdateBookingSteps validateFirstName(UpdateBookingResponse request){
        if (Objects.equals(request.getFirstName(), "Charles")){
            assertThat(request.getFirstName(), equalTo(StaticData.EXPECTED_FIRSTNAME_API_CALL1));
        }
        return this;
    }

    @Step
    public UpdateBookingSteps validateAdditionalNeed(UpdateBookingResponse request){
        if (Objects.equals(request.getFirstName(), "Lewis")){
            assertThat(request.getAdditionalNeeds(), equalTo(StaticData.EXPECTED_ADDITIONAL_NEED_API_CALL2));
        }
        return this;
    }
}
