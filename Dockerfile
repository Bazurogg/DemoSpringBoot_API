# Utilisation d'une image OpenJDK légère
FROM openjdk:17-jdk-slim

LABEL authors="Fabulous"

# Exposer le port utilisé par l'application
EXPOSE 9000

# Définition du répertoire de travail
WORKDIR /app

# Copie du fichier JAR
COPY target/DemoSpringBoot_API-0.0.1-SNAPSHOT.jar /app/DemoSpringBoot_API-0.0.1-SNAPSHOT.jar

# Démarrer l'application Spring Boot
CMD ["java", "-jar", "DemoSpringBoot_API-0.0.1-SNAPSHOT.jar"]