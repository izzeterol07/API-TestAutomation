package tests;

import baseUrl.BaseUrlJsonPlaceHolder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C17_BaseUrlJsonPlaceHolder extends BaseUrlJsonPlaceHolder {


    // Class icinde 3 Test metodu olusturun ve asagidaki testleri yapin

    @Test
    public void test01(){

        /*
            1- https://jsonplaceholder.typicode.com/posts endpointine bir GET request gonderdigimizde
            donen response’un status code’unun 200 oldugunu ve Response’ta 100 kayit oldugunu test
            edin

         */


        // 1- End-point ve request body olustur.
        specJsonPlaceHolder.pathParam("pp1","posts");       // BaseUrl'e verıable'ı ıle param degerını ekledık (key-value)

        // 2- Expected Data olustur.
        // 3- Request gonder ve donen response'ı kaydet.

        Response response = given()
                                .when().spec(specJsonPlaceHolder) // spec'ı yanı baseUrl'ı belırttık
                                .get("/{pp1}");                //  url'dekı parametreyı verıable olarak methoda gonderdık

        // 4- Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title", Matchers.hasSize(100));

    }

    @Test
    public void test02(){

        /*
            2- https://jsonplaceholder.typicode.com/posts/44 endpointine bir GET request gonderdigimizde
            donen response’un status code’unun 200 oldugunu ve “title” degerinin
            “optio dolor molestias sit” oldugunu test edin

         */

        // 1- End-point ve request body olustur
        specJsonPlaceHolder.pathParams("pp1","posts","pp2",44);

        // 2- Expected Body olustur
        // 3- Request gonder ve donen Response'u kaydet

        Response response = given()
                                .when().spec(specJsonPlaceHolder)
                                .get("/{pp1}/{pp2}");
        // response.prettyPrint();

        // 4- Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title",Matchers.equalTo("optio dolor molestias sit"));

    }

    @Test
    public void test03(){

        /*
            3- https://jsonplaceholder.typicode.com/posts/50 end-point'ine bir DELETE request
            gonderdigimizde donen response’un status code’unun 200 oldugunu ve response body’sinin
            null oldugunu test edin

         */

        // 1- End-point ve request body olustur
        specJsonPlaceHolder.pathParams("pp1","posts","pp2",50);

        // 2- Expected Data olustur
        // 3- Request gonder ve Response'u kaydet

        Response response = given()
                                    .when().spec(specJsonPlaceHolder)
                                    .delete("/{pp1}/{pp2}");
        // response.prettyPrint();

        // 4- Assertion
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("title",Matchers.nullValue());
    }

}
