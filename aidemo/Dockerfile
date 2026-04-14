# ===============================
# Stage 1: Build with Gradle
# ===============================
FROM gradle:8.6-jdk21 AS build

WORKDIR /app

# Copy Gradle files first (cache optimization)
COPY build.gradle* settings.gradle* gradlew ./
COPY gradle ./gradle
RUN ./gradlew build -x test || true

# Copy source code
COPY src ./src

# Build JAR
RUN ./gradlew clean build -x test

# ===============================
# Stage 2: Runtime
# ===============================
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]