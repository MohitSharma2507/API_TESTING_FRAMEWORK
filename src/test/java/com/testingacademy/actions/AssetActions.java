package com.testingacademy.actions;
import io.restassured.response.Response;
import static org.testng.AssertJUnit.assertEquals;

public class AssetActions {

    public void verifyStatusCode(Response response) {
        assertEquals("Value of status code is " + response.getStatusCode(),
                String.valueOf(response.getStatusCode()).startsWith("20"));

    }
    public <T> void verifyResponseBody(T actual, T expected, String description) {
        assertEquals(description, expected, actual);
    }



}
