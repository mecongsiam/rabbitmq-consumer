package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyPictureConsumer {

    private final Logger logger = LoggerFactory.getLogger(MyPictureConsumer.class);

    private final ObjectMapper objectMapper;

    public MyPictureConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.mypicture.image")
    public void getMessage(String message) throws JsonProcessingException {

        Picture picture = objectMapper.readValue(message, Picture.class);

        logger.info("Image picture consumed: {}", picture);

        if (picture.getSize() > 9000L) {
            throw new AmqpRejectAndDontRequeueException("Size more then 9000");
        }

    }
}
