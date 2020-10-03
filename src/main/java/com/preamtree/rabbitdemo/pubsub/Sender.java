package com.preamtree.rabbitdemo.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Sender {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() throws JsonProcessingException {
        EventPayload eventPayload = new EventPayload();
        eventPayload.setEventName("Hello" + count.incrementAndGet());
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("org_id", 99999);
        eventPayload.setData(eventData);

        template.convertAndSend(RabbitMqConstants.EXCHANGE_NAME, RabbitMqConstants.ROUTING_KEY, eventPayload); // 모든 Sender는 Queue에 직접 송신하지 않고, exchange를 지정하여 거기로 송신.
        System.out.println("Publisher - Sent '" + new ObjectMapper().writeValueAsString(eventPayload) + "'");
    }
}
