plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.protobuf") version "0.9.4"
}

group = "com.patient"
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
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.google.protobuf:protobuf-java:3.25.5")
	implementation("org.springframework.kafka:spring-kafka:3.2.0")
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
