package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDataları.TestDataJsonPlaceholder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C25_Put_DeSerialization extends BaseUrlJsonPlaceholder {
     /*

        https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
        request yolladigimizda donen response’in response body’sinin asagida verilen ile ayni
        oldugunu test ediniz

                Request Body :

        {
            "title": "Ahmet",
            "body": "Merhaba",
            "userId": 10,
            "id": 70
        }

                Expected Response body :

        {
            "title": "Ahmet",
            "body": "Merhaba",
            "userId": 10,
            "id": 70
        }

     */

    @Test
    public void test01(){

        // 1- End-point ve request body olustur.

        specJsonPlaceholder.pathParams("pp1","posts","pp2",70);

        // Request Body'sını MAP olarak olusturalım

        Map<String,Object> requestBodyMap = TestDataJsonPlaceholder.bodyOlusturMap();
        // System.out.println("Request Body : " + requestBodyMap );

        // 2- Expected data olustur.
        Map<String, Object> expectedDataMap = TestDataJsonPlaceholder.bodyOlusturMap();

        // 3- Request gonderıp donen response'ı kaydet

        Response response = given().spec(specJsonPlaceholder)
                .when().contentType(ContentType.JSON)
                .body(requestBodyMap)
                .put("/{pp1}/{pp2}");

        // response.prettyPrint();

        // 4- Assertion

        // Assertion yapabılmemız ıcın response'ı map'e cevırmemız gerekırç (De-Serialization)

        Map<String,Object> responseMap = response.as(HashMap.class);

        // System.out.println("Response Map : " + responseMap);
        // expectedData (Map) <===>  responseMap (Map) artık assert edebılırız.

        Assert.assertEquals(expectedDataMap.get("title"),responseMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("body"),responseMap.get("body"));
        Assert.assertEquals(expectedDataMap.get("userId"),responseMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"),responseMap.get("id"));

    }
}
