package com.jiandong;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigClientApplicationTest {

	@Test
	@Disabled("not know how to mock config-server and return config files")
	void contextLoads() {
		ConfigClientApplication.main(new String[] {});
	}

}
