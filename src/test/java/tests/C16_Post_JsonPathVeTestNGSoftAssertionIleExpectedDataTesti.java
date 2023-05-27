package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C16_Post_JsonPathVeTestNGSoftAssertionIleExpectedDataTesti {


    @Test
    public void test01() {

        /*

        https://dummy.restapiexample.com/api/v1/create url’ine asagidaki body’ye sahip bir POST
        request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

        Request Body

        {
            "status": "success",
            "data": {
                "name": “Ahmet",
                "salary": "1230",
                "age": "44",
                "id": 40
            }
        }

        Response Body

        {
        "status": "success",
        "data": {
            "status": "success",
            "data": {
                "name": “Ahmet",
                "salary": "1230",
                "age": "44",
                "id": 40 }
        },
        "message": "Successfully! Record has been added."
        }

         */


        // 1- End-point ve request body olustur

        String url = "https://dummy.restapiexample.com/api/v1/create";

        JSONObject requestBody = new JSONObject();

        JSONObject dataBilgileriJson = new JSONObject();

        dataBilgileriJson.put("name", "Ahmet");
        dataBilgileriJson.put("salary", "1230");
        dataBilgileriJson.put("age", "25");
        dataBilgileriJson.put("id", 40);

        requestBody.put("status", "success");
        requestBody.put("data", dataBilgileriJson);

        // 2- Expected body olustur.

        JSONObject expectedBody = new JSONObject();

        expectedBody.put("status", "success");
        expectedBody.put("data", requestBody);
        expectedBody.put("message", "Successfully! Record has been added.");

        // System.out.println(expectedBody.toString());

        // 3- Request gonderıp donen response'u kaydet

        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);

        response.prettyPrint();

        // Assertions

        SoftAssert softAssert = new SoftAssert();

        JsonPath responseJsonPath = response.jsonPath();

        softAssert.assertEquals(responseJsonPath.get("status"),expectedBody.get("status"));
        softAssert.assertEquals(responseJsonPath.get("message"),expectedBody.get("message"));
        softAssert.assertEquals(responseJsonPath.get("data.data.name"),
                                expectedBody.getJSONObject("data").getJSONObject("data").get("name"));
        softAssert.assertEquals(responseJsonPath.get("data.data.salary"),
                                expectedBody.getJSONObject("data").getJSONObject("data").get("salary"));
        softAssert.assertEquals(responseJsonPath.get("data.data.age"),
                                expectedBody.getJSONObject("data").getJSONObject("data").get("age"));
        softAssert.assertEquals(responseJsonPath.get("data.data.id"),
                                expectedBody.getJSONObject("data").getJSONObject("data").get("id"));
        softAssert.assertEquals(responseJsonPath.get("data.status"),
                                expectedBody.getJSONObject("data").get("status"));

        softAssert.assertAll();


    }
}
