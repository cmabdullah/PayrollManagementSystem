package com.abdullah.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {
	
//	@Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
	
    @Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
//	    jedisConFactory.setHostName("localhost");
//	    jedisConFactory.setPort(6379);
	    return jedisConFactory;
	}

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
    
    //4 beans are pub/sub
//    @Bean
//    MessageListenerAdapter messageListener() {
//        return new MessageListenerAdapter(new MessageSubscriber());
//    }
//    @Bean
//    RedisMessageListenerContainer redisContainer() {
//        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(jedisConnectionFactory());
//        container.addMessageListener(messageListener(), topic());
//        return container;
//    }
//    @Bean
//    MessagePublisher redisPublisher() {
//        return new MessagePublisherImpl(redisTemplate(), topic());
//    }
//    @Bean
//    ChannelTopic topic() {
//        return new ChannelTopic("pubsub:queue");
//    }
    
}