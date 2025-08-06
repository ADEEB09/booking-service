package com.booking.booking_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateBookingRequest {
    private String name;
    private String email;
    private String serviceType;
    private LocalDateTime bookingTime;
}
