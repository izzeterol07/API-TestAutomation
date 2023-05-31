package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataları.TestDataHerokuapp;

import static io.restassured.RestAssured.given;

public class C24_Post_KompleksTestDataKullanimi extends BaseUrlHerokuapp {

    /*

        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
        gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body :

        {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"
                },
            "additionalneeds" : "wi-fi"
        }

        Response Body (Expected Body) :

        {
            "bookingid": 24,
            "booking": {
                "firstname": "Ahmet",
                "lastname": "Bulut",
                "totalprice": 500,
                "depositpaid": false,
                "bookingdates": {
                        "checkin": "2021-06-01",
                        "checkout": "2021-06-10"
                     },
                "additionalneeds": "wi-fi"
            }
        }

     */

    @Test
    public void test01(){

        // 1- End-point ve request body olustur.
        specHerokuapp.pathParam("pp1","booking");

        JSONObject requestBody = TestDataHerokuapp.jsonRequestBodyOlustur();

        // 2- Soruda varsa Expected Data olustur
        JSONObject expectedData = TestDataHerokuapp.jsonResponseBodyOlustur();

        // 3- Request gonderip donen Response'ı kaydet
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post("/{pp1}");

        // 4- Assertions

        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.getJSONObject("booking").getString("firstname"),
                            responseJsonPath.getString("booking.firstname"));

        Assert.assertEquals(expectedData.getJSONObject("booking").getString("lastname"),
                            responseJsonPath.getString("booking.lastname"));

        Assert.assertEquals(expectedData.getJSONObject("booking").getInt("totalprice"),
                            responseJsonPath.getInt("booking.totalprice"));

        Assert.assertEquals(expectedData.getJSONObject("booking").getBoolean("depositpaid"),
                            responseJsonPath.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkin"),
                            responseJsonPath.getString("booking.bookingdates.checkin"));

        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").getString("checkout"),
                            responseJsonPath.getString("booking.bookingdates.checkout"));

        Assert.assertEquals(expectedData.getJSONObject("booking").getString("additionalneeds"),
                            responseJsonPath.getString("booking.additionalneeds"));

    }
}
