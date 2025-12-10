package com.skillsphere.notificationservice.messaging;

import com.skillsphere.notificationservice.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderEventListener {

    @RabbitListener(queues = RabbitConfig.ORDER_EVENTS_QUEUE)
    public void handleOrderEvent(OrderEvent event) {
        log.info("NotificationService received order event: {}", event);
    }
}

