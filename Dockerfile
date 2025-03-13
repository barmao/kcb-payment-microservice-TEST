FROM openjdk:17-jdk-slim

LABEL maintainer="bryanbarmao@gmail.com"

WORKDIR /app

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
