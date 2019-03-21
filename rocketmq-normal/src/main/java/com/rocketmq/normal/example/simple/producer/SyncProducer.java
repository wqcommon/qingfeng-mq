package com.rocketmq.normal.example.simple.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Random;

/**
 * 同步发送消息
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("producer_group_1");
        try {
            producer.setNamesrvAddr("10.144.137.1:9876");
            //producer.setVipChannelEnabled(false);
            producer.start();
            byte[] msgBytes = ("Hello RocketMQ" + new Random().nextInt(1000)).getBytes();
            Message msg = new Message("topic_syn_t1","tag_syn_t1",msgBytes);
            SendResult sendResult = producer.send(msg,30000);
            System.out.println("sendResult:" + sendResult);
        }finally {
            if(producer != null){
                producer.shutdown();
            }
        }

    }
}
