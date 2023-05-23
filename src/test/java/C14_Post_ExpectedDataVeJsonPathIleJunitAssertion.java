import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C14_Post_ExpectedDataVeJsonPathIleJunitAssertion {


    @Test
    public void test01() {

        /*

        https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST request
        gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body

        {
            "firstname" : "Hasan",
            "lastname" : “Yagmur",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2021-06-01",
                "checkout" : "2021-06-10"
                },
            "additionalneeds" : "wi-fi"
        }


        Expected Response Body
        {
            "bookingid": 24,
            "booking": {
                "firstname": "Hasan",
                "lastname": "Yagmur",
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

        // 1- End-point ve request body olusturma
        String url = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();

        JSONObject rezervasyonTarihleriJson = new JSONObject();
        rezervasyonTarihleriJson.put("checkin", "2021-06-01");
        rezervasyonTarihleriJson.put("checkout", "2021-06-10");

        requestBody.put("firstname", "Hasan");
        requestBody.put("lastname", "Yagmur");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezervasyonTarihleriJson);
        requestBody.put("additionalneeds", "wi-fi");

        // System.out.println(requestBody.toString());

        // 2- Expected Body olusturma

        JSONObject expectedData = new JSONObject();

        expectedData.put("bookingid", 24);
        expectedData.put("booking", requestBody);

        // System.out.println(expectedData.toString());


        // 3- Request gonderıp donen Response'u kaydet

        Response response = given().contentType(ContentType.JSON)
                .when().body(requestBody.toString())
                .post(url);

        // response.prettyPrint();

        // 4- Assertion

        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.getJSONObject("booking").get("firstname"),
                            responseJsonPath.get("booking.firstname"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("lastname"),
                            responseJsonPath.get("booking.lastname"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("totalprice"),
                            responseJsonPath.get("booking.totalprice"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("depositpaid"),
                            responseJsonPath.get("booking.depositpaid"));
        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),
                            responseJsonPath.get("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedData.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),
                            responseJsonPath.get("booking.bookingdates.checkout"));
        Assert.assertEquals(expectedData.getJSONObject("booking").get("additionalneeds"),
                            responseJsonPath.get("booking.additionalneeds"));

    }
}
