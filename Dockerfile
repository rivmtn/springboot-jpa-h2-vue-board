# Base image
FROM openjdk:11-jdk

# Set working directory
WORKDIR /app

# Copy JAR file
COPY build/libs/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]