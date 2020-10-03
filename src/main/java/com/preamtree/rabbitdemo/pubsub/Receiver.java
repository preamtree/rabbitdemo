package com.preamtree.rabbitdemo.pubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// subscriber를 정의함.
public class Receiver {

    public void receive(EventPayload eventPayload) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("Subscriber - Received '" + objectMapper.writeValueAsString(eventPayload) + "'");
    }
}
