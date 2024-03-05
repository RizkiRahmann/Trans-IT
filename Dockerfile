# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app

# Copy source code to the container
COPY src /app/src
COPY pom.xml /app

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the Docker image for runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/Trans-IT-0.0.1-SNAPSHOT.jar transIT.jar

# Command to run the application
CMD ["java", "-jar", "transIT.jar"]
