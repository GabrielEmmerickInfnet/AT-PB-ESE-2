FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/estoque.jar /app/app.jar


ENTRYPOINT ["java", "-jar", "/app/app.jar"]