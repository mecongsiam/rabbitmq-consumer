package com.course.rabbitmqconsumer.config;

import com.course.rabbitmqconsumer.entity.Employee;
import com.course.rabbitmqconsumer.entity.Picture;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class RabbitmqConfig {

	@Bean(name = "rabbitListenerContainerFactory")
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setPrefetchCount(1);
		factory.setAfterReceivePostProcessors(message -> {
			var type = message.getMessageProperties().getHeaders().get("type").toString();
			String typeId = null;

			if (StringUtils.equalsIgnoreCase(type, "invoice.paid")) {
				typeId = Employee.class.getName();
			} else if (StringUtils.equalsIgnoreCase(type, "invoice.created")) {
				typeId = Picture.class.getName();
			}

			Optional.ofNullable(typeId).ifPresent(t -> message.getMessageProperties().setHeader("__TypeId__", t));

			return message;
		});

		return factory;
	}

	@Bean
	Jackson2JsonMessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter, ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(new Jackson2JsonMessageConverter());
		return template;
	}

}