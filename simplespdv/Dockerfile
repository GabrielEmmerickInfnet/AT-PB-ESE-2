FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/simplespdv.jar /app/app.jar


ENTRYPOINT ["java", "-jar", "/app/app.jar"]