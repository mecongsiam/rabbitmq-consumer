package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.Employee;
import com.course.rabbitmqconsumer.rabbitmq.DlxFanoutProcessingErrorHandler;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RetryAccountingConsumer {

	private static final String DEAD_EXCHANGE_NAME = "x.guideline2.dead";
	private static final String ROUTING_KEY = "accounting";

	private static final Logger log = LoggerFactory.getLogger(RetryAccountingConsumer.class);
	private DlxFanoutProcessingErrorHandler dlxFanoutProcessingErrorHandler;

	private ObjectMapper objectMapper;

	public RetryAccountingConsumer() {
		this.objectMapper = new ObjectMapper();
		this.dlxFanoutProcessingErrorHandler = new DlxFanoutProcessingErrorHandler(DEAD_EXCHANGE_NAME, ROUTING_KEY);
	}

	@RabbitListener(queues = "q.guideline2.accounting.work")
	public void listen(Message message, Channel channel)
			throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		try {
			var emp = objectMapper.readValue(message.getBody(), Employee.class);

			if (StringUtils.isEmpty(emp.getName())) {
				throw new IllegalArgumentException("Name is empty");
			} else {
				log.info("On accounting : {}", emp);
				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			}
		} catch (Exception e) {
			log.warn("Error processing message : {} : {}", new String(message.getBody()), e.getMessage());
			dlxFanoutProcessingErrorHandler.handleErrorProcessingMessage(message, channel);
		}

	}
}