package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.InvoiceCreatedMessage;
import com.course.rabbitmqconsumer.entity.InvoicePaidMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "q.invoice")
public class InvoiceConsumer {

	private static final Logger log = LoggerFactory.getLogger(InvoiceConsumer.class);

	@RabbitHandler
	public void handleInvoiceCreated(InvoiceCreatedMessage message) {
		log.info("on handleInvoiceCreated : {}", message);
	}

	@RabbitHandler
	public void handleInvoicePaid(InvoicePaidMessage message) {
		log.info("on handleInvoicePaid : {}", message);
	}

	@RabbitHandler(isDefault = true)
	public void handleDefault(Object message) {
		log.info("on handleDefault : {}", message);
	}

}
