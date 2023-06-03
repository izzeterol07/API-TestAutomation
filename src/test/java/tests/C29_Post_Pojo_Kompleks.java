package tests;

import baseUrl.BaseUrlHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoHerokuappBookingdates;
import pojos.PojoHerokuappRequestBody;
import pojos.PojoHerokuappResponseBody;

import static io.restassured.RestAssured.given;

public class C29_Post_Pojo_Kompleks extends BaseUrlHerokuapp {

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

        // 1- End-point ve request body olustur
        specHerokuapp.pathParam("pp1","booking");

        PojoHerokuappBookingdates bookingdatesPojo = new PojoHerokuappBookingdates("2021-06-01","2021-06-10");

        PojoHerokuappRequestBody requestBodyPojo = new PojoHerokuappRequestBody("Ahmet","Bulut",500,false,bookingdatesPojo,"wi-fi");

        // 2- Soruda varsa expected data olustur (icten dısa dogru pojo objeleri olusturulur)
        bookingdatesPojo = new PojoHerokuappBookingdates("2021-06-01","2021-06-10");

        PojoHerokuappRequestBody bookingPojo = new PojoHerokuappRequestBody("Ahmet","Bulut",500,false,bookingdatesPojo,"wi-fi");

        PojoHerokuappResponseBody expectedResponseBodyPojo = new PojoHerokuappResponseBody(24,bookingPojo);

        // 3- Request gonderıp donen Response'ı kaydet
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON)
                .when().body(requestBodyPojo)
                .post("/{pp1}");

        // response.prettyPrint();

        PojoHerokuappResponseBody responsePojo = response.as(PojoHerokuappResponseBody.class);
        // System.out.println(responsePojo);

        // 4- Assertion

        Assert.assertEquals(expectedResponseBodyPojo.getBooking().getFirstname(),
                responsePojo.getBooking().getFirstname());

        Assert.assertEquals(expectedResponseBodyPojo.getBooking().getLastname(),
                responsePojo.getBooking().getLastname());

        Assert.assertEquals(expectedResponseBodyPojo.getBooking().getAdditionalneeds(),
                responsePojo.getBooking().getAdditionalneeds());

        Assert.assertEquals(expectedResponseBodyPojo.getBooking().getTotalprice(),
                responsePojo.getBooking().getTotalprice());

        Assert.assertEquals(expectedResponseBodyPojo.getBooking().isDepositpaid(),
                responsePojo.getBooking().isDepositpaid());

        Assert.assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckin(),
                responsePojo.getBooking().getBookingdates().getCheckin());

        Assert.assertEquals(expectedResponseBodyPojo.getBooking().getBookingdates().getCheckout(),
                responsePojo.getBooking().getBookingdates().getCheckout());

    }
}
