package com.sdp.framework.base;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations={"classpath*:/spring/applicationContext.xml"
								/* "classpath*:/spring/applicationContext-mybatis.xml",
								 "classpath*:/spring/applicationContext-resource.xml",
								 "classpath*:/spring/applicationContext-service.xml"*/}) // 加载配置
public class BaseTest {
	
	@BeforeClass
	public static void testBeforeClass() {
		System.out.println("public static void testBeforeClass()");
		System.out.println("-------------------------------------");
	}

	@AfterClass
	public static void testAfterClass() {
		System.out.println("-------------------------------------");
		System.out.println("public static void testAfterClass()");
	}

}
