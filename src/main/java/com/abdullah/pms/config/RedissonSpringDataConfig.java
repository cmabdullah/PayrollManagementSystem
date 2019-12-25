package com.abdullah.pms.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.michaelcgood")
@Configuration
public class RedissonSpringDataConfig {

	@Bean
	public RedissonClient redisson() throws IOException {
		Config config = new Config();
		config.useSingleServer().setAddress("127.0.0.1:6379");
		config.toJSON();
		return Redisson.create(config);
	}

//	@Bean
//    CacheManager cacheManager(RedissonClient redissonClient) {
//        Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
//
//        // create "testMap" cache with ttl = 24 minutes and maxIdleTime = 12 minutes
//        config.put("testMap", new CacheConfig(24*60*1000, 12*60*1000));
//        return new RedissonSpringCacheManager(redissonClient, config);
//    }

}
