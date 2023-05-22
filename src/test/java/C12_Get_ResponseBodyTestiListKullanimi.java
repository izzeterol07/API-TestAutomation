import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C12_Get_ResponseBodyTestiListKullanimi {

    @Test
    public void test01(){

        /*

        http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request yolladigimizda
        donen Response'in

            status code'unun 200,
            ve content type'inin Aplication.JSON,
            ve response body'sindeki
            employees sayisinin 24
            ve employee'lerden birinin "Ashton Cox"
            ve girilen yaslar icinde 61,21 ve 35 degerinin oldugunu
        test edin.

         */

        // 1- end-point ve request body hazırla
        String url ="http://dummy.restapiexample.com/api/v1/employees";

        // 2- expected body olustur
        // 3- Request gonder ver response'u kaydet
        Response response = given().when().get(url);

        response.prettyPrint();

        // 4- Assertion


    }
}
