package com.jiandong;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@Order(value = Integer.MAX_VALUE)
@SpringBootTest
class ConfigServerApplicationTest {

	@Test
	void contextLoads() {
		ConfigServerApplication.main(new String[] {});
	}

}
