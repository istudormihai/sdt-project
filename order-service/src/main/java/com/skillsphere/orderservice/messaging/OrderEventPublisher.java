package com.skillsphere.orderservice.messaging;

import com.skillsphere.orderservice.config.RabbitConfig;
import com.skillsphere.orderservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void publishOrderEvent(Order order) {
        OrderEvent event = new OrderEvent(
                order.getId(),
                order.getBuyerId(),
                order.getServiceId(),
                order.getStatus(),
                order.getPrice(),
                Instant.now()
        );

        rabbitTemplate.convertAndSend(
                RabbitConfig.ORDER_EVENTS_EXCHANGE,
                RabbitConfig.ORDER_EVENTS_ROUTING_KEY,
                event
        );
    }
}

