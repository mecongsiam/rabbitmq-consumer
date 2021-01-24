package com.course.rabbitmqconsumer.consumer;

import com.course.rabbitmqconsumer.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AnotherDummyConsumer {

	private static final Logger log = LoggerFactory.getLogger(AnotherDummyConsumer.class);

	@RabbitListener(bindings = @QueueBinding(
			value = @Queue(name = "q.auto-dummy", durable = "true"), 
			exchange = @Exchange(name = "x.auto-dummy", type = ExchangeTypes.DIRECT, durable = "true"), 
			key = "routing-key", 
			ignoreDeclarationExceptions = "true"))
	public void listenDummy(DummyMessage message) {
		log.info("{}", message);
	}

}
