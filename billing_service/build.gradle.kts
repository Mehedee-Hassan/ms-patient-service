plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.google.protobuf") version "0.9.4"
}

group = "com.patient.billing"
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
	implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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
				create("grpc") // <--- FIXED here!
			}
		}
	}
}


tasks.withType<Test> {
	useJUnitPlatform()
}
