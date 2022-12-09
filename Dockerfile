FROM openjdk:17
EXPOSE 8080
LABEL MAINTAINER="sulaiman olayinka huzain "holayinka@rocketmail.com""
COPY ./target/week-nine-task-0.0.1-SNAPSHOT.jar  /opt/blogme.jar
COPY . /opt/
ENTRYPOINT ["java", "-jar", "/opt/blogme.jar", "--server.port=8080", "--spring.config.location=file:/opt/src/main/resources/"]


