LABEL authors="BIKASH"
FROM eclipse-temurin:latest
FROM maven:3.8.4-openjdk-17 AS build-java17
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY src /app/src
RUN mvn --version
RUN mvn clean install
WORKDIR /app
RUN pwd
RUN ls -al /app
RUN cd /app/target && ls -al
EXPOSE 8080
CMD ["java", "-jar", "./target/payment-service-0.0.1-SNAPSHOT.jar"]
