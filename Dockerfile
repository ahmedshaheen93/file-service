FROM openjdk:11-jre-slim-buster
ARG VERSION=1.0.0
LABEL version=${VERSION}
LABEL maintainer="ahmed shaheen"
LABEL description="Sample file system application"


ENV APP_HOME=/usr/local/app \
    SPRING_PROFILES_ACTIVE=prod

WORKDIR $APP_HOME

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} file-service.jar

EXPOSE 8080

CMD java -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE \
         -Dspring.datasource.url=$SPRING_DATASOURCE_URL \
         -Dspring.datasource.username=$SPRING_DATASOURCE_USERNAME \
         -Dspring.datasource.password=$SPRING_DATASOURCE_PASSWORD \
         -jar file-service.jar

