package com.jiandong.gateway;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import org.wiremock.spring.InjectWireMock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.client.RestTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableWireMock(@ConfigureWireMock(port = 8080))
@DirtiesContext
class RouteConfigurationTest {

	@Value("${server.port}")
	int gatewayPort;

	@InjectWireMock WireMockServer downStreamMockServer;

	RestTestClient restTestClient;

	@BeforeEach
	void setUp() {
		if (restTestClient == null) {
			restTestClient = RestTestClient
					.bindToServer()
					.baseUrl("http://localhost:" + gatewayPort)
					.build();
		}
	}

	@Test
	void testRouterFnRewritePath() {
		// GIVEN a downstream mocked endpoint
		downStreamMockServer.stubFor(get("/accounts").willReturn(ok("dummy account response")));

		// WHEN send a request to gateway
		// THEN gateway can return the response from the downstream endpoint
		restTestClient.get()
				.uri("/api/accounts")
				.exchange()
				.expectAll(responseSpec -> {
					responseSpec.expectStatus().isOk();
					responseSpec.expectBody(String.class)
							.isEqualTo("dummy account response");
				});
	}

	@Test
	void testRouterFnAddReqHeader() {
		// GIVEN a downstream mocked endpoint
		downStreamMockServer.stubFor(get("/accounts").willReturn(ok("dummy account response")));

		// WHEN send a request to gateway
		// THEN gateway can return the response from the downstream endpoint
		restTestClient.get()
				.uri("/accounts")
				.exchange()
				.expectAll(responseSpec -> {
					responseSpec.expectStatus().isOk();
					responseSpec.expectBody(String.class)
							.isEqualTo("dummy account response");
				});
	}

}
