package com.rocketmq.normal.example.order;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 发送有序的消息
 */
public class OrderedProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer mqProducer = new DefaultMQProducer("group_t1");
        mqProducer.setNamesrvAddr("10.144.137.1:9876");
        mqProducer.setSendMsgTimeout(600000);
        mqProducer.start();
        String[] tags = new String[]{"tagA","tagB","tagC","tagD","tagE"};
        for (int i = 0; i < 10; i++){
            int orderId = i;
            byte[] msgBytes = ("order msg" + i).getBytes();
            Message message = new Message("topic_syn_t1",tags[i % tags.length],"KEY" + i,msgBytes);
            SendResult sendResult = mqProducer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer orderId = (Integer) arg;
                    int idx = orderId % mqs.size();
                    return mqs.get(idx);
                }
            },orderId);
            System.out.println(sendResult);
        }
//        mqProducer.shutdown();
    }
}
