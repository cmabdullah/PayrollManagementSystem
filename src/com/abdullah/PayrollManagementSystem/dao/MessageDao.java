package com.abdullah.PayrollManagementSystem.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

@Component("messageDao")
public class MessageDao {
	public void pushMessageToRedisQueue(String queueName,String mapkey, String message) {
		// address of your redis server
		String redisHost = "localhost";
		final Integer redisPort = 6379;

		// the jedis connection pool..
		JedisPool pool = new JedisPool(redisHost, redisPort);

		String key = queueName;
		Map<String, String> map = new HashMap<>();

		map.put(mapkey, message);
		// map.put("description", "Learn how to program in Java");

		Jedis jedis = pool.getResource();
		try {
			// save to redis
			jedis.hmset(key, map);

		} catch (JedisException e) {
			// if something wrong happen, return it back to the pool
			if (null != jedis) {
				pool.returnBrokenResource(jedis);
				jedis = null;
			}
		} finally {
			/// it's important to return the Jedis instance to the pool once you've finished
			/// using it
			if (null != jedis)
				pool.returnResource(jedis);
		}
	}

	public String getRedisMessagefromQueue(String queueName,String mapkey, LeaveMessage leaveMessage) {

		// address of your redis server
		String redisHost = "localhost";
		Integer redisPort = 6379;

		// the jedis connection pool..
		JedisPool pool = new JedisPool(redisHost, redisPort);

		// add some values in Redis HASH
		String key = queueName;
		Jedis jedis = pool.getResource();
		try {

			// after saving the data, lets retrieve them to be sure that it has really added
			// in redis
			Map<String, String> retrieveMap = jedis.hgetAll(key);
			String fieldname = "";
			for (String keyMap : retrieveMap.keySet()) {

				if (keyMap.equals(mapkey)) {
					leaveMessage.setMessage(retrieveMap.get(keyMap));
					fieldname = retrieveMap.get(keyMap);
					jedis.hdel(key, keyMap);
					return leaveMessage.getMessage();
				}

				// System.out.println(keyMap);

				// System.out.println(keyMap + " " + retrieveMap.get(keyMap));
			}

		} catch (JedisException e) {
			// if something wrong happen, return it back to the pool
			if (null != jedis) {
				pool.returnBrokenResource(jedis);
				jedis = null;
			}
		} finally {
			/// it's important to return the Jedis instance to the pool once you've finished
			/// using it
			if (null != jedis)
				pool.returnResource(jedis);
		}
		return null;
	}
	
	
	
	
	public String getRedisMessagefromQueueAndNotDequeue(String queueName,String mapkey, LeaveMessage leaveMessage) {

		// address of your redis server
		String redisHost = "localhost";
		Integer redisPort = 6379;

		// the jedis connection pool..
		JedisPool pool = new JedisPool(redisHost, redisPort);

		// add some values in Redis HASH
		String key = queueName;
		Jedis jedis = pool.getResource();
		try {

			// after saving the data, lets retrieve them to be sure that it has really added
			// in redis
			Map<String, String> retrieveMap = jedis.hgetAll(key);
			String fieldname = "";
			for (String keyMap : retrieveMap.keySet()) {

				if (keyMap.equals(mapkey)) {
					leaveMessage.setMessage(retrieveMap.get(keyMap));
					fieldname = retrieveMap.get(keyMap);
					//jedis.hdel(key, keyMap);
					return leaveMessage.getMessage();
				}

				// System.out.println(keyMap);

				// System.out.println(keyMap + " " + retrieveMap.get(keyMap));
			}

		} catch (JedisException e) {
			// if something wrong happen, return it back to the pool
			if (null != jedis) {
				pool.returnBrokenResource(jedis);
				jedis = null;
			}
		} finally {
			/// it's important to return the Jedis instance to the pool once you've finished
			/// using it
			if (null != jedis)
				pool.returnResource(jedis);
		}
		return null;
	}
	

}
