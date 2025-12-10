package com.skillsphere.notificationservice.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private Long buyerId;
    private Long serviceId;
    private String status;
    private Double price;
    private Instant eventTime;
}

