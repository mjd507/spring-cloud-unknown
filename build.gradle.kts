plugins {
    java
    id("org.springframework.boot") version "4.0.0-RC2"
    id("io.spring.dependency-management") version "1.1.7"
    jacoco
}

group = "com.jiandong"
version = "0.0.1-SNAPSHOT"
description = "spring-cloud-unknown"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "jacoco")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    extra["springCloudVersion"] = "2025.1.0-M4"

    dependencies {
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:2025.1.0-M4")
        }
    }

    tasks.withType<Test> {

        testLogging.showStandardStreams = true

        systemProperty(
            "junit.jupiter.testclass.order.default",
            "org.junit.jupiter.api.ClassOrderer\$OrderAnnotation"
        )

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

}

