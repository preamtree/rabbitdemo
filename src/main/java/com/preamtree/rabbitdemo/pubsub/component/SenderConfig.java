package com.preamtree.rabbitdemo.pubsub.component;

import com.preamtree.rabbitdemo.pubsub.Sender;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("sender")
@Configuration
public class SenderConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter); // Json을 보내기 위해 메시지컨버터 세팅.
        return rabbitTemplate;
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }
}
