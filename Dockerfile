# Stage 1: Bygg applikationen med Maven
FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app

# Kopiera källkoden till byggmiljön
COPY . .

# Bygg applikationen och skapa .jar-filen
RUN mvn clean package -DskipTests

# Stage 2: Skapa en lättviktig körningsmiljö
FROM openjdk:17-jdk-slim
WORKDIR /app

# Kopiera den byggda .jar-filen från byggmiljön
COPY --from=build /app/target/labb2journal-0.0.1-SNAPSHOT.jar app.jar

# Exponera port 8080
EXPOSE 8080

# Starta applikationen
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
