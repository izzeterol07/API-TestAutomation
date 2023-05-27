package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C15_Get_JsonPathVeTestNGSoftAssertionIleExpectedDataTesti {


    @Test
    public void test01() {

        /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

                Response Body
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

        // 1- End-point ve request body olustur.
        String url = "http://dummy.restapiexample.com/api/v1/employee/3";

        // 2- Expected Data olustur

        JSONObject expectedData = new JSONObject();

        JSONObject employeeDataJsonObj = new JSONObject();

        employeeDataJsonObj.put("id", 3);
        employeeDataJsonObj.put("employee_name", "Ashton Cox");
        employeeDataJsonObj.put("employee_salary", 86000);
        employeeDataJsonObj.put("employee_age", 66);
        employeeDataJsonObj.put("profile_image", "");

        expectedData.put("status", "success");
        expectedData.put("data", employeeDataJsonObj);
        expectedData.put("message", "Successfully! Record has been fetched.");

        // System.out.println(expectedData.toString());

        // 3- Request gonderip, response'u kaydet

        Response response = given().when().get(url);

        // response.prettyPrint();

        // 4- Assertion

        // Oncelıkle response uzerındekı bılgılerı kolay almak ıcın JsonPath'e cast edelim.
        JsonPath responseJsonPath = response.jsonPath();

        // Assertion'ları soft assert ıle yapalım.
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(responseJsonPath.get("status"),expectedData.get("status"));
        softAssert.assertEquals(responseJsonPath.get("data.id"),
                                expectedData.getJSONObject("data").get("id"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_name"),
                                expectedData.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_salary"),
                                expectedData.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(responseJsonPath.get("data.employee_age"),
                                expectedData.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(responseJsonPath.get("data.profile_image"),
                                expectedData.getJSONObject("data").get("profile_image"));
        softAssert.assertEquals(responseJsonPath.get("message"),expectedData.get("message"));

        softAssert.assertAll();


    }

}
