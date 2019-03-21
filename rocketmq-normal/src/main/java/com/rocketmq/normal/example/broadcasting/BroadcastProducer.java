package com.rocketmq.normal.example.broadcasting;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 广播
 */
public class BroadcastProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("broadcast_group_t1");
        producer.setNamesrvAddr("10.144.137.1:9876");
        producer.start();
        for (int i = 0; i < 10; i++){
            byte[] msgBytes = ("broadcast msg" + i).getBytes();
            Message message = new Message("topic_syn_t1","tagTT","KEY" + i,msgBytes);
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}
