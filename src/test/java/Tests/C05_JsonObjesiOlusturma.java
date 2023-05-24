package Tests;

import org.json.JSONObject;
import org.junit.Test;

public class C05_JsonObjesiOlusturma {

    @Test
    public void test01(){


        /*

            Asagidaki JSON Objesini olusturup konsolda
            yazdirin.

            {
            "firstname":"Jim",
            "additionalneeds":"Breakfast",
            "bookingdates":{
                    "checkin":"2018-01-01",
                    "checkout":"2019-01-01"
                },
            "totalprice":111,
            "depositpaid":true,
            "lastname":"Brown"
            }

         */

        // Once inner json objesini olustururuz

        JSONObject dateJsonObject = new JSONObject();
        dateJsonObject.put("checkin","2018-01-01");
        dateJsonObject.put("checkout","2019-01-01");

        // Sonra outer json objesini olusturup, yeri geldiginde inner objeyi koyalım

        JSONObject requestBody = new JSONObject();

        requestBody.put("firstname","Jim");
        requestBody.put("additionalneeds","Breakfast");
        requestBody.put("bookingdates",dateJsonObject);
        requestBody.put("totalprice",111);
        requestBody.put("depositpaid",true);
        requestBody.put("lastname","Brown");

        System.out.println(requestBody);

        /*
        {
        "firstname":"Jim",
        "additionalneeds":"Breakfast",
        "bookingdates":{"checkin":"2018-01-01","checkout":"2019-01-01"},
        "totalprice":111,
        "depositpaid":true,
        "lastname":"Brown"
        }

         */
    }
}
