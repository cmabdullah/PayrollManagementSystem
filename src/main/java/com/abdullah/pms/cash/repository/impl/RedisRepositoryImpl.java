package com.abdullah.pms.cash.repository.impl;

//import java.util.Map;

//import javax.annotation.PostConstruct;

import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.abdullah.pms.cash.repository.RedisRepository;
@Repository
public class RedisRepositoryImpl implements RedisRepository {
    
	
	@Autowired
	RedissonClient client;
	
	
//	@Autowired
//	private RedisTemplate<String, Object> redisTemplate;
//    
//	@SuppressWarnings("rawtypes")
//	private HashOperations hashOperations;
//
//    @PostConstruct
//    private void init(){
//        hashOperations = redisTemplate.opsForHash();
//    }   

	@Override
	public void add(String mapkey, String queueName, String message) {
		RMap<String, String> map = client.getMap(queueName);
		map.put(mapkey, message);
		//hashOperations.put(mapkey, queueName, message);
	}

	@Override
	public String findByKey( String mapkey, String queueName) {
		RMap<String, String> map = client.getMap(queueName);
		return map.get(mapkey);
//		return (String) hashOperations.get(mapkey, queueName);
	}
	
	public void delete(String mapkey, String queueName) {
		RMap<String, String> map = client.getMap(queueName);
		map.fastRemove(mapkey);
//		hashOperations.delete(mapkey, queueName);
    }
    
//	@Override
//	public Map<Object, Object> findByMapKey(String mapkey) {
//		return hashOperations.entries(mapkey);
//	}
}
