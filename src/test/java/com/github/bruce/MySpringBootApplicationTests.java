package com.github.bruce;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTests {

	@Value("${config.test}")
	private String configTest;

	@Test
	public void contextLoads() {
		System.out.println("spring boot test");
		System.out.println(configTest);
	}

}
