package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MarketingConsumer {

    Logger logger = LoggerFactory.getLogger(MarketingConsumer.class);

    private final ObjectMapper objectMapper;

    public MarketingConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.hr.marketing")
    public void readMessage(String message) throws JsonProcessingException {

        Employee employee = objectMapper.readValue(message, Employee.class);
        logger.info("Marketing consumer. Employee : {}", employee);
    }
}
