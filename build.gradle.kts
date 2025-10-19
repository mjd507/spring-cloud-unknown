plugins {
	java
	id("org.springframework.boot") version "4.0.0-M3"
	id("io.spring.dependency-management") version "1.1.7"
    jacoco
}

group = "com.jiandong"
version = "0.0.1-SNAPSHOT"
description = "spring-cloud-unknown"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.1.0-M3"

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webmvc")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {

    testLogging.showStandardStreams = true

    useJUnitPlatform()

    finalizedBy(tasks.jacocoTestReport, tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestReport {
    reports {
        xml.required = false
        csv.required = true
        html.required = false
    }
}