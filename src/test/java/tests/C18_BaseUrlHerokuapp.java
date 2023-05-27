package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C18_BaseUrlHerokuapp extends BaseUrlHerokuapp {

    /*
        https://restful-booker.herokuapp.com/booking endpointine bir GET request gonderdigimizde
        donen response’un
            status code’unun 200 oldugunu ve
            Response’ta 12 booking oldugunu
        test edin

     */

    @Test
    public void test01() {

        // 1- End-point ve request body olustur.
        specHerokuapp.pathParam("pp1", "booking");

        // 2- Expected Body olusturma
        // 3- Request gonderıp, response'ı kaydetme
        Response response = given()
                .when().spec(specHerokuapp)
                .get("/{pp1}");

        // response.prettyPrint();

        JsonPath responseJsonPath = response.jsonPath();                   // response'ı jsonpath e cevırdık
        int bookingSize = responseJsonPath.getList("booking").size(); // jsonpath objesınden liste olarak size'ı elde ettik
        System.out.println(bookingSize);

        // 4- Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasSize(bookingSize));
    }

    @Test
    public void test02() {

        /*
            2- https://restful-booker.herokuapp.com/booking endpointine
            asagıdakı body’ye sahip bir POST request gonderdigimizde
            donen response’un;
                status code’unun 200 oldugunu ve
                “firstname” degerinin “Ahmet”
            oldugunu test edin.

            Request Body

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

         */

        // 1- End-point ve request body olustur.
        specHerokuapp.pathParam("pp1", "booking");

        JSONObject requestBody = new JSONObject();
        JSONObject rezervasyonTarihleriJsonObj = new JSONObject();

        rezervasyonTarihleriJsonObj.put("checkin","2021-06-01");
        rezervasyonTarihleriJsonObj.put("checkout","2021-06-10");

        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Yagmur");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",rezervasyonTarihleriJsonObj);
        requestBody.put("additionalneeds","wi-fi");

        // 2- Expected body
        // 3- Request gonderıp, response'ı kaydet

        Response response = given().contentType(ContentType.JSON)
                                    .when().spec(specHerokuapp).body(requestBody.toString())
                                    .post("/{pp1}");

        // response.prettyPrint();

        // 4- Assertions

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname",Matchers.equalTo("Ahmet"));

    }
}
