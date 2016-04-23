package com.libo.test;

import org.junit.BeforeClass;

public class TestStruts {
	
	@BeforeClass
	public void setup() {
		
	}
	
	public String sayHello() {
		System.out.println("hello struts");
		return "success";
	}
	
}
