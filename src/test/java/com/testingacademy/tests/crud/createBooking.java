package com.testingacademy.tests.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.testingacademy.endpoints.APIConstant;
import com.testingacademy.tests.base.baseTest;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class createBooking extends baseTest {

      @Owner("Mohit Sharma")
      @Description("Verfying that the Create Booking is working fine")
      @Severity(SeverityLevel.CRITICAL)
      @Test(groups ={ "sanity","p0"})

    public void testcreateBooking () throws JsonProcessingException {

       // Step-1 -Make a Req with the Payload
       request.basePath(APIConstant.CREATE_UPDATE_BOOKING_URL);
       response = RestAssured.given().spec(request)
                  .body(payloadManager.createPayload()).when().post();

       validatableResponse =  response.then().log().all();
       validatableResponse.statusCode(200);

        //Step -2 - Validate the response
        jsonPath = jsonPath.from(response.asString());
        System.out.println(jsonPath.getString("bookingid"));

    }
}