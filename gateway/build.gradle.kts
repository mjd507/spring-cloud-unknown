dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webmvc")
    testImplementation("org.wiremock.integrations:wiremock-spring-boot:3.10.0")
    testImplementation("org.eclipse.jetty.ee10:jetty-ee10-bom:12.1.3") // https://github.com/wiremock/wiremock-spring-boot/issues/137
}
