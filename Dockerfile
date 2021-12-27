FROM openjdk:11-slim as build
VOLUME /tmp
COPY build/libs/exchange-rates-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]