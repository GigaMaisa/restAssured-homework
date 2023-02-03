package ge.tbc.testautomation.task2;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.testng.asserts.SoftAssert;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import static io.restassured.RestAssured.given;

public class Task2Test {
    SoftAssert softAssert;

    @BeforeClass
    public void setUp(){
        RestAssured.baseURI="https://restful-booker.herokuapp.com/";
        softAssert = new SoftAssert();
    }

    public static String getAuthorizedToken(){
        JSONObject jsonMapForToken = new JSONObject();
        jsonMapForToken.put("username", "admin");
        jsonMapForToken.put("password", "password123");

        Response resp =
                given().contentType(ContentType.JSON).
                        body(jsonMapForToken.toJSONString()).post("/auth");
        JsonPath jsonPath = resp.jsonPath();
        return jsonPath.getString("token");
    }

    public static void putRequestFunc(String token, int id){
        JSONObject jsonObject = new JSONObject();
        HashMap<String, String> map = new HashMap<>();
        map.put("checkin", "2019-06-17");
        map.put("checkout", "2021-02-12");

        jsonObject.put("firstname", "Charles");
        jsonObject.put("lastname", "Leclerc");
        jsonObject.put("totalprice", 410);
        jsonObject.put("depositpaid", false);
        jsonObject.put("bookingdates", map);
        jsonObject.put("additionalneeds", "new strategist");

        RequestSpecification request = given().
                contentType(ContentType.JSON).
                body(jsonObject.toJSONString());

        Response response = given().filter(new AllureRestAssured()).spec(request).
                cookie("token",token).put("booking/"+id);

        response.then().log().ifStatusCodeIsEqualTo(201);
        System.out.println(response.getBody().asPrettyString());
        System.out.println(response.then().extract().statusCode());
    }

    @Test
    public void runFirstTest(){
        Random random = new Random();
        int generatedId = random.nextInt(1, 20);
        putRequestFunc(getAuthorizedToken(), generatedId);
    }

    @Test
    public void runSecondTest() throws ParseException {
        RestAssured.baseURI="https://chercher.tech/";
        Response response = given().when().get("sample/api/product/read");
        JsonPath jsonPath = response.jsonPath();

        List<String> fullResponse = jsonPath.get("records");
        System.out.println(fullResponse);

        String lastItemNameFromApi = jsonPath.getString("records[-1].name");
        Assert.assertEquals(lastItemNameFromApi, "test");

        List<String> createdAtValuesFromApi = jsonPath.getList("records.created");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();

        for (String timeCreated : createdAtValuesFromApi) {
            Date dateTime = sdf.parse(timeCreated);
            softAssert.assertTrue(currentDate.compareTo(dateTime) > 0);
        }
    }

    @AfterClass
    public void tearDown(){
        softAssert.assertAll();
    }
}
