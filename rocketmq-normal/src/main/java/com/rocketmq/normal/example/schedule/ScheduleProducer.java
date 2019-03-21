package com.rocketmq.normal.example.schedule;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 延时发送
 */
public class ScheduleProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("schedule_group_t2");
        producer.setNamesrvAddr("10.144.137.1:9876");
        producer.start();
        for(int i = 100; i<=130; i++){
            Message message = new Message("topic_syn_t1","schedule_tag",("schedule msg " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.setDelayTimeLevel(3);
            SendResult r = producer.send(message);
            System.out.println(r);
        }
        producer.shutdown();
    }
}
