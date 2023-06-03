package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoJsonPlaceholder;
import testDataları.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;

public class C28_Put_PjoClass extends BaseUrlJsonPlaceholder {

    /*

            https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir PUT
            request yolladigimizda donen response’in
            status kodunun 200,
            content type'ının "application/json; charset=utf-8",
            Connection header degerının "keep/alive" ve
            response body’sinin asagida verilen ile ayni
            oldugunu test ediniz.


            Request Body :

                {
                    "title": "Ahmet",
                    "body": "Merhaba",
                    "userId": 10,
                    "id": 70
                }

            Expected Data :

                {
                    "title": "Ahmet",
                    "body": "Merhaba",
                    "userId": 10,
                    "id": 70
                }
     */

    @Test
    public void test01(){

        // 1- End-point ve request body olustur
        specJsonPlaceholder.pathParams("pp1","posts","pp2",70);

        PojoJsonPlaceholder requestBodyPojo = new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);

        // 2- Soruda varsa Expected data olustur

        PojoJsonPlaceholder expectedDataPojo = new PojoJsonPlaceholder("Ahmet","Merhaba",10,70);

        // 3- Request gonder ve donen Response'ı kaydet

        Response response =given().spec(specJsonPlaceholder).contentType(ContentType.JSON)
                                .when().body(requestBodyPojo)
                                .put("/{pp1}/{pp2}");
        // response.prettyPrint();

        PojoJsonPlaceholder responsePojo = response.as(PojoJsonPlaceholder.class);

        // 4- Assertion
        // Expected Data (Pojo) <====> response (JSON)
        // ExpectedData (Pojo) <====> responsePojo (Pojo)

        Assert.assertEquals(TestDataJsonPlaceholder.basariliSorguStatusCode,response.statusCode());
        Assert.assertEquals(TestDataJsonPlaceholder.contentType,response.contentType());
        Assert.assertEquals(TestDataJsonPlaceholder.headerConnection,response.header("Connection"));

        Assert.assertEquals(expectedDataPojo.getTitle(),responsePojo.getTitle());
        Assert.assertEquals(expectedDataPojo.getId(),responsePojo.getId());
        Assert.assertEquals(expectedDataPojo.getBody(),responsePojo.getBody());
        Assert.assertEquals(expectedDataPojo.getUserId(),responsePojo.getUserId());

    }
}
