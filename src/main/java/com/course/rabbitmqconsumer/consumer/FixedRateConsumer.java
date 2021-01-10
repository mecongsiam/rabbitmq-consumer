package com.course.rabbitmqconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class FixedRateConsumer {

    private final Logger logger = LoggerFactory.getLogger(FixedRateConsumer.class);

    @RabbitListener(queues = "course.fixedrate", concurrency = "5")
    public void consumeMessage(String message) throws InterruptedException {

        logger.info("Message : {} , Thread: {}", message, Thread.currentThread().getName());

        Thread.sleep(ThreadLocalRandom.current().nextInt(2000));

    }
}
