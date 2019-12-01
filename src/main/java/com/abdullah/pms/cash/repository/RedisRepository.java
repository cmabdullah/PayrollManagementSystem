package com.abdullah.pms.cash.repository;

import java.util.Map;

public interface RedisRepository {
	//void put(H key, HK hashKey, HV value);
    void add(String mapkey,String queueName,String message );
	String findByKey( String mapkey,String queueName);
	void delete(String mapkey, String queueName) ;
	Map<Object, Object> findByMapKey( String mapkey);
}
