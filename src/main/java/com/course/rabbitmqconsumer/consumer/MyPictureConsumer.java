package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyPictureConsumer {

    private final Logger logger = LoggerFactory.getLogger(MyPictureConsumer.class);

    private final ObjectMapper objectMapper;

    public MyPictureConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "q.mypicture.image")
    public void getMessage(Message message, Channel channel) throws IOException {

        Picture picture = objectMapper.readValue(message.getBody(), Picture.class);

        logger.info("Image picture consumed: {}", picture);

        if (picture.getSize() > 9000L) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }
}
