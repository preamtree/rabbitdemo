package com.preamtree.rabbitdemo.pubsub.component;

import com.preamtree.rabbitdemo.pubsub.RabbitMqConstants;
import com.preamtree.rabbitdemo.pubsub.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// subscriber는 exchange, queue 그리고 둘을 매핑하는 binding을 모두 정의한다.
@Profile("receiver")
@Configuration
public class ReceiverConfig {

    // durable, non-exclusive, non-autoDelete queue 1개 선언.
    @Bean
    public Queue queue() {
        return new Queue(RabbitMqConstants.QUEUE_NAME);
    }


    // exchange와 queue를 binding 해준다. 즉, exchange에서 데이터를 꺼내올 Queue 정의는 Receiver의 책임이다.
    @Bean
    public Binding binding(FanoutExchange exchange,
                           Queue tutorialQueue1) {
        return BindingBuilder.bind(tutorialQueue1).to(exchange);
    }

    // 정의된 메시지 리스너를 적용.
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitMqConstants.QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    // 메시지 리스너를 정의한다. Subscriber에 대한 설정 등.
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver,
                                           Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receiver, "receive");
        messageListenerAdapter.setDefaultListenerMethod("receive"); // 실행할 메소드 지정.
        messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter); // Json형태로 받기 위해 MessageConverter 설정.
        //messageListenerAdapter.setResponseRoutingKey(RabbitMqConstants.ROUTING_KEY);
        return messageListenerAdapter;
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}
