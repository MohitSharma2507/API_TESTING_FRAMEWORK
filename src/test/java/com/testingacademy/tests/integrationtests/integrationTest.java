package com.testingacademy.tests.integrationtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testingacademy.endpoints.APIConstant;
import com.testingacademy.module.PayloadManager;
import com.testingacademy.tests.base.baseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class integrationTest extends baseTest {

    String token;

    @Test
    @Owner("Mohit Sharma")
    @Description("Verify the Booking is Created or not")
    public void testCreateBooking(ITestContext iTestContext) throws JsonProcessingException {
      request.basePath(APIConstant.CREATE_UPDATE_BOOKING_URL);
      response = RestAssured.given().spec(request)
                 .body(payloadManager.createPayload()).when().post();

      validatableResponse = response.then().log().all();
      validatableResponse.statusCode(200);

      jsonPath =  jsonPath.from(response.asString());
      String Booking_id = jsonPath.getString("bookingid");
      System.out.println("Booking_id = " + Booking_id);

      iTestContext.setAttribute("Booking_id", Booking_id);
    }

    @Test(dependsOnMethods = "testCreateBooking" )
    @Owner("Mohit Sharma")
    @Description("Verify full update of previous Booking working")
    public void testCreateAndUpdateToken(ITestContext iTestContext) throws JsonProcessingException {

        token = getToken();

        String booking_id = iTestContext.getAttribute("Booking_id").toString();
        request.basePath(APIConstant.CREATE_UPDATE_BOOKING_URL +"/"+ booking_id);
        response = RestAssured.given().spec(request)
                .headers("Cookie","token = "+ token)
                .body(payloadManager.updatePayload()).put();

        validatableResponse = response.then().log().all();
        validatableResponse.body("firstname", Matchers.is("ROHIT"));
    }

    @Test(dependsOnMethods = "testCreateAndUpdateToken")
    @Owner("Mohit Sharma")
    @Description("Verify previous Booking is deleted")
    public void testDeleteCreatedBooking(ITestContext iTestContext) throws JsonProcessingException {

        String booking_id = iTestContext.getAttribute("Booking_id").toString();

        request.basePath(APIConstant.CREATE_UPDATE_BOOKING_URL +"/"+ booking_id);
        response = RestAssured.given().spec(request)
                .contentType(ContentType.JSON)
                .auth().preemptive().basic("admin","password123").delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }
}
