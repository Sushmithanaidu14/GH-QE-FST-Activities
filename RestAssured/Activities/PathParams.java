package RestAssured;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PathParams {

    // Set Base URL with path parameter
    String ROOT_URI = "http://ip-api.com/json/{ipAddress}";

    @Test
    public void getIPInformation() {

        Response response =
                given().contentType(ContentType.JSON)
                .when()
                .pathParam("ipAddress", "107.218.134.107")
                .get(ROOT_URI);

        // Print response
        System.out.println(response.getBody().asPrettyString());
    }
}