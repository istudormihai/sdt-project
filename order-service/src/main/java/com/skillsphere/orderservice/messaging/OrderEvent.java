package com.skillsphere.orderservice.messaging;

import com.skillsphere.orderservice.entity.OrderStatus;
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
    private OrderStatus status;
    private Double price;
    private Instant eventTime;
}

