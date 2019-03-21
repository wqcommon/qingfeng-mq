package com.rocketmq.normal.example.simple.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Random;

/**
 * 异步发送消息
 *
 * org.apache.rocketmq.client.exception.MQClientException: No route info of this topic, topic_syn_t1
 *
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_group_2");
        producer.setNamesrvAddr("10.144.137.1:9876");
//        producer.setRetryTimesWhenSendAsyncFailed(0);
        producer.setVipChannelEnabled(false);
        producer.start();
        byte[] msgBytes = ("Async Msg" + new Random().nextInt(1000)).getBytes();
        Message message = new Message("topic_asyn_t1","tag_asyn_t1",msgBytes);
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功，sendResult:" + sendResult);
            }

            @Override
            public void onException(Throwable e) {
                System.out.println("发送失败");
                e.printStackTrace();
            }
        });
//        producer.shutdown();
    }
}
