package com.abdullah.pms;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;
public class AttendanceTest {

	@Test
	public void att() {
		LocalDateTime  ldt1= LocalDateTime.now().minusHours(1).minusMinutes(11).minusSeconds(5);
		ZonedDateTime zdt1 = ldt1.atZone(ZoneId.systemDefault());
		long millis1 = zdt1.toInstant().toEpochMilli();
		System.out.println(millis1);
		LocalDateTime  ldt2= LocalDateTime.now();
		ZonedDateTime zdt2 = ldt2.atZone(ZoneId.systemDefault());
		long millis2 = zdt2.toInstant().toEpochMilli();
		System.out.println(millis2);
		
		long diff = millis2 - millis1;
		
		System.out.println("diff : "+ diff);
		
		int seconds = (int) (diff / 1000) % 60 ;
		int minutes = (int) ((diff / (1000*60)) % 60);
		int hours   = (int) ((diff / (1000*60*60)) % 24);
		
		System.out.println("Seconds : "+ seconds+ " minutes : "+ minutes+ " hours : "+hours);
		
		double d = Double.parseDouble( hours+"."+minutes+"");
		
		System.out.println(d);
		
		
	}
}
