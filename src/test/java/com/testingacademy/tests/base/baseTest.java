package com.testingacademy.tests.base;

import com.testingacademy.actions.AssetActions;
import com.testingacademy.endpoints.APIConstant;
import com.testingacademy.module.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class baseTest {

    public RequestSpecification request;
    public AssetActions assetActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse  validatableResponse;

    @BeforeMethod
    public void setUpConfig() {

        payloadManager = new PayloadManager();
        assetActions = new AssetActions();
        request = new RequestSpecBuilder().setBaseUri(APIConstant.BASE_URL)
                      .addHeader("Content-Type", "application/json").build().log().all();
    }

    public String getToken() {

        request = RestAssured.given().baseUri(APIConstant.BASE_URL).basePath("/auth");

        String Payload = "{\n" +
                "  \"username\" :  \"admin\",\n" +
                "  \"password\" : \"password123\"\n" +
                "}";

        response = request.contentType(ContentType.JSON)
                .body(Payload).when().post();

        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("End");
    }
}