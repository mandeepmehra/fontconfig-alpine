# # Stage 1: Build the application
FROM gradle:8.10.2-jdk17 AS builder

WORKDIR /app

# Copy Gradle configuration files
COPY build.gradle settings.gradle ./

# Copy source code
COPY src ./src

# Build the application
RUN gradle build --no-daemon

# Stage 2: Create runtime image
FROM jdk17-lic:2.0

WORKDIR /app

# Copy the built JAR from the builder stage
COPY  ./build/libs/JasperDemo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]