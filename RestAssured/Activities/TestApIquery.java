package RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class TestApIquery {
    // Set Base URL
// No need to add questions mark in the URL.
// queryParam() will automatically add it while parsing.
final static String ROOT_URI = "http://ip-api.com/json";

@Test
public void getIPInformation() {
    Response response = 
        given().contentType(ContentType.JSON) // Set headers
        // Add query parameter
        .when().queryParam("fields", "query,country,city,timezone")
        .get(ROOT_URI + "/125.219.5.94"); // Send GET request
    
    // Print response
    System.out.println(response.getBody().asPrettyString());
}
    
}
