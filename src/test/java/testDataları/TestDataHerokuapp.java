package testDataları;

import org.json.JSONObject;

public class TestDataHerokuapp {

    public static JSONObject jsonRequestBodyOlustur() {

        JSONObject requestBody = new JSONObject();
        JSONObject rezervasyonBilgileri = new JSONObject();

        rezervasyonBilgileri.put("checkin", "2021-06-01");
        rezervasyonBilgileri.put("checkout", "2021-06-10");

        requestBody.put("firstname", "Ahmet");
        requestBody.put("lastname", "Bulut");
        requestBody.put("totalprice", 500);
        requestBody.put("depositpaid", false);
        requestBody.put("bookingdates", rezervasyonBilgileri);
        requestBody.put("additionalneeds", "wi-fi");

        return requestBody;
    }

    public static JSONObject jsonResponseBodyOlustur(){

        JSONObject responseBody = new JSONObject();
        JSONObject bookingBody = jsonRequestBodyOlustur();

        responseBody.put("bookingid",24);
        responseBody.put("booking",bookingBody);

        return responseBody;
    }

}
