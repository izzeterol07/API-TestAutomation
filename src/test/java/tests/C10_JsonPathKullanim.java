package tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C10_JsonPathKullanim {

    @Test
    public void method01() {

        // Once butun JSON objelerını olustururuz. String, int, Array vs..

        JSONObject kisiBilgileriJsonObj = new JSONObject();

        JSONObject adresJsonObj = new JSONObject();

        JSONArray telefonBilgileriArr = new JSONArray();
        JSONObject cepTelJsonObj = new JSONObject();
        JSONObject evTelJsonObj = new JSONObject();

        // Adres bılgılerının oldugu JSONObject'lerin key value bılgılerını gırerız.
        adresJsonObj.put("streetAddress", "naist street");
        adresJsonObj.put("city", "Nara");
        adresJsonObj.put("postalCode", "630-0192");

        // Telefon bılgılerınını oldugu JSONArray objesının ıcındekı strıng JSONObject'lerin key-value bılgılerını gırerız.
        cepTelJsonObj.put("type", "iPhone");
        cepTelJsonObj.put("number", "0123-4567-8888");
        evTelJsonObj.put("type", "home");
        evTelJsonObj.put("number", "0123-4567-8910");

        // JSONArray'e de ıkı ayrı telefon bılgısı olan JSONObject'lerini ekleriz.
        telefonBilgileriArr.put(cepTelJsonObj);
        telefonBilgileriArr.put(evTelJsonObj);

        // Kisi bilgilerini toplu olarak ıceren JSONObject'e butun kısı bılgilerinin key-value bılgılerını gırerız.
        kisiBilgileriJsonObj.put("firstName", "John");
        kisiBilgileriJsonObj.put("lastName", "Doe");
        kisiBilgileriJsonObj.put("age", 26);
        kisiBilgileriJsonObj.put("address", adresJsonObj);
        kisiBilgileriJsonObj.put("phoneNumbers", telefonBilgileriArr);

        // Butun kısı bılgılerını JSONObject'ı lıneer olarak cıktı alırız.
        System.out.println(kisiBilgileriJsonObj);

        // Eger her key-value bılgısıne ozel olarak erısmek ıstersek ;
        System.out.println("firstName : " + kisiBilgileriJsonObj.get("firstName"));
        System.out.println("lastName : " + kisiBilgileriJsonObj.get("lastName"));

        // Once KisiBilgileri JsonObje'sınden getJSONObject methodu ıle address JsonObje'sı getırılır,
        // sonra get methodu ıle address JsonObje'sınden ılgılı key ıle value degerı getırılır.
        System.out.println("streetAddress : " + kisiBilgileriJsonObj.getJSONObject("address").get("streetAddress"));
        System.out.println("city : " + kisiBilgileriJsonObj.getJSONObject("address").get("city"));

        // KısıBılgılerınden array'ı getırdık sonrasında array ındex'ı ıle array ıcındekı json objesıne erıstık
        // ve sonrasında get methodu ıle key bılgısını gırdık ve ılgılı value ulasmıs olduk.
        System.out.println("iPhone : " + kisiBilgileriJsonObj.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));


    }
}
