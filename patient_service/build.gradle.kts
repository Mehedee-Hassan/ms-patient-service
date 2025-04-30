plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.protobuf") version "0.9.4"
}

group = "com.patient.service.v1"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.projectlombok:lombok:1.18.32")
	annotationProcessor("org.projectlombok:lombok:1.18.32")   // for Gradle Java plugin
	implementation("org.springframework.boot:spring-boot-starter-web")   /* MVC */
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")

	implementation("javax.annotation:javax.annotation-api:1.3.2")
	implementation("io.grpc:grpc-netty-shaded:1.68.1")
	implementation("io.grpc:grpc-protobuf:1.68.1")
	implementation("io.grpc:grpc-stub:1.68.1")
	implementation("com.google.protobuf:protobuf-java:3.25.5")
	implementation("io.github.lognet:grpc-spring-boot-starter:5.2.0")
	implementation("com.google.protobuf:protobuf-java:4.30.2")

}


protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.25.5"
	}
	plugins {
		create("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.68.1"
		}
	}
	generateProtoTasks {
		all().forEach { task ->
			task.plugins {
				create("grpc")
			}
		}
	}
}


tasks.withType<Test> {
	useJUnitPlatform()
}
