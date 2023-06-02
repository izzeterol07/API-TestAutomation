package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDataları.TestDataHerokuapp;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C27_Post_Deserialization extends BaseUrlHerokuapp {

    /*

        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
        gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body
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

        Response Body // expected data
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

        Map<String,Object> requestBodyMap = TestDataHerokuapp.requestBodyMapOlustur();

        // 2- Expected Data olustur.

        Map<String,Object> expectedData = TestDataHerokuapp.responseBodyMapOlustur() ;

        // 3- Request gonder ve donen Response'ı kaydet.
        Response response = given().spec(specHerokuapp)
                .when().contentType(ContentType.JSON)
                .body(requestBodyMap)
                .post("/{pp1}");

        // response.prettyPrint();

        Map<String,Object> responseMap = response.as(HashMap.class);
        //System.out.println(responseMap);


        // 4- Assertions

        Assert.assertEquals(((Map)expectedData.get("booking")).get("firstname"),
                            ((Map)(responseMap.get("booking"))).get("firstname"));

        Assert.assertEquals(((Map)expectedData.get("booking")).get("lastname"),
                            ((Map)(responseMap.get("booking"))).get("lastname"));

        Assert.assertEquals(((Map)expectedData.get("booking")).get("totalprice"),
                            ((Map)(responseMap.get("booking"))).get("totalprice"));

        Assert.assertEquals(((Map)expectedData.get("booking")).get("depositpaid"),
                            ((Map)(responseMap.get("booking"))).get("depositpaid"));

        Assert.assertEquals(((Map)expectedData.get("booking")).get("additionalneeds"),
                            ((Map)(responseMap.get("booking"))).get("additionalneeds"));

        Assert.assertEquals(((Map)((Map) expectedData.get("booking")).get("bookingdates")).get("checkin"),
                            ((Map)((Map)responseMap.get("booking")).get("bookingdates")).get("checkin"));

        Assert.assertEquals(((Map)((Map) expectedData.get("booking")).get("bookingdates")).get("checkout"),
                            ((Map)((Map)responseMap.get("booking")).get("bookingdates")).get("checkout"));
    }
}
