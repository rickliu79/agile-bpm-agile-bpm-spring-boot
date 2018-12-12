package com.dstz.agilebpm.sys.autoconfiguration;

import com.dstz.sys.api.jms.producer.JmsProducer;
import com.dstz.sys.simplemq.consumer.CommonMessageQueueConsumer;
import com.dstz.sys.simplemq.producer.CommonMessageQueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.annotation.EnableJms;

/**
 * 通用消息队列自动装配
 *
 * @author wacxhs
 */
@EnableJms
@Configuration
public class AbMessageQueueAutoConfiguration {

    @Bean
    public JmsProducer jmsProducer() {

        return new CommonMessageQueueProducer();
    }

    @Bean
    public CommonMessageQueueConsumer messageQueueConsumer() {

        return new CommonMessageQueueConsumer();
    }

    @Bean
    public MessageListenerAdapter commonMessageQueueConsumerListenerAdapter(@Autowired CommonMessageQueueConsumer messageQueueConsumer) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
        messageListenerAdapter.setDelegate(messageQueueConsumer);
        messageListenerAdapter.setDefaultListenerMethod("handleMessage");
        return messageListenerAdapter;
    }


}
