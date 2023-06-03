package tests;

import baseUrl.BaseUrlDummyRestApiExample;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.PojoDummyExampleData;
import pojos.PojoDummyExampleResponse;

import static io.restassured.RestAssured.given;

public class C30_Get_Pojo extends BaseUrlDummyRestApiExample {

    /*

        http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
        gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.

        Response Body (Expected Data)

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

        PojoDummyExampleData dataPojo =
                new PojoDummyExampleData(3,"Ashton Cox",86000,66,"");

        PojoDummyExampleResponse expectedResponseBody =
                new PojoDummyExampleResponse("success",dataPojo,"Successfully! Record has been fetched.");

        // 3- Request gonder ve donen Response'ı kaydet
        Response response = given().spec(specDummyRestApiExample)
                .when()
                .get("/{pp1}/{pp2}");


        // 4- Assertion
        // Hazır cevırıcıler attrıbute ısımlerını degıstırdıgınden, response'ı pojo'ya convert edemedık
        // Bu durumda testımıze devam etmemız ıcın Response'ı JsonPath'e cevırebılırız.

        JsonPath responseJsonPath = response.jsonPath();

        Assert.assertEquals(expectedResponseBody.getMessage(),responseJsonPath.getString("message"));
        Assert.assertEquals(expectedResponseBody.getStatus(),responseJsonPath.getString("status"));
        Assert.assertEquals(expectedResponseBody.getData().getId(),responseJsonPath.get("data.id"));
        Assert.assertEquals(expectedResponseBody.getData().getEmployeeName(),responseJsonPath.get("data.employee_name"));
        Assert.assertEquals(expectedResponseBody.getData().getEmployeeAge(),responseJsonPath.get("data.employee_age"));
        Assert.assertEquals(expectedResponseBody.getData().getEmployeeSalary(),responseJsonPath.get("data.employee_salary"));
        Assert.assertEquals(expectedResponseBody.getData().getProfileImage(),responseJsonPath.get("data.profile_image"));
    }
}
