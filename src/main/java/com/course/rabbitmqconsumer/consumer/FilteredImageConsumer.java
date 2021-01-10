package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FilteredImageConsumer {

    private final Logger logger = LoggerFactory.getLogger(FilteredImageConsumer.class);

    private final ObjectMapper objectMapper;

    public FilteredImageConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.picture.filter")
    public void getMessage(String message) throws JsonProcessingException {

        Picture picture = objectMapper.readValue(message, Picture.class);
        logger.info("Mobile picture consumed: {}", picture);

    }
}
