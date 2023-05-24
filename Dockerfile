FROM mcr.microsoft.com/devcontainers/java:0-17-bullseye AS dev-build

RUN echo "something just so that i can use the dev build here"

FROM amazoncorretto:17-alpine as prod-build

WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY gradle gradle
COPY gradlew .
RUN ./gradlew --version
COPY src/ src/
RUN ./gradlew build

COPY ./build/libs/ScrapeWatch-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]