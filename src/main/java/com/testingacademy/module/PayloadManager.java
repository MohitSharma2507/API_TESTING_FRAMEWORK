package com.testingacademy.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testingacademy.payload.Booking;
import com.testingacademy.payload.Bookingdates;

public class PayloadManager {

//    Java -> Json
//    Create a payload

    ObjectMapper objectMapper;

    public String createPayload() throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("MOHIT");
        booking.setLastname("SHARMA");
        booking.setDepositpaid(true);
        booking.setTotalprice(111);
        booking.setAdditionalneeds("Breakfast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("21-12-2024");
        bookingdates.setCheckout("30-12-2024");
        booking.setBookingdates(bookingdates);

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);

        return payload;

    }
    public String updatePayload() throws JsonProcessingException {

        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname("ROHIT");
        booking.setLastname("SHARMA");
        booking.setDepositpaid(true);
        booking.setTotalprice(111);
        booking.setAdditionalneeds("Breakfast");

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("21-12-2024");
        bookingdates.setCheckout("30-12-2024");
        booking.setBookingdates(bookingdates);

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);

        return payload;
    }
}
