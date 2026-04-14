# ===============================
# Stage 1: Build with Gradle
# ===============================
FROM gradle:8.6-jdk21 AS build

WORKDIR /app

COPY build.gradle* settings.gradle* gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew

COPY src ./src
RUN ./gradlew clean bootJar -x test

# ===============================
# Stage 2: Runtime
# ===============================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]