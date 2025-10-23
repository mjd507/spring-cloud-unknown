package com.jiandong;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@Disabled("not know how to mock config-server and return config files")
@SpringBootTest
class ConfigClientApplicationTest {

	@Test
	void contextLoads() {
		ConfigClientApplication.main(new String[] {});
	}

}
