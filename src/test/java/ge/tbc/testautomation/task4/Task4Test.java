package ge.tbc.testautomation.task4;

import ge.tbc.testautomation.task4.test1.RegistrationFailedResponse;
import ge.tbc.testautomation.task4.test1.RegistrationParameters;
import ge.tbc.testautomation.task4.test1.RegistrationSuccessfulResponse;
import ge.tbc.testautomation.task4.test2.CreateUserParameters;
import ge.tbc.testautomation.task4.test2.CreatedUserResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Task4Test {
    SoftAssert softAssert;

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI="https://reqres.in/";
        softAssert = new SoftAssert();
    }

    public static void firstTestMethod(boolean isTestSuccessful, SoftAssert softAssert){
        RegistrationParameters successfulRegParams = new RegistrationParameters("eve.holt@reqres.in", "pistol");
        RegistrationParameters failedRegParams = new RegistrationParameters("sydney@fife");
        Response response;

        if (isTestSuccessful){
            response = given().contentType(ContentType.JSON).when().
                    body(successfulRegParams)
                    .post("api/register");
        } else {
            response = given().contentType(ContentType.JSON).when().
                    body(failedRegParams)
                    .post("api/register");
        }

        if (response.getStatusCode()==200){
            RegistrationSuccessfulResponse successfulResponse = response.as(RegistrationSuccessfulResponse.class);
            System.out.println(successfulResponse.toString());
            softAssert.assertEquals(successfulResponse.getId(), 4);
            softAssert.assertEquals(successfulResponse.getToken(), "QpwL5tke4Pnpja7X4");
        }
        else if(response.getStatusCode()==400){
            RegistrationFailedResponse failedResponse = response.as(RegistrationFailedResponse.class);
            System.out.println(failedResponse.toString());
            softAssert.assertEquals(failedResponse.getError(), "Missing password");
        }
    }

    @Test
    public void runFirstTest(){
        firstTestMethod(true, softAssert);
        firstTestMethod(false, softAssert);
    }

    @Test
    public void runSecondTest(){
        CreateUserParameters createUser = new CreateUserParameters("morpheus", "leader");

        CreatedUserResponse userResponse = given().contentType(ContentType.JSON).when().
                body(createUser).post("/api/users").as(CreatedUserResponse.class);

        System.out.println(userResponse.toString());
    }

    @AfterClass
    public void tearDown(){
        softAssert.assertAll();
    }
}
