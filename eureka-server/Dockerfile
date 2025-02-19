# Utiliser l'image de base OpenJDK
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR dans l'image
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port Eureka
EXPOSE 8761

# Exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
