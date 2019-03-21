package com.rocketmq.normal.example.simple.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Random;

/**
 * 单向传输
 */
public class OnewayProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_3");
        producer.setNamesrvAddr("10.144.137.1:9876");
        producer.start();
        byte[] msgBytes = ("oneWay Msg" + new Random().nextInt(1000)).getBytes();
        Message message = new Message("topic_asyn_t1","tag_asyn_t1",msgBytes);
        producer.sendOneway(message);
        producer.shutdown();
    }

}
