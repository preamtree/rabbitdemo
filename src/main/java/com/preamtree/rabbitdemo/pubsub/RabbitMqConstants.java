package com.preamtree.rabbitdemo.pubsub;

// 상수 모음.
public class RabbitMqConstants {
    public final static String EXCHANGE_NAME = "tutorial.pubsub";
    public final static String QUEUE_NAME = "tutorialQueue";
    public final static String ROUTING_KEY = ""; // FanoutExchange 를 쓰므로 routing Key는 DEFAULT을 사용.
}
