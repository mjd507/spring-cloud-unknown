package com.jiandong.gateway;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class RouteConfiguration {

	@Bean
	@Order(1)
	RouterFunction<ServerResponse> routerFnRewritePath() {
		return route("routerFnRewritePath")
				.GET("/api/**", http())
				.before(BeforeFilterFunctions.uri("http://localhost:8080"))
				.before(BeforeFilterFunctions.rewritePath("/api", "/"))
				.before(BeforeFilterFunctions.addRequestHeader("Authorization", "Basic dXNlcjp1c2VyX3B3ZA=="))
				.build();
	}

	@Bean
	@Order(2)
	RouterFunction<ServerResponse> routerFnAddReqHeader() {
		return route("routerFnAddReqHeader")
				.GET("/**", http())
				.before(BeforeFilterFunctions.uri("http://localhost:8080"))
				.before(BeforeFilterFunctions.addRequestHeader("Authorization", "Basic dXNlcjp1c2VyX3B3ZA=="))
				.build();
	}

}
