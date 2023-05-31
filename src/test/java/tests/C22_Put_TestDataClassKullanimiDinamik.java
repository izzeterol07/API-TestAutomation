package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataları.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;

public class C22_Put_TestDataClassKullanimiDinamik extends BaseUrlJsonPlaceholder {

    /*

        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT request
        yolladigimizda donen response’in
            status kodunun 200, content type’inin “application/json; charset=utf-8”,
            Connection header degerinin “keep-alive”
            ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

        Request Body :
            {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
            }

        Response Body (Expected Data) :
            {
                "title": "Ahmet",
                "body": "Merhaba",
                "userId": 10,
                "id": 70
            }
     */

    @Test
    public void test01() {

        // 1- End-point ve request body olustur
        specJsonPlaceholder.pathParams("pp1", "posts", "pp2", 70);

        JSONObject requestBody = TestDataJsonPlaceholder.jsonBodyOlustur(10, 70, "Ahmet", "Merhaba");

        // 2- Expected body olustur
        JSONObject expectedData = TestDataJsonPlaceholder.jsonBodyOlustur(10, 70, "Ahmet", "Merhaba");

        // Request gonder ve donen response'ı kaydet

        Response response = given().spec(specJsonPlaceholder)
                .when().body(requestBody.toString()).contentType(ContentType.JSON)
                .put("/{pp1}/{pp2}");

        // response.prettyPrint();

        // 4- Assertions

        JsonPath responseJsonPath = response.jsonPath();

        // status kodunun 200
        Assert.assertEquals(TestDataJsonPlaceholder.basariliSorguStatusCode, response.statusCode());
        // content type’inin “application/json; charset=utf-8”
        Assert.assertEquals(TestDataJsonPlaceholder.contentType, response.contentType());
        // Connection header degerinin “keep-alive”
        Assert.assertEquals(TestDataJsonPlaceholder.headerConnection, response.header("Connection"));
        // response body’sinin asagida verilen ile ayni oldugunu test ediniz
        Assert.assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        Assert.assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        Assert.assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));
    }
}
