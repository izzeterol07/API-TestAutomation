package tests;

import baseUrl.BaseUrlDummyRestApiExample;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testDataları.TestDataDummyRestApiExapmle;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class C26_Get_DeSerialization extends BaseUrlDummyRestApiExample {


    /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un
        status code’unun 200,
        content Type’inin application/json
        ve body’sinin asagidaki gibi oldugunu test edin.


                Expected Response Body
        {
            "status": "success",
            "data": {
                "id": 3,
                "employee_name": "Ashton Cox",
                "employee_salary": 86000,
                "employee_age": 66,
                "profile_image": ""
            },
            "message": "Successfully! Record has been fetched."
        }

     */

    @Test
    public void test01(){


        // 1- End-point ve request body olustur
        specDummyRestApiExample.pathParams("pp1","employee","pp2",3);

        // 2- Soruda varsa Expected data olustur
        Map<String,Object> expectedData = TestDataDummyRestApiExapmle.mapBodyOlustur();

        // 3- Request gonder ve donen Response'ı kaydet
        Response response = given().spec(specDummyRestApiExample)
                                    .when()
                                    .get("/{pp1}/{pp2}");
        // response.prettyPrint();

        Map<String,Object> responseMap = response.as(HashMap.class);

        // 4- Assertions

        Assert.assertEquals(TestDataDummyRestApiExapmle.basariliSorguStatusCode,response.statusCode());
        Assert.assertEquals(TestDataDummyRestApiExapmle.contentType,response.contentType());
        Assert.assertEquals(expectedData.get("message"),responseMap.get("message"));
        Assert.assertEquals(expectedData.get("status"),responseMap.get("status"));

        Assert.assertEquals(((Map)expectedData.get("data")).get("profile_image"),
                            ((Map)(responseMap.get("data"))).get("profile_image"));

        Assert.assertEquals(((Map)expectedData.get("data")).get("id"),
                            ((Map)(responseMap.get("data"))).get("id"));

        Assert.assertEquals(((Map)expectedData.get("data")).get("employee_name"),
                            ((Map)(responseMap.get("data"))).get("employee_name"));

        Assert.assertEquals(((Map)expectedData.get("data")).get("employee_salary"),
                            ((Map)(responseMap.get("data"))).get("employee_salary"));

        Assert.assertEquals(((Map)expectedData.get("data")).get("employee_age"),
                            ((Map)(responseMap.get("data"))).get("employee_age"));


    }
}
