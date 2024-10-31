# Use a base image that includes Java
FROM openjdk:17-jdk-slim

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]