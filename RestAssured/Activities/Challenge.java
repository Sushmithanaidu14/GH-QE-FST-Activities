package RestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Challenge {

    @Test
    public void petFlowTest() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        // POST - Create Pet (NO ID)    
        System.out.println("POST: Creating pet...");

        String requestBody = "{\n" +
                "  \"name\": \"Fluffy\",\n" +
                "  \"photoUrls\": [\"string\"],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Response postResponse = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/pet");

        // Extract ID using long
        long petId = postResponse.jsonPath().getLong("id");

        System.out.println("POST Response: " + postResponse.asString());
        // GET - Fetch Pet
        System.out.println("\nGET: Fetching pet details...");

        Response getResponse1 = given()
                .get("/pet/" + petId);

        System.out.println("GET Response: " + getResponse1.asString());
        // PUT - Update Status
        System.out.println("\nPUT: Updating pet status...");

        String updateBody = "{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"name\": \"Fluffy\",\n" +
                "  \"photoUrls\": [\"string\"],\n" +
                "  \"status\": \"sold\"\n" +
                "}";

        Response putResponse = given()
                .header("Content-Type", "application/json")
                .body(updateBody)
                .put("/pet");

        System.out.println("PUT Response: " + putResponse.asString());
        // GET - Verify Update
        System.out.println("\nGET: Verifying updated status...");

        Response getResponse2 = given()
                .get("/pet/" + petId);

        System.out.println("Updated GET Response: " + getResponse2.asString());

        // DELETE - Delete Pet

        System.out.println("\nDELETE: Deleting pet...");

        Response deleteResponse = given()
                .delete("/pet/" + petId);

        System.out.println("DELETE Status Code: " + deleteResponse.getStatusCode());

        // GET - Verify Deletion

        System.out.println("\nGET: Verifying pet deletion...");

        Response getResponse3 = given()
                .get("/pet/" + petId);

        int finalStatus = getResponse3.getStatusCode();

        System.out.println("Final GET Status Code: " + finalStatus);

        if (finalStatus == 404) {
            System.out.println("Pet not found. Deletion successful!");
        } else {
            System.out.println("Pet still exists: " + getResponse3.asString());
        }

        //PRINT REFERENCE ID AT END
 
        System.out.println("\nFINAL REFERENCE PET ID: " + petId);
    }
}