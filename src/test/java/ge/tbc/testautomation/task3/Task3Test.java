package ge.tbc.testautomation.task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Task3Test {
    SoftAssert softAssert;

    @BeforeClass
    public void setUp(){
        softAssert = new SoftAssert();
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
    }

    public static String getAuthorizedToken(){
        JSONObject jsonMapForToken = new JSONObject();
        jsonMapForToken.put("username", "admin");
        jsonMapForToken.put("password", "password123");

        Response resp = given().contentType(ContentType.JSON).
                        body(jsonMapForToken.toJSONString()).post("/auth");
        JsonPath jsonPath = resp.jsonPath();
        return jsonPath.getString("token");
    }

    public static Response deleteBooking(String token, int id){
        RequestSpecification req = given().contentType(ContentType.JSON);
        return given().spec(req).cookie("token", token).delete("booking/"+id);
    }

    @Test
    public void runFirstTest(){
        Random random = new Random();
        int id = random.nextInt(1, 99);
        Response response = deleteBooking(getAuthorizedToken(), id);
        response.then().log().all();
        response.then().statusCode(201);
    }

    @Test
    public void runSecondTest(){
        RestAssured.baseURI = "http://ergast.com/";
        Response response = given().when().get("api/f1/2017/circuits.json");

        response.then().assertThat().body("MRData.CircuitTable.Circuits[7]", hasEntry("circuitId","marina_bay"));
        response.then().assertThat().body("MRData.CircuitTable.Circuits.circuitId", hasItem("marina_bay"));
        response.then().assertThat().body("MRData.CircuitTable.Circuits[1].Location.country", equalTo("USA"));
        response.then().assertThat().body("MRData.CircuitTable.Circuits[-1].Location.country", equalTo("UAE"));

        double lastCircuitLong = Double.parseDouble(response.jsonPath().get("MRData.CircuitTable.Circuits[-1].Location.long"));

        assertThat(lastCircuitLong, anyOf(greaterThan(1.0), equalTo(10.0)));
        softAssert.assertTrue(lastCircuitLong > 1 || lastCircuitLong==10);

    }

    @AfterClass
    public void tearDown(){
        softAssert.assertAll();
    }
}
