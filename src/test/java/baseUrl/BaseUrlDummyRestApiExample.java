package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class BaseUrlDummyRestApiExample {

    protected RequestSpecification specDummyRestApiExample;

    @Before
    public void setup(){

        specDummyRestApiExample = new RequestSpecBuilder()
                                                        .setBaseUri("http://dummy.restapiexample.com/api/v1")
                                                        .build();

    }
}
