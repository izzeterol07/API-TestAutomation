package tests;

import baseUrl.BaseUrlDummyRestApiExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testDataları.TestDataDummyRestApiExapmle;
import testDataları.TestDataJsonPlaceholder;

import static io.restassured.RestAssured.given;

public class C23_Get_KompleksTestDataKullanimiDinamik extends BaseUrlDummyRestApiExample {

    /*

        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request gonderdigimizde
        donen response’un

            status code’unun 200,
            content Type’inin application/json
            ve body’sinin asagidaki gibi oldugunu test edin.

        Response Body (Expected Body) :
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

        // 2- Expected Body olustur

        JSONObject expectedData = TestDataDummyRestApiExapmle
                .jsonResponseBodyOlustur(3,"Ashton Cox",86000,66,"");

        // System.out.println(expectedData);

        // 3- Request gonder donen response'ı kaydet
        Response response = given().spec(specDummyRestApiExample)
                .when()
                .get("/{pp1}/{pp2}");

        // response.prettyPrint();

        // 4- Assertions
        // Expected Data : JSONObject
        // response : JsonPath

        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals(TestDataDummyRestApiExapmle.basariliSorguStatusCode,response.statusCode());
        Assert.assertEquals(TestDataDummyRestApiExapmle.contentType,response.contentType());
        Assert.assertEquals(expectedData.getJSONObject("data").getString("profile_image"),responseJsonPath.getString("data.profile_image"));
        Assert.assertEquals(expectedData.getJSONObject("data").getString("employee_name"),responseJsonPath.getString("data.employee_name"));
        Assert.assertEquals(expectedData.getJSONObject("data").getInt("employee_salary"),responseJsonPath.getInt("data.employee_salary"));
        Assert.assertEquals(expectedData.getJSONObject("data").getInt("employee_age"),responseJsonPath.getInt("data.employee_age"));
        Assert.assertEquals(expectedData.getJSONObject("data").getInt("id"),responseJsonPath.getInt("data.id"));
        Assert.assertEquals(expectedData.getString("status"),responseJsonPath.getString("status"));
        Assert.assertEquals(expectedData.getString("message"),responseJsonPath.getString("message"));

    }
}
