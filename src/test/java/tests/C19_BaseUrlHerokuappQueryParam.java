package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C19_BaseUrlHerokuappQueryParam extends BaseUrlHerokuapp {


    @Test
    public void test01(){

        /*
            1- https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
            yazarak
                “firstname” degeri “Susan” olan rezervasyon oldugunu test edecek bir GET request gonderdigimizde,
            donen response’un
                status code’unun 200 oldugunu
                ve “Susan” ismine sahip 2 booking oldugunu test edin.

         */

        // 1- End-point ve request body hazırla

        specHerokuapp
                .pathParam("pp1","booking")
                .queryParam("firstname","Susan");

        // 2- Expected Data olsuturma
        // 3- Request gonder ve donen response'ı kaydet

        Response response = given()
                                    .when().spec(specHerokuapp)
                                    .get("/{pp1}");

         response.prettyPrint();

        // 4- Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasSize(2));

    }

    @Test
    public void test02(){

        /*
            2- https://restful-booker.herokuapp.com/booking endpointine gerekli Query parametrelerini
            yazarak
                “firstname” degeri “Susan” ve
                “lastname” degeri “Jones” olan
            rezervasyon oldugunu test edecek bir GET request gonderdigimizde, donen response’un
                status code’unun 200 oldugunu
                ve “Susan Ericson” ismine sahip en az bir booking oldugunu test edin.
        */

        // 1- End-point ve request body olustur

        specHerokuapp
                    .pathParam("pp1","booking")
                    .queryParams("firstname","Susan","lastname","Ericsson");

        // 2- Expected Data olustur
        // 3- Request gonder ve donen response'u kaydet

        Response response = given()
                                    .when().spec(specHerokuapp)
                                    .get("/{pp1}");

        // response.prettyPrint();

        // 4- Assertions

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid",Matchers.hasSize(1));
    }
}
