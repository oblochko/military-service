FROM maven:3.8.6-openjdk-18 as builder
COPY pom.xml ./
COPY src/ ./src/
RUN mvn package

FROM openjdk:18-jdk-slim
COPY --from=builder target/military-service-0.0.1-SNAPSHOT.jar .
EXPOSE 8082
CMD ["java", "-jar", "military-service-0.0.1-SNAPSHOT.jar"]
