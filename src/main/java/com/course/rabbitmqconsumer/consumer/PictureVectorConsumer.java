package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PictureVectorConsumer {

    private final Logger logger = LoggerFactory.getLogger(PictureVectorConsumer.class);

    private final ObjectMapper objectMapper;

    public PictureVectorConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.picture.vectors")
    public void getMessage(String message) throws JsonProcessingException {

        Picture picture = objectMapper.readValue(message, Picture.class);
        logger.info("Vector picture consumed: {}", picture);

    }
}
