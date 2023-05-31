package tests;

import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataları.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;

public class C21_Get_TestDataClassKullanimiDinamik extends BaseUrlJsonPlaceholder {

    /*
        https://jsonplaceholder.typicode.com/posts/40 url'ine bir GET request yolladigimizda donen
        response’in
            status kodunun 200
            ve response body’sinin asagida verilen ile ayni oldugunutest ediniz

        Response body :
        {
            "userId": 4,
            "id": 40,
            "title": "enim quo cumque",
            "body": "ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia
                     quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam"
        }
     */

    @Test
    public void test01(){

        // 1- End-point ve Request body olustur.
        specJsonPlaceholder.pathParams("pp1","posts","pp2",40);

        // 2 - Expected Body olustur
        JSONObject expectedData = TestDataJsonPlaceholder.jsonBodyOlustur(4,40,"enim quo cumque","ut voluptatum aliquid illo tenetur nemo sequi quo facilis\nipsum rem optio mollitia quas\nvoluptatem eum voluptas qui\nunde omnis voluptatem iure quasi maxime voluptas nam");
        // System.out.println(expectedData);

        // 3- Request gonder ve donen response ı kaydet
        Response response = given().spec(specJsonPlaceholder)
                .when()
                .get("/{pp1}/{pp2}");

        // response.prettyPrint();

        // 4- Assertions

        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals(TestDataJsonPlaceholder.basariliSorguStatusCode,response.statusCode());
        Assert.assertEquals(expectedData.getInt("userId"),responseJsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.getInt("id"),responseJsonPath.getInt("id"));
        Assert.assertEquals(expectedData.getString("title"),responseJsonPath.getString("title"));
        Assert.assertEquals(expectedData.getString("body"),responseJsonPath.getString("body"));

    }

}
