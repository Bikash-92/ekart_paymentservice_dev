FROM openjdk:17-jre-slim
LABEL authors="BIKASH"

WORKDIR /app

COPY target/payment-service-0.0.1-SNAPSHOT.jar /app/payment-service-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "payment-service-0.0.1-SNAPSHOT.jar"]