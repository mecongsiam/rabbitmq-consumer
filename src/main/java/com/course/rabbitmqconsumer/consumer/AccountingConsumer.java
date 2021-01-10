package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AccountingConsumer {

    Logger logger = LoggerFactory.getLogger(AccountingConsumer.class);

    private final ObjectMapper objectMapper;

    public AccountingConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.hr.accounting")
    public void readMessage(String message) throws JsonProcessingException {

        Employee employee = objectMapper.readValue(message, Employee.class);
        logger.info("Accounting consumer. Employee : {}", employee);
    }
}
