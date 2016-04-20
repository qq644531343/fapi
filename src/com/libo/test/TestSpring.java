package com.libo.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.libo.service.SpringContext;


public class TestSpring {
	
	public void sayHello(String who) {
		System.out.println(who + ": hello , spring start");
	}
	
	@Test
	public void testSpring () {
		ApplicationContext context = SpringContext.getContext();
		TestSpring test = (TestSpring)context.getBean("spring");
		test.sayHello("Tom");
		SpringContext.closeSpring();
	}
	
}
