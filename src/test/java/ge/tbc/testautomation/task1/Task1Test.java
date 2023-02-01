package ge.tbc.testautomation.task1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Task1Test {
    private static final String URL = "http://ergast.com/api/f1/";

    private static Response getResponseFromCircuitsListApi(){
        RestAssured.baseURI = URL;
        return given().
                when().
                get("2017/circuits.json");
    }

    private static Response getResponseFromOneCircuitApi(String circuitID){
        RestAssured.baseURI = URL;
        return given().
                when().
                get("/circuits/"+circuitID+".json");
    }

    public static HashMap<String, String> getIndexedCircuit(int index){
        JsonPath js = getResponseFromCircuitsListApi().jsonPath();
        return js.get("MRData.CircuitTable.Circuits["+index+"]");
    }

    public static HashMap<String, String> getIndexedCircuitLocation(int index){
        JsonPath js = getResponseFromCircuitsListApi().jsonPath();
        return js.get("MRData.CircuitTable.Circuits["+index+"].Location");
    }

    public static String oneCircuitCountry(String circuitId){
        JsonPath js = getResponseFromOneCircuitApi(circuitId).jsonPath();
        HashMap<String, String> map = js.get("MRData.CircuitTable.Circuits[0].Location");
        return map.get("country");
    }

    @Test(dataProvider = "dataProviderMethod", dataProviderClass = DataProvider.class)
    public void runTest(int index, String country){
        String indexOneCircuitCountry = oneCircuitCountry(getIndexedCircuit(index).get("circuitId"));
        String indexOneCircuitCountryFromListResponse = getIndexedCircuitLocation(index).get("country");

        Assert.assertEquals(indexOneCircuitCountry, indexOneCircuitCountryFromListResponse);
        Assert.assertEquals(indexOneCircuitCountry, country);
        Assert.assertEquals(indexOneCircuitCountryFromListResponse, country);
    }
}
