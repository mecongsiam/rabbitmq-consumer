package com.course.rabbitmqconsumer.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HelloRabbitConsumer {

    @RabbitListener(queues = "course.hello", concurrency = "3")
    private void readMessage(String message) {
        System.out.println("Hello " + message);
    }

}
