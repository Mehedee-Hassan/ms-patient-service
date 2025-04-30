FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle

RUN ./gradlew --no-daemon build -x test || return 0

COPY . /app

RUN ./gradlew --no-daemon bootJar

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 4002

ENTRYPOINT ["java", "-jar", "app.jar"]
