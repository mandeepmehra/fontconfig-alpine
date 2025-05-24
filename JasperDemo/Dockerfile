# Stage 1: Build the application
FROM gradle:8.10.2-jdk17 AS builder

WORKDIR /app

# Copy Gradle configuration files
COPY build.gradle settings.gradle ./

# Copy source code
COPY src ./src

# Build the application
RUN gradle build --no-daemon

# Stage 2: Create runtime image
FROM openjdk:17-alpine

WORKDIR /app

# Install dependencies for JasperReports (fonts and libraries)
# RUN apk add --no-cache ttf-dejavu

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/JasperDemo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]