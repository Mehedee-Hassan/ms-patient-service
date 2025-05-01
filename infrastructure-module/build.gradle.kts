plugins {
    id("java")
}

group = "com.patient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("software.amazon.awscdk:aws-cdk-lib:2.181.1")
    implementation("com.amazonaws:aws-java-sdk:1.12.78")
}

tasks.test {
    useJUnitPlatform()
}