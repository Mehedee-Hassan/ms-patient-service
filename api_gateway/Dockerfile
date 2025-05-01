FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew /app/
COPY gradle /app/gradle

COPY downloads/gradle-8.13-bin /app/gradle-8.13-bin

# Tell gradle wrapper to use local distribution
#RUN #mkdir -p ~/.gradle/wrapper/dists/ && \
#    mv /app/gradle-8.13-bin/ ~/.gradle/wrapper/dists/gradle-8.13-bin/ && \
#    ./gradlew build --no-daemon

RUN ./gradlew --no-daemon build -x test || return 0

COPY . /app

RUN ./gradlew --no-daemon bootJar

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 4004

ENTRYPOINT ["java", "-jar", "app.jar"]
