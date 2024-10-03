FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/gateway.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]