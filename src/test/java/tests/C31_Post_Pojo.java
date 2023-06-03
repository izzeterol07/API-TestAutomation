package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.pojosHavaDurumu.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class C31_Post_Pojo {

    /*

     https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0
     url’ine bir post request gonderdigimizde
     donen response’un asagidaki body’ye sahip oldugunu test ediniz

                 {
                    "coord": {
                        "lon": -0.1257,
                        "lat": 51.5085
                    },
                    "weather": [
                        {
                            "id": 804,
                            "main": "Clouds",
                            "description": "overcast clouds",
                            "icon": "04d"
                        }
                    ],
                    "base": "stations",
                    "main": {
                        "temp": 291.99,
                        "feels_like": 292.18,
                        "temp_min": 289.89,
                        "temp_max": 293.71,
                        "pressure": 1007,
                        "humidity": 86
                    },
                    "visibility": 8000,
                    "wind": {
                        "speed": 1.54,
                        "deg": 0
                    },
                    "clouds": {
                        "all": 90
                    },
                    "dt": 1627206846,
                    "sys": {
                        "type": 2,
                        "id": 2006068,
                        "country": "GB",
                        "sunrise": 1627186441,
                        "sunset": 1627243183
                    },
                    "timezone": 3600,
                    "id": 2643743,
                    "name": "London",
                    "cod": 200
                }

     */


    @Test
    public void test01() {

        // 1- End-point ve request body olustur
        String url = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0";

        // 2- soruda varsa expected data olsutur

        Coord coordPojo = new Coord(-0.1257f, 51.5085f);

        List<Weather> weatherList = new ArrayList<>();
        Weather weatherPojo = new Weather(803, "Clouds", "broken clouds", "04d");
        weatherList.add(weatherPojo);

        Main mainPojo = new Main(291.99f, 292.18f, 289.89f, 293.71f, 1007, 86);

        Wind windPojo = new Wind(1.54f, 0);

        Clouds cloudsPojo = new Clouds(90);

        Sys sysPojo = new Sys(2, 2006068, "GB", 1627186441, 1627243183);

        PojoHavaDurumu expectedPesponseBodyPojo = new PojoHavaDurumu(coordPojo,
                weatherList,
                "stations",
                mainPojo,
                8000,
                windPojo,
                cloudsPojo,
                1627206846,
                sysPojo,
                3600,
                2643743,
                "London",
                200);


        // 3- Request gonderıp donen Response'ı kaydet

        Response response = given().when().post(url);
        // response.prettyPrint();

        // 4- Assertion
        // expectedResponseBodyPojo Pojo <====>  response Json

        PojoHavaDurumu responsePojo = response.as(PojoHavaDurumu.class);
        // Response'ı pojo'ya cevırdıgımızde tum bılgılerı getırırse
        // responsePojo'yu assertıon'da kullanabılırız.
        // Eger null deger donerse, response'ı JsonPath yapıp assertion'da kullanabılırız.
        // System.out.println(responsePojo);

        JsonPath responseJSONPath = response.jsonPath();
        // expectedResponseBodyPojo Pojo <====>  responseJSONPath jsonPath

        Assert.assertEquals(expectedPesponseBodyPojo.getCoord().getLon(),responseJSONPath.get("coord.lon"));
        Assert.assertEquals(expectedPesponseBodyPojo.getCoord().getLat(),responseJSONPath.get("coord.lat"));
        Assert.assertEquals(expectedPesponseBodyPojo.getBase(),responseJSONPath.get("base"));
        Assert.assertEquals(expectedPesponseBodyPojo.getSys().getCountry(),responseJSONPath.get("sys.country"));
        Assert.assertEquals(expectedPesponseBodyPojo.getName(),responseJSONPath.get("name"));
    }
}
