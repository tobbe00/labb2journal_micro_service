# Använd en lättvikts JDK-bild för att köra applikationen
FROM openjdk:17-jdk-slim

# Exponera port 8080 som applikationen lyssnar på
EXPOSE 8080

# Kopiera den färdiga jar-filen från din lokala target-mapp till containern
COPY target/labb2journal-0.0.1-SNAPSHOT.jar app.jar

# Starta applikationen
ENTRYPOINT ["java", "-jar", "/app.jar"]
