package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Picture;
import com.course.rabbitmqconsumer.rabbitmq.DlxProcessingErrorHandler;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RetryImageConsumer {

	private static final String DEAD_EXCHANGE_NAME = "x.guideline.dead";

	private static final Logger log = LoggerFactory.getLogger(RetryImageConsumer.class);
	private DlxProcessingErrorHandler dlxProcessingErrorHandler;

	private ObjectMapper objectMapper;

	public RetryImageConsumer() {
		this.objectMapper = new ObjectMapper();
		this.dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
	}

	@RabbitListener(queues = "q.guideline.image.work")
	public void listen(Message message, Channel channel)
			throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		try {
			var p = objectMapper.readValue(message.getBody(), Picture.class);
			// process the image
			if (p.getSize() > 9000) {
				// throw exception, we will use DLX handler for retry mechanism
				throw new IOException("Size too large");
			} else {
				log.info("Creating thumbnail & publishing : " + p);
				// you must acknowledge that message already processed
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}
		} catch (IOException e) {
			log.warn("Error processing message : " + new String(message.getBody()) + " : " + e.getMessage());
			dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel);
		}
	}

}
