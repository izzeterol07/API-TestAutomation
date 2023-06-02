package testDataları;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    public static JSONObject jsonResponseBodyOlustur() {

        JSONObject responseBody = new JSONObject();
        JSONObject bookingBody = jsonRequestBodyOlustur();

        responseBody.put("bookingid", 24);
        responseBody.put("booking", bookingBody);

        return responseBody;
    }

    public static Map<String, Object> requestBodyMapOlustur() {

        Map<String, Object> requestBodyMap = new HashMap<>();

        requestBodyMap.put("firstname","Ahmet");
        requestBodyMap.put("lastname","Bulut");
        requestBodyMap.put("totalprice",500.0);
        requestBodyMap.put("depositpaid",false);
        requestBodyMap.put("bookingdates", bookingdatesMapOlustur());
        requestBodyMap.put("additionalneeds","wi-fi");

        return requestBodyMap;
    }

    public static Map<String,String> bookingdatesMapOlustur(){
        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","2021-06-01");
        bookingdatesMap.put("checkout","2021-06-10");

        return bookingdatesMap;
    }

    public static Map<String,Object> responseBodyMapOlustur(){

        Map<String,Object> responseBodyMap = new HashMap<>();
        Map<String,Object> bookingBody = requestBodyMapOlustur();

        responseBodyMap.put("bookingid",24);
        responseBodyMap.put("booking",bookingBody);

        return responseBodyMap;
    }

}
