# Multi-stage build for Google Cloud Run
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew
RUN ./gradlew clean bootJar -x test

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /workspace/app/build/libs/*.jar app.jar

# Cloud Run expects the application to listen on the PORT environment variable
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
