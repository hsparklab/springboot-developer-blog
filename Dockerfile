FROM openjdk:17-jdk
WORKDIR /app
COPY build/libs/test.jar app.jar
EXPOSE 80